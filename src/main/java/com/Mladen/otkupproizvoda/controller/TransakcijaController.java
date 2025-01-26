package com.Mladen.otkupproizvoda.controller;

import com.Mladen.otkupproizvoda.entity.Operant;
import com.Mladen.otkupproizvoda.entity.Proizvod;
import com.Mladen.otkupproizvoda.entity.Transakcija;
import com.Mladen.otkupproizvoda.repository.TransakcijaRepository;
import com.Mladen.otkupproizvoda.service.OperantService;
import com.Mladen.otkupproizvoda.service.ProizvodService;
import com.Mladen.otkupproizvoda.service.TransakcijaService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;

import com.itextpdf.kernel.pdf.canvas.PdfCanvas;



import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/transakcija")
public class TransakcijaController {
    TransakcijaService transakcijaService;
    OperantService operantService;
    ProizvodService proizvodService;

    @Autowired
    public TransakcijaController(TransakcijaService transakcijaService, OperantService operantService, ProizvodService proizvodService) {
        this.transakcijaService = transakcijaService;
        this.operantService = operantService;
        this.proizvodService = proizvodService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/form")
    public String transakcijaForm(Model model)
    {
        List<Operant> operants=operantService.findAll();
        model.addAttribute("operants",operants);
        model.addAttribute("transakcija",new Transakcija());
        return "transakcija-form";
    }
    @PostMapping("/save")
    public void saveTransakcija(@Valid @ModelAttribute("transakcija") Transakcija transakcija,
                                BindingResult bindingResult,
                                Model model,
                                HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operants", operantService.findAll());
            try {
                response.sendRedirect("/transakcija-form");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        transakcijaService.save(transakcija);

        // Generiši i preuzmi PDF
        try {
            generatePdf(transakcija, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generatePdf(Transakcija transakcija, HttpServletResponse response) throws IOException {
        // Postavi response za PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=transakcija-" + transakcija.getId() + ".pdf");

        try {
            // iText PDF generisanje
            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Dodaj pozadinsku sliku
            String imagePath = "src/main/resources/static/images/otpremnica.jpg";
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image image = new Image(imageData);

            // Podesi poziciju slike (malkice ulevo)
            image.setFixedPosition(20, 0); // Pomeraj na x osi: 20


            // Prilagodi veličinu slike prema stranici
            float pageWidth = pdf.getDefaultPageSize().getWidth();
            float pageHeight = pdf.getDefaultPageSize().getHeight();
            image.scaleToFit(pageWidth, pageHeight);

            // Dodaj sliku na novu stranicu
            pdf.addNewPage();
            document.add(image);

            PdfCanvas pdfCanvas = new PdfCanvas(pdf.getFirstPage()); // Kreiranje PdfCanvas za prvu stranicu
            Rectangle rect = pdf.getDefaultPageSize(); // Dohvati veličinu stranice kao Rectangle
            Canvas canvas = new Canvas(pdfCanvas, rect); // Proslijedi pravougaonik umesto PdfDocument


            // Dodaj tekst na precizne koordinate

            canvas.showTextAligned(
                    new Paragraph(" "+proizvodService.findById(transakcija.getProizvodId()).getNaziv()),
                    280, 360,
                    TextAlignment.RIGHT
            );

            canvas.showTextAligned(
                    new Paragraph("kg"),
                    320, 360,
                    TextAlignment.RIGHT
            );

            canvas.showTextAligned(
                    new Paragraph(transakcija.getKilaza()+""),
                    360, 360,
                    TextAlignment.RIGHT
            );

            canvas.showTextAligned(
                    new Paragraph("Kutije"),
                    280, 340,
                    TextAlignment.RIGHT
            );

            canvas.showTextAligned(
                    new Paragraph("kom"),
                    320, 340,
                    TextAlignment.RIGHT
            );

            canvas.showTextAligned(
                    new Paragraph(transakcija.getBrojKutija()+""),
                    360, 340,
                    TextAlignment.RIGHT
            );

            canvas.showTextAligned(
                    new Paragraph("Posude"),
                    280, 320,
                    TextAlignment.RIGHT
            );

            canvas.showTextAligned(
                    new Paragraph("kom"),
                    320, 320,
                    TextAlignment.RIGHT
            );

            canvas.showTextAligned(
                    new Paragraph(transakcija.getBrojKutija()*8+""),
                    360, 320,
                    TextAlignment.RIGHT
            );

            canvas.showTextAligned(
                    new Paragraph(String.format("%02d.%02d.",transakcija.getDatum().getDayOfMonth(),transakcija.getDatum().getMonthValue() )),
                    370, 430, // Opet niže
                    TextAlignment.LEFT
            );

            canvas.showTextAligned(
                    new Paragraph(""+transakcija.getDatum().getYear()%100),
                    420, 430, // Opet niže
                    TextAlignment.LEFT
            );

            canvas.showTextAligned(
                    new Paragraph("Agrobel Sistem"),
                    370, 510, // Opet niže
                    TextAlignment.LEFT
            );

            canvas.showTextAligned(
                    new Paragraph(operantService.findById(transakcija.getOperantId()).getFullName()+" "+transakcija.getOperantId()),
                    130, 515, // Opet niže
                    TextAlignment.LEFT
            );

            // Zatvori dokument
            document.close();
        } catch (Exception e) {
            // Prati grešku u logovima
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Došlo je do greške pri generisanju PDF-a.");
        }
    }
}
