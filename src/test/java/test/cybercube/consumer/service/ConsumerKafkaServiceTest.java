package test.cybercube.consumer.service;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.KafkaContainer;
import test.cybercube.consumer.dto.PeopleDTO;
import test.cybercube.consumer.repository.PeopleDTORepository;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Log4j2
class ConsumerKafkaServiceTest {

    @MockBean
    PeopleDTORepository repository;

    static KafkaContainer kafkaContainer = new KafkaContainer();
    static final String TOPIC = "any-topic";

    @BeforeAll
    public static void setUp() {
        kafkaContainer.start();
        System.setProperty("consumer.bootstrap-servers", kafkaContainer.getBootstrapServers());
        System.setProperty("consumer.topic", TOPIC);
    }

    @Test
    public void consumerReceiveFromKafkaTest() {
        PeopleDTO peopleDTO = PeopleDTO.builder().firstName("someFirstName").lastName("someSecondName").age(30).baseSeed(0.5).build();
        Producer<String, PeopleDTO> producer = createProducer();
        ProducerRecord<String, PeopleDTO> record = new ProducerRecord<>(TOPIC, peopleDTO);
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                log.debug(metadata.topic() + " : " + metadata.offset());
            }
        });
        ArgumentCaptor<PeopleDTO> argumentCaptor = ArgumentCaptor.forClass(PeopleDTO.class);
        verify(repository, timeout(10_000)).save(argumentCaptor.capture());
        PeopleDTO capturedPeopleDto = argumentCaptor.getValue();
        Assertions.assertNotNull(capturedPeopleDto.getId());
        peopleDTO.setId(capturedPeopleDto.getId());
        peopleDTO.setSocialRatingScore(15D);
        Assertions.assertEquals(peopleDTO, capturedPeopleDto);
    }

    private Producer<String, PeopleDTO> createProducer() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
        return new DefaultKafkaProducerFactory<String, PeopleDTO>(props, new StringSerializer(), new JsonSerializer<>()).createProducer();
    }

}
