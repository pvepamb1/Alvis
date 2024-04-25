# Alvis

Alvis is a home automation application written in Java, using the Spring Boot framework. It is designed to run both on a host machine and inside a Docker container. The frontend is built with React and bundled with npm.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 11 or higher
- Node.js and npm
- Docker (optional)

### Building the Project

To build the project, navigate to the project root directory and run the following command:

```bash
./gradlew build
```

This will compile the Java code, run tests, and create a runnable JAR file in the `build/libs` directory.

### Running the Project

To run the project, use the following command:

```bash
./gradlew bootRun
```

This will start the Spring Boot application.

### Environment Variables

The application uses the following environment variables:

- `HOME`: Used to determine the location of the configuration directory when running on a host machine.
- `CONTAINER`: Used to determine whether the application is running inside a Docker container.

### Docker

To build a Docker image of the application, use the following command:

```bash
docker build -t alvis .
```

To run the application inside a Docker container, use the following command:

```bash
docker run -p 8080:8080 -e CONTAINER=docker alvis
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

This project is licensed under the MIT License - see the [LICENSE](https://choosealicense.com/licenses/mit/) file for details.