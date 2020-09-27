package test.cybercube.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import test.cybercube.consumer.dto.PeopleDTO;
import test.cybercube.consumer.repository.PeopleDTORepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConsumerKafkaService {

    private final PeopleDTORepository repository;

    @KafkaListener(topics = "${consumer.topic}")
    public void receive(@Payload PeopleDTO data,
                        @Headers MessageHeaders headers) {
        log.info("received data='{}'", data);
        data.setId(Long.toString(System.currentTimeMillis()));
        repository.save(data);
/*
        headers.keySet().forEach(key -> {
            log.info("{}: {}", key, headers.get(key));
        });*/
    }
}
