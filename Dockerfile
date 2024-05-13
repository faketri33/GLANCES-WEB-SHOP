FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package

FROM amazoncorretto:17
WORKDIR /app
RUN mkdir -p /app/images
RUN chmod -R 755 /app/images
COPY --from=builder /app/target/*.jar /app/OnlineMarket.jar
ENTRYPOINT ["java", "-jar", "/app/OnlineMarket.jar"]