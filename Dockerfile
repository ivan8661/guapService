FROM adoptopenjdk/openjdk16:x86_64-alpine-jre16u-nightly
MAINTAINER Ivan Poltorakov <ivan@poltorakov.ru>
ADD ./target/GUAP.jar GUAP.jar
ENTRYPOINT java -jar GUAP.jar guap
EXPOSE 8070
