ARG JAR_FILE=target/app.jar
FROM openjdk:11
WORKDIR /usr/app
RUN pwd
RUN ls
RUN /bin/bash -c echo $JAR_FILE > build.log
#ADD ${JAR_FILE} app.jar
RUN pwd
RUN ls
EXPOSE 80
CMD cat build.log
#ENTRYPOINT ["java","-jar","app.jar"]