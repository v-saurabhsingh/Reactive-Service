FROM openjdk:11
EXPOSE 80
WORKDIR /usr/app
RUN pwd
RUN ls
RUN /bin/bash -c 'echo $JAR_FILE'
ARG JAR_FILE=target/app.jar
RUN /bin/bash -c 'echo $JAR_FILE'
ADD ${JAR_FILE} app.jar
RUN pwd
RUN ls
ENTRYPOINT ["java","-jar","app.jar"]