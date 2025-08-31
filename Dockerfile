# Use official Java image
FROM openjdk:20-jdk-slim

# Set working dir
WORKDIR /app

# Copy jar into image
COPY target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
