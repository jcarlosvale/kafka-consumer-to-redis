package test.cybercube.consumer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("consumer")
@Data
public class ConsumerProperties {
    private String topic = "";
    private String bootstrapServers = "";
    private String groupId = "";
    private String autoOffsetReset = "earliest";
}
