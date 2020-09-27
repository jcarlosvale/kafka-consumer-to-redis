package test.cybercube.consumer.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import test.cybercube.consumer.dto.PeopleDTO;

@Service
@Log4j2
public class ConsumerKafkaService {

    @KafkaListener(topics = "${consumer.topic}")
    public void receive(@Payload PeopleDTO data,
                        @Headers MessageHeaders headers) {
        log.info("received data='{}'", data);
/*
        headers.keySet().forEach(key -> {
            log.info("{}: {}", key, headers.get(key));
        });*/
    }
}
