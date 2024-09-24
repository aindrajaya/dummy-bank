# Dummy Bank

Dummy Bank is a mock banking service built using Spring Boot, providing a simple API to simulate common banking operations. This project also extends the OFX4J library to handle OFX (Open Financial Exchange) files, enabling better interaction with financial data formats.

## Features

- **Spring Boot Framework**: A robust, production-ready framework for building RESTful APIs.
- **Enhanced OFX4J Library**: Custom enhancements to the OFX4J library for improved parsing and handling of OFX files.
- **Banking Operations**: Simulate essential banking operations like account balance retrieval, transaction history, and fund transfers.
- **OFX Parsing**: Support for reading and processing OFX files to interact with financial data.
- **Customizable and Extensible**: Easily extend the functionality to integrate with real banking APIs or additional formats.

## Technologies Used
- **Java 17**: The main programming language.
- **Spring Boot**: Backend framework for developing REST APIs.
- **OFX4J (Enhanced)**: Library for parsing OFX files with added customizations for extended functionality.
- **Maven**: Dependency management and build automation.

## Getting Started
- Clone the repository.
- Install dependencies using Maven.
- Run the application with Spring Boot (`mvn spring-boot:run`).
- You can copy the `dummy.ofx` file to the folder that you want to execute the request command
- Access the API at `http://localhost:8080/ofx`.
- Open new terminal and write this command on terminal: `curl -X POST http://localhost:8080/ofx -H "Content-Type: application/x-ofx" --data-binary "@dummy.ofx"`

If you run into any issues or have feedback, please [file an issue](https://github.com/aindrajaya/dummy-bank/issues/new)

## Use Cases
- Simulate banking operations for testing financial software.
- Parse and interact with OFX files for financial data exchange.

## Contributions
Contributions are welcome! Please fork the repository and submit a pull request with your enhancements or bug fixes.

## License
This project is licensed under the MIT License.