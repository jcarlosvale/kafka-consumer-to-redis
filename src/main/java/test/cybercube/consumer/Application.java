package test.cybercube.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import test.cybercube.consumer.configuration.ConsumerProperties;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties(ConsumerProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
