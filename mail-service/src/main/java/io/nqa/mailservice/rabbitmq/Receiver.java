package io.nqa.mailservice.rabbitmq;

import io.nqa.mailservice.configuration.ApplicationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nqa.mailservice.model.MqEmailPayload;
import io.nqa.mailservice.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Receiver {
    private final ApplicationProperties properties;
    private final RabbitTemplate template;
    private final MailService mailService;
    private final ObjectMapper mapper = new ObjectMapper();

    public void receiveMessage(byte[] in) {
        try {
            MqEmailPayload payload = mapper.readValue(in, MqEmailPayload.class);
            if (payload.isSent()) return;
            payload.setSent(mailService.sendMenetlusEmail(payload.getEmail()));
            String json = mapper.writeValueAsString(payload);
            Message message = MessageBuilder.withBody(json.getBytes()).build();
            template.convertAndSend(properties.getRabbitMq().getExchangeName(), properties.getRabbitMq().getRoutingKey(), message);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
