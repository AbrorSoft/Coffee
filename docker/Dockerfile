# Base image with Maven and OpenJDK 17 for building the project
FROM maven:3.9.9-amazoncorretto-17 AS build

# Specify the working directory
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .

# Copy all project files
COPY . /app

# Build the project using Maven, skipping tests
RUN mvn clean package -DskipTests

# Create the final image based on OpenJDK 17
FROM openjdk:17-jdk-slim

# Specify the working directory
WORKDIR /app

# Copy the compiled .jar file from the previous build stage
COPY --from=build /target/*.jar app.jar

# Open port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
