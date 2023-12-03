package com.app.service.impl;

import com.app.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${email.from}")
    private String emailFrom;

    private final Mailer mailer;
    private static final Logger logger = LogManager.getRootLogger();
    @Override
    public void send(String to, String subject, String content) {
        var email = EmailBuilder
                .startingBlank()
                .withSubject(subject)
                .withHTMLText(content)
                .from(emailFrom)
                .to(to)
                .buildEmail();

        mailer
                .sendMail(email)
                .thenAccept(res -> logger.info("Email has been sent"))
                .exceptionally(ex -> {
                    logger.error("Email error: %s".formatted(ex.getMessage()));
                    return null;
                })
                .whenComplete((res, err) -> mailer.shutdownConnectionPool());
    }
}
