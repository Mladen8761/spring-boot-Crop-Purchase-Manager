# Produce Procurement App

A Spring Boot application for managing the procurement of agricultural products (e.g., strawberries, raspberries). The app includes features for tracking transactions, managing operators, printing delivery notes, and handling packaging logistics.

## Features

- **Add Operators:** Create and manage operator profiles.
- **Transactions:** Record purchase transactions for agricultural products.
- **Delivery Notes:** Generate and print delivery notes for transactions.
- **Packaging Management:**
  - Track packaging loans and returns.
  - Ensure proper balance of packaging inventory.

## Prerequisites

- **Java 17 or higher**
- **Maven 3.8+**
- **MySQL database**

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Mladen8761/spring-boot-Crop-Purchase-Manager.git
2.	**Navigate to the project directory:**
   cd ispring-boot-Crop-Purchase-Manager

3.	**Configure the database:**
   
	•	Create a MySQL database for the app.

	•	Update the application.properties file with your database credentials:


spring.datasource.url=jdbc:mysql://localhost:3306/your_database

spring.datasource.username=your_username

spring.datasource.password=your_password



4.	**Run the application:**


**Usage**

	•	Add Operators:
	•	Navigate to the operator management page and add/edit operators.
	•	Record Transactions:
	•	Log the purchase of products (e.g., quantities, prices, product types).
	•	Print Delivery Notes:
	•	Access transaction details and print delivery notes in PDF format.
	•	Manage Packaging:
	•	Track loans and returns of packaging materials.



**Future Enhancements**

	•	Integration with external APIs (e.g., weather APIs for crop planning).
	•	Dashboard for tracking total purchases, packaging status, and operator performance.
	•	Export reports in CSV or Excel formats.

**License**

This project is licensed under the MIT License.

