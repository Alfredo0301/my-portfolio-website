FROM openjdk:25-jdk-slim

WORKDIR /app

COPY target/PortfolioWebsite-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod
ENV DB_URL=jdbc:mysql://mysql:3306/db_portfolio
ENV DB_USERNAME=root
ENV DB_PASSWORD=

ENTRYPOINT ["java", "-jar", "app.jar"]

