## miscroservices with quarkus 


### package poduct

```./mvnw package```


### run project product on docker

#### build image

```docker build -f src/main/docker/Dockerfile.jvm -t quarkus-microservices/products .```

``` docker run -d --name product -p 8081:8081 quarkus-microservices/products```