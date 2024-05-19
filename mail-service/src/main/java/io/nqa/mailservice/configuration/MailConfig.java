package io.nqa.mailservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfig {
    private final ApplicationProperties applicationProperties;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(applicationProperties.getMail().getHost());
        mailSender.setPort(applicationProperties.getMail().getPort());
        mailSender.setUsername(applicationProperties.getMail().getUsername());
        mailSender.setPassword(applicationProperties.getMail().getPassword());

        Properties properties = mailSender.getJavaMailProperties();
        properties.putAll(applicationProperties.getMail().getProperties());
        return mailSender;
    }
}
