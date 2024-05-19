package io.nqa.mailservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private RabbitMq rabbitMq;
    private Mail mail;

    @Data
    public static class Mail {
        private boolean disabled;
        private String host;
        private int port;
        private String username;
        private String password;
        private Map<String, String> properties;
        private String signature;
        private String defaultSubject;
        private String defaultText;
    }

    @Data
    public static class RabbitMq {
        private boolean durable;
        private String queueName;
        private String exchangeName;
        private String routingKey;
    }
}
