# Getting Started

#### Build application without dockerfile-maven-plugin
- `mvn clean install`
- `mvn clean install -DskipTest`
- `docker build --no-cache -t reactive-service:0.0.1-SNAPSHOT  .`
- `docker run -itd -p 80:80 --name reactive-service reactive-service:0.0.1-SNAPSHOT`

#### Build application without dockerfile-maven-plugin
- `mvn clean install`
- `mvn docker:build`

#### Docker commands

- `docker ps`
- `docker images`
- `docker rm $(docker ps -a -q)`
- `docker rmi $(docker images -q)`
- `docker build --no-cache -t reactive-service:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/app.jar .`
- `docker run -itd -p 80:80 --name reactive-service reactive-service:0.0.1-SNAPSHOT`
- `docker attach reactive-service`
- `docker exec -it reactive-service bash`
- `docker stop reactive-service`
- `docker kill reactive-service`
- `docker rm reactive-service`
- `docker rmi reactive-service:0.0.1-SNAPSHOT`


