package io.nqa.menetlus.service;

public interface IRabbitMqService {
    void sendViaTopicExchange(String message);
    void sendViaHeadersExchange(String message);
    void sendViaDirectExchange(String message);
    void sendViaFanoutExchange(String message);
}
