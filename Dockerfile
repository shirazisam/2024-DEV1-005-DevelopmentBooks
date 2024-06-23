FROM openjdk:latest

LABEL authors="Shiraz Isam"

EXPOSE 80

WORKDIR /usr/local/bin

COPY ./target/bnp-kata-0.0.1-SNAPSHOT.jar webapp.jar

CMD ["java", "-jar", "webapp.jar"]