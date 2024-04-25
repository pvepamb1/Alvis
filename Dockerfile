FROM openjdk:17
COPY alvis/build/libs/alvis.jar app.jar
ENV CONTAINER=docker
ENTRYPOINT ["java","-jar","/app.jar"]