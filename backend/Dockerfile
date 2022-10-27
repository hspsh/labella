FROM openjdk:17-jdk-alpine
ADD . .
RUN ./mvnw package

FROM openjdk:17-jdk-alpine as labella
COPY --from=0 target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
