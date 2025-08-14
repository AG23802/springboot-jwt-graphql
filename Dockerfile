# Start from an OpenJDK image
FROM openjdk:24-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the build artifact (jar) into the container
COPY target/springboot-jwt-graphql-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]