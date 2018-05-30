package com.dimiroma.vernam.areas.smtp.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class SmtpServiceImpl implements SmtpService {
    private JavaMailSender mailSender;

    @Autowired
    public SmtpServiceImpl(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(final String to, final String subject, final String body) throws MessagingException {
        MimeMessage message = this.mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body, false);

        mailSender.send(message);
    }

    @Override
    public void send(final String to, final String subject, final String body, final InputStream stream) throws MessagingException, IOException {
        MimeMessage message = this.mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.addAttachment("policy_.pdf", new ByteArrayResource(IOUtils.toByteArray(stream)));
        helper.setText(body, false);

        mailSender.send(message);
    }

}
