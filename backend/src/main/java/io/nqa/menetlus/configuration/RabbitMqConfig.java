package io.nqa.menetlus.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {
    private final ApplicationProperties.RabbitMq properties;

    @Bean
    Queue queue() {
        return new Queue(properties.getQueueName(), properties.isDurable());
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(properties.getTopicExchangeName());
    }

    @Bean
    HeadersExchange headers() {
        return new HeadersExchange(properties.getHeaderExchangeName());
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(properties.getDirectExchangeName());
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(properties.getFanoutExchangeName());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getRoutingKey());
    }

    @Bean
    Binding bindingHeader(Queue queue, HeadersExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).where(properties.getHeaderArgument()).exists();
    }

    @Bean
    Binding directExchangeBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getDirectRoutingKey());
    }

    @Bean
    Binding bindingFanout(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
}
