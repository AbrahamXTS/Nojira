FROM maven:3.9.4 AS builder

MAINTAINER Abraham Espinosa - abrahamespinosa3132@gmail.com

COPY .. .

RUN mvn clean package

FROM amazoncorretto:17-alpine-jdk

COPY --from=builder /target/nojira-0.0.1.jar nojira-0.0.1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "nojira-0.0.1.jar"]