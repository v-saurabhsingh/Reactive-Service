# Getting Started

#### Build application without dockerfile-maven-plugin

- `mvn -DskipTests -Ddockerfile.skip=true clean install` 
- `docker build --no-cache -t reactive-service:latest  .`
- `docker run -itd -p 80:80 --name reactive-service reactive-service:latest`
- `docker logs --tail 0 -f reactive-service`

#### Build application with dockerfile-maven-plugin

- `mvn clean install`
- `mvn dockerfile:build`
- `mvn dockerfile:tag`
- `mvn dockerfile:push`

#### Docker commands

- `docker ps`
- `docker images`
- `docker rm $(docker ps -a -q)`
- `docker rmi $(docker images -q)`
- `docker build --no-cache -t reactive-service --build-arg JAR_FILE=target/app.jar .`
- `docker run -itd -p 80:80 --name reactive-service reactive-service:latest`
- `docker attach reactive-service`
- `docker exec -it reactive-service bash`
- `docker stop reactive-service`
- `docker start reactive-service`
- `docker kill reactive-service`
- `docker rm reactive-service`
- `docker rmi reactive-service`
- `docker network ls`
- `docker volume ls`
- `docker logs --tail 0 -f reactive-service`

#### Docker HUB
- `docker login`
- `docker login --username="someuser" --password="somepass" --email="somemail@gmail.com"`
- `docker tag reactive-service:latest singhsaurabh920/reactive-service:1.0`
- `docker push singhsaurabh920/reactive-service:1.0`



