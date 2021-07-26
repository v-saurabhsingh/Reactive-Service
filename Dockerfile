FROM openjdk:11
EXPOSE 80
WORKDIR /usr/app
ARG JAR_FILE=target/app.jar
RUN pwd
RUN ls
RUN /bin/bash -c echo $JAR_FILE > build.log
ADD ${JAR_FILE} app.jar
RUN pwd
RUN ls
CMD cat build.log
#ENTRYPOINT ["java","-jar","app.jar"]