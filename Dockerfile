FROM openjdk:17-alpine

MAINTAINER Abraham Espinosa Mendoza

USER root

ENV JAVA_HOME=/opt/openjdk-17

ENV TZ=America/Mexico_City

ADD nojira-0.0.1.jar //

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["java", "-jar", "-DSpring.profiles.active=qa", "nojira-0.0.1.jar"]