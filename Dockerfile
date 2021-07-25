FROM openjdk:11
EXPOSE 80
WORKDIR /usr/app
RUN pwd
RUN ls
#ARG JAR_FILE=target/app.jar
ADD ${JAR_FILE} app.jar
RUN pwd
RUN ls
ENTRYPOINT ["java","-jar","app.jar"]