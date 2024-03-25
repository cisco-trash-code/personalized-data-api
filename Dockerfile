FROM openjdk:17-alpine

WORKDIR /app

COPY target/shop-0.0.1-SNAPSHOT.jar /app/shop-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "shop-0.0.1-SNAPSHOT.jar"]