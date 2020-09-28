package test.cybercube.consumer.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import test.cybercube.consumer.dto.PeopleDTO;

import java.util.HashMap;
import java.util.Map;

@EnableConfigurationProperties(ConsumerProperties.class)
@Configuration
public class ConsumerKafkaConfig {

    @Bean
    public Map<String, Object> consumerConfigs(ConsumerProperties consumerProperties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerProperties.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerProperties.getGroupId());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerProperties.getAutoOffsetReset());
        return props;
    }

    @Bean
    public ConsumerFactory<String, PeopleDTO> consumerFactory(ConsumerProperties consumerProperties) {
        JsonDeserializer<PeopleDTO> deserializer = new JsonDeserializer<>(PeopleDTO.class);
        deserializer.ignoreTypeHeaders();
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(consumerProperties),
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PeopleDTO> kafkaListenerContainerFactory(
            ConsumerFactory<String, PeopleDTO> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, PeopleDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
