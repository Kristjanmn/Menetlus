package io.nqa.mailservice.service;

import io.nqa.mailservice.configuration.ApplicationProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService implements IMailService {
    private final ApplicationProperties properties;
    private final JavaMailSender mailSender;

    @Override
    public boolean send(String to, String subject, String text) {
        if (properties.getMail().isDisabled())
            return true;
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setFrom(properties.getMail().getUsername());
            helper.setSubject(subject);
            helper.setText(text + properties.getMail().getSignature());
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendMenetlusEmail(String to) {
        return this.send(to, properties.getMail().getDefaultSubject(), properties.getMail().getDefaultText());
    }
}
