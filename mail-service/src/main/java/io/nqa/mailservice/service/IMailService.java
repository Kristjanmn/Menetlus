package io.nqa.mailservice.service;

public interface IMailService {
    boolean send(String to, String subject, String text);
    boolean sendMenetlusEmail(String to);
}
