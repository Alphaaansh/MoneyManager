package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendActivationEmail(String toEmail,String activationToken){
        SimpleMailMessage message=new SimpleMailMessage();

        message.setFrom("alphaanshjha@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Money Manager - Account Activation");
        message.setText("Click the link below to activate your account:\n\n" +
                "http://localhost:8080/api/v1.0/activate?token=" + activationToken);

        mailSender.send(message);
    }
}
