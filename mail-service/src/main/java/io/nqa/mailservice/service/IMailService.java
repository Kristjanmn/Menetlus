package io.nqa.mailservice.service;

public interface IMailService {

    /**
     * Send an email.
     *
     * @param to Recipiant's e-mail address
     * @param subject E-mail subject
     * @param text E-mail content
     * @return Returns true if sending was successful
     * or if mail service is disabled in config
     */
    boolean send(String to, String subject, String text);

    /**
     * Send pre-made e-mail.
     *
     * @param to Recipiant's e-mail address
     * @return Returns true if sending was successful
     * or if mail service is disabled in config
     */
    boolean sendMenetlusEmail(String to);
}
