FROM openjdk:11
WORKDIR /usr/app
ARG JAR_FILE=target/app.jar
RUN pwd
RUN ls
RUN echo ${JAR_FILE} > build.log
#ADD ${JAR_FILE} app.jar
RUN pwd
RUN ls
EXPOSE 80
CMD cat build.log
#ENTRYPOINT ["java","-jar","app.jar"]