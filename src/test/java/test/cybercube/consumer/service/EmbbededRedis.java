package test.cybercube.consumer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.embedded.RedisServer;

@Service
public class EmbbededRedis {

    private final RedisServer redisServer;

    public EmbbededRedis(@Value("${spring.redis.port}") final int redisPort) {
        this.redisServer = new redis.embedded.RedisServer(redisPort);
    }

    public void startRedis() {
        this.redisServer.start();
    }

    public void stopRedis() {
        this.redisServer.stop();
    }
}
