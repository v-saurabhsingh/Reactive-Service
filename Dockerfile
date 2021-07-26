FROM openjdk:11
WORKDIR /usr/app
ARG JAR_FILE=target/app.jar
RUN pwd
RUN ls
RUN /bin/bash -c 'echo $JAR_FILE' > build.log
RUN cat build.log
#ADD ${JAR_FILE} app.jar
RUN pwd
RUN ls
EXPOSE 80
#ENTRYPOINT ["java","-jar","app.jar"]