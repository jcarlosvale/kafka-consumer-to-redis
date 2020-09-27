package test.cybercube.consumer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import test.cybercube.consumer.dto.PeopleDTO;

public class ConsumerJedisConfiguration {

    @Bean
    RedisConnectionFactory jedisConnectionFactory(ConsumerProperties consumerProperties) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                consumerProperties.getRedisHostname(),
                consumerProperties.getRedisPort());
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
        //return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    RedisTemplate<String, PeopleDTO> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, PeopleDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }
}
