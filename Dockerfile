FROM openjdk:16
MAINTAINER Ivan Poltorakov <ivan@poltorakov.ru>
ADD ./target/GUAP.jar GUAP.jar
ENTRYPOINT ["java","-jar","GUAP.jar"]
EXPOSE 8060
