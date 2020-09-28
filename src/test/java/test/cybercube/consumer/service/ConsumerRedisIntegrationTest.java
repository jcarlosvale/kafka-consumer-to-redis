package test.cybercube.consumer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import test.cybercube.consumer.dto.PeopleDTO;
import test.cybercube.consumer.repository.PeopleDTORepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ConsumerRedisIntegrationTest {

    @Autowired
    ConsumerKafkaService consumerKafkaService;

    @Autowired
    PeopleDTORepository peopleDTORepository;

    @Autowired
    EmbbededRedis redis;

    @Test
    public void receiveAndSaveTest() {
        redis.startRedis();
        PeopleDTO peopleDTO = PeopleDTO.builder().firstName("someFirstName").lastName("someSecondName").age(30).baseSeed(0.5).build();
        consumerKafkaService.receive(peopleDTO);
        Assertions.assertEquals(1, peopleDTORepository.count());
        Assertions.assertEquals(peopleDTO, peopleDTORepository.findById(peopleDTO.getId()).get());
        redis.stopRedis();
    }
}
