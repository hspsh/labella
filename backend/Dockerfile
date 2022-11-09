FROM openjdk:17-jdk-alpine
RUN apk --update add imagemagick
ADD . .
RUN ./mvnw package

FROM openjdk:17-jdk-alpine as labella
RUN apk --update add imagemagick
COPY --from=0 target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
