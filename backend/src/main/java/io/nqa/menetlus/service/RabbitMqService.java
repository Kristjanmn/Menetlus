package io.nqa.menetlus.service;

import io.nqa.menetlus.configuration.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqService implements IRabbitMqService {
    private final ApplicationProperties.RabbitMq properties;
    private final AmqpTemplate template;

    @Override
    public void sendViaTopicExchange(String message) {
        template.convertAndSend(properties.getTopicExchangeName(), "menetlus.test", message);
    }

    @Override
    public void sendViaHeadersExchange(String message) {
        Message sysErrMsg = MessageBuilder.withBody(message.getBytes()).setHeader("testId", "123")
                .setHeader("anotherId", "another123").build();
        template.convertAndSend(properties.getHeaderExchangeName(), properties.getHeaderRoutingKey(), sysErrMsg);
    }

    @Override
    public void sendViaDirectExchange(String message) {
        template.convertAndSend(properties.getDirectExchangeName(), properties.getDirectRoutingKey(), message);
    }

    @Override
    public void sendViaFanoutExchange(String message) {
        template.convertAndSend(properties.getFanoutExchangeName(), properties.getFanoutRoutingKey(), message);
    }
}
