FROM node:16.18.1
ADD ./froncik/ .
RUN npm install
RUN npm run export

FROM openjdk:17-jdk-alpine
RUN apk --update add imagemagick
ADD ./backend/ .
COPY --from=0 out/* backend/src/main/resources/static/
RUN ./mvnw package

FROM openjdk:17-jdk-alpine as labella
RUN apk --update add imagemagick
COPY --from=1 target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
