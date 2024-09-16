FROM gradle:8.10.1-jdk17 AS BUILD
WORKDIR /src
COPY . . 
RUN gradle bootJar

FROM openjdk:17
ENV JAR_NAME=app.jar
ENV APP_HOME=/src
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
EXPOSE 8080
ENTRYPOINT exec java -jar $APP_HOME/build/libs/$JAR_NAME  

