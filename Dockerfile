FROM node:16.18.1
ADD ./froncik/ .
RUN npm install
RUN npm run export
FROM openjdk:17-jdk-alpine
RUN apk --update add inkscape

ADD ./backend/ .
COPY --from=0 out/ src/main/resources/static/
RUN ./mvnw package

FROM openjdk:17-jdk-alpine as labella
RUN apk --update add inkscape cups 
RUN apk add terminus-font ttf-inconsolata ttf-dejavu font-noto font-noto-cjk ttf-font-awesome font-noto-extra
COPY --from=1 target/*.jar app.jar
VOLUME /data
ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:h2:file:/data/labella","-jar","/app.jar"]
