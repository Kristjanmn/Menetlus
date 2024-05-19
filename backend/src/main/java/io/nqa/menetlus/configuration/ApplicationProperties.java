package io.nqa.menetlus.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private RabbitMq rabbitMq;

    @Data
    public static class RabbitMq {
        private boolean durable;
        private String queueName;
        private String exchangeName;
        private String routingKey;
    }
}
