# Getting Started

#### Build application without dockerfile-maven-plugin

- `mvn -DskipTests -Ddockerfile.skip=true clean install` 
- `docker build --no-cache -t reactive-service:0.0.1-SNAPSHOT  .`
- `docker run -itd -p 80:80 --name reactive-service reactive-service:0.0.1-SNAPSHOT`
- `docker logs --tail 0 -f reactive-service`

#### Build application with dockerfile-maven-plugin

- `mvn clean install`
- `mvn dockerfile:build`
- `mvn dockerfile:deploy`

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
- `docker start reactive-service`
- `docker kill reactive-service`
- `docker rm reactive-service`
- `docker rmi reactive-service:0.0.1-SNAPSHOT`
- `docker network ls`
- `docker volume ls`
- `docker logs --tail 0 -f reactive-service`



