package com.evila.emailsender.email_sender.controllers;

import com.evila.emailsender.email_sender.User;
import com.evila.emailsender.email_sender.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLOutput;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final EmailService emailService;

    @Autowired
    public UserController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String inputForm() {
        logger.info("Handling '/' request");
        return "index";
    }

    @PostMapping("/sendemail")
    public String sendEmail(@ModelAttribute("user")User user) {
        logger.info("Handling /sendemail request");
        String email = user.getEmail();
        String name = user.getName();
        String body = "Hey there, " + name + "!.\nThanks so much for testing" +
                "the email sender project!";
        String subject = "THANK YOU, " + name.toUpperCase() + "!";

        logger.info("Sending email.");

        emailService.sendEmail(email, subject, body);

        return "redirect:/sendemail/thanks";
    }

    @GetMapping("/sendemail/thanks")
    public String thankYouPage() {
        return "thank-you-page";
    }
}
