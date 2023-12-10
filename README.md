# spring-cloud-sample ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)😎
Spring MSA architecture Sample Project

Welcome to the Spring Cloud Microservices Project! This project is designed to demonstrate the implementation of microservices architecture using Spring Cloud in a multi-module Gradle project. The project is structured to include Eureka Server, Config Server, and Gateway Server.

<b>Config Git Here</b>
[![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/DongJu-Na/spring-cloud-sample-config-repo)

## Project Structure

The project is organized into the following modules:

- **eureka-server**: Eureka Server module responsible for service registration and discovery.
- **config-server**: Config Server module handling externalized configuration for microservices.
- **gateway-server**: Gateway Server module serving as the entry point for all microservices.
- **dummy-api-server**: DummyApi Server module serving as the entry point for all microservices.

## Prerequisites

Make sure you have the following prerequisites installed:

- Java 17
- Gradle
- Lombok plugin for your IDE (for annotation processing)

## Getting Started

To build and run the project, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/spring-cloud-microservices.git
   cd spring-cloud-microservices
   ```

2. Build the project:

   ```bash
   ./gradlew build
   ```

3. Run the Eureka Server:

   ```bash
   ./gradlew :eureka-server:bootRun
   ```

4. Run the Config Server:

   ```bash
   ./gradlew :config-server:bootRun
   ```

5. Run the Gateway Server:

   ```bash
   ./gradlew :gateway-server:bootRun
   ```
   
6. Run the Dummy API:

   ```bash
   ./gradlew :dummy-api:bootRun
   ```

Now, you have the extended microservices architecture up and running locally.

## Project Dependencies

- Spring Boot: Rapid application development platform for building stand-alone, production-grade Spring-based applications.
- Spring Cloud: Set of tools for building and orchestrating microservices.
- Lombok: Library simplifying Java code and eliminating boilerplate code for better readability.

## Additional Information

- For detailed documentation and guides, refer to the [Spring Cloud Documentation](https://spring.io/projects/spring-cloud).
- Explore each module's `build.gradle` file for specific dependencies and configurations.

Feel free to contribute, report issues, or provide feedback. Happy coding! 🚀
