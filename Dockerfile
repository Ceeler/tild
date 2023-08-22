#COPY .mvn/ .mvn/
#COPY mvnw pom.xml ./

#RUN ./mvnw dependency:resolve

#COPY src ./src

#CMD ["./mvnw", "spring-boot:run"]


FROM maven:3.8.4-openjdk-17-slim AS build

# Copy Maven files for dependency resolution
COPY pom.xml ./
COPY .mvn .mvn

# Copy application source code
COPY src src

# Build the project and create the executable JAR
RUN mvn clean install -DskipTests

# Stage 2: Run stage
FROM amazoncorretto:17

# Set working directory
WORKDIR tild-service

# Copy the JAR file from the build stage
COPY --from=build target/*.jar /app/tild-service.jar

# Set the entrypoint command for running the application
ENTRYPOINT ["java", "-jar", "/app/tild-service.jar"]