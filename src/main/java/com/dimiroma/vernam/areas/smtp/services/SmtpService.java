package com.dimiroma.vernam.areas.smtp.services;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;

public interface SmtpService {
    void send(final String to, final String subject, final String body) throws MessagingException;

    void send(final String to, final String subject, final String body, final InputStream stream) throws MessagingException, IOException;
}
