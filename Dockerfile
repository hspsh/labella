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
RUN apk add terminus-font ttf-inconsolata ttf-dejavu font-bitstream-* font-noto font-noto-cjk ttf-font-awesome font-noto-extra
COPY --from=1 target/*.jar app.jar
VOLUME /data
ENTRYPOINT ["java","-jar","/app.jar","-Dspring.datasource.url=jdbc:h2:file:/data/labella"]
