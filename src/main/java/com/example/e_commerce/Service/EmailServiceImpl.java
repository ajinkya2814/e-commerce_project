package com.example.e_commerce.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender mailSender;


    public void sendWelcomeEmail(String to,String name,String lastname){

        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Welcome To Ecommerce");

        message.setText(
                """
                        Hello %S %S,
                        Welcome to our ecommerce application.
                        Your account has been created successfully.
                        Thank you for registering.
                        
                        Regards,
                        Ecommerce Team
                        """.formatted(name,lastname)
        );
        mailSender.send(message);
    }
}