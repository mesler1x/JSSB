FROM maven:3.8.7-openjdk-18-slim as mvn_packager
COPY . .
RUN mvn --no-transfer-progress clean package -Dmaven.test.skip
FROM openjdk:18
COPY --from=mvn_packager /target/mod3_skillbox-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]