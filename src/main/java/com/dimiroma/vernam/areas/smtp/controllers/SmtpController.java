package com.dimiroma.vernam.areas.smtp.controllers;

import com.dimiroma.vernam.areas.smtp.models.Comment;
import com.dimiroma.vernam.areas.smtp.services.SmtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class SmtpController {
    private final SmtpService smtpService;

    @Autowired
    public SmtpController(SmtpService smtpService) {
        this.smtpService = smtpService;
    }

    @PostMapping("/receive-comment")
    public String sendMail(final @RequestBody Comment comment) throws MessagingException {
        String to = "invoice.dimiroma@gmail.com";
        String subject = "Comment from " + comment.getEmail();
        String message = comment.getMessage();
        String body = comment.getName() + " made a comment with message: " + message;
        this.smtpService.send(to, subject, body);
        return "{\"result: \" : \"Comment Sent!\"}";
    }
}
