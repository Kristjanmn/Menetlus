package io.nqa.menetlus.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {
    private final ApplicationProperties properties;

    @Bean
    Queue queue() {
        return new Queue(properties.getRabbitMq().getQueueName(), properties.getRabbitMq().isDurable());
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(properties.getRabbitMq().getTopicExchangeName());
    }

    @Bean
    HeadersExchange headers() {
        return new HeadersExchange(properties.getRabbitMq().getHeaderExchangeName());
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(properties.getRabbitMq().getDirectExchangeName());
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(properties.getRabbitMq().getFanoutExchangeName());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getRabbitMq().getRoutingKey());
    }

    @Bean
    Binding bindingHeader(Queue queue, HeadersExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).where(properties.getRabbitMq().getHeaderArgument()).exists();
    }

    @Bean
    Binding directExchangeBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getRabbitMq().getDirectRoutingKey());
    }

    @Bean
    Binding bindingFanout(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
}
