package com.app.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.app")
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class AppBeansConfig {

    private final Environment environment;
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public Mailer mailer(){
        return MailerBuilder
                .withSMTPServer(
                        environment.getRequiredProperty("email.host"),
                        environment.getRequiredProperty("email.port", Integer.class),
                        environment.getRequiredProperty("email.username"),
                        environment.getRequiredProperty("email.password"))
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .async()
                .buildMailer();
    }
}
