package test.cybercube.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
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
    public PeopleDTO receive(@Payload PeopleDTO peopleDTO) {
        log.info("received data='{}'", peopleDTO);
        peopleDTO.setId(Long.toString(System.currentTimeMillis()));
        calculateScore(peopleDTO);
        log.info("{} {} has {} score", peopleDTO.getFirstName(), peopleDTO.getLastName(), peopleDTO.getSocialRatingScore());
        return repository.save(peopleDTO);
    }

    private void calculateScore(PeopleDTO peopleDTO) {
        peopleDTO.setSocialRatingScore(peopleDTO.getBaseSeed() * peopleDTO.getAge());
    }
}
