FROM openjdk:17
COPY applications/butler/build/libs/butler.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]