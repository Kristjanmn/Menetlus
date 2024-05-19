package io.nqa.menetlus.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nqa.menetlus.model.MqEmailPayload;
import io.nqa.menetlus.service.MenetlusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Receiver {
    private final MenetlusService menetlusService;
    private final ObjectMapper mapper;

    public void receiveMessage(byte[] in) {
        try {
            MqEmailPayload payload = mapper.readValue(in, MqEmailPayload.class);
            menetlusService.setEmailDelivered(payload.getId(), payload.isSent());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
