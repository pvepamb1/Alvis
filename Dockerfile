FROM openjdk:17
COPY applications/butler/build/libs/butler.jar app.jar
ENV CONTAINER=docker
ENTRYPOINT ["java","-jar","/app.jar"]