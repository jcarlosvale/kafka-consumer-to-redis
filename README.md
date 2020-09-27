# Data Collector from REST to Kafka - A Kafka Producer
by João Carlos (https://www.linkedin.com/in/joaocarlosvale/)

## Description:

It is a microservice (Kafka consumer) that:
1. read message from the queue (KAFKA)
2. calculate social rating score (score = base seed * user’s age)
3. print similar to the following message to the application console
"<firstName> <lastName> has <socialRatingScore> score"
4. stores the information in a REDIS memcache

Input:
```json
{
"first_name": <text>,
"last_name": <text>,
"age": <number>
}
```

## Technologies used:
* Java 11
* Spring
* Maven 
* Kafka
* Redis

##Setting up Kafka's Docker
The files used by me you can find inside the docker folder in the project.

    #starts the container
    docker-compose -f docker-compose.yml up -d
    
    #verify running
    docker-compose ps
    
    #stop the cluster
    docker-compose down
    
    #acess the container kafka
    docker exec -it kafka /bin/sh
    
    #acess the container REDIS
    docker exec -it redis redis-cli

Commands inside the container - Examples (Kafka / Redis)
    
    #KAFKA
    /opt/kafka_2.13-2.6.0 # ./bin/kafka-topics.sh --zookeeper zookeper:2181 --list
    
    keys *  #REDIS

To connect:
    
    docker run --rm -v /var/run/docker.sock:/var/run/docker.sock -e HOST_IP=127.0.0.1 -i -t wurstmeister/kafka /bin/bash

## Commands:

To generate JAR:

    mvn clean package

To run:

    java -jar target/consumer-0.0.1-SNAPSHOT.jar
    
To run tests:

    mvn test
