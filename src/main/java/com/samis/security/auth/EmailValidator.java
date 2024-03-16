package com.samis.security.auth;

import com.samis.emailServices.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class EmailValidator {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private  final EmailService emailService;

    @Autowired
    public EmailValidator(EmailService emailService) {
        this.emailService = emailService;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
//
    public String ValidateEmail(String email) throws MessagingException, jakarta.mail.MessagingException {
        if (isValidEmail(email)) {
            String subject = "SAMIS LIBRARY MANAGEMENT SYSTEM";
            String body = "<html><body><h1>Registered successfully</h1><p>The account will automatically be deleted if it is not activated within 14 days.</p><p>Contact <a href=\"mailto:samissystems@gmail.com\">samissystems@gmail.com</a> for more information.</p><p><i>If you have mistakenly received this email, please ignore and delete it.</i></p></body></html>";
            emailService.sendEmail(email,subject,body);
            return email;
        } else {
            throw new WrongEmail("Invalid email");
        }
    }
}
