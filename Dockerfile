FROM openjdk:17
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY applications/butler/build/libs/butler.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]