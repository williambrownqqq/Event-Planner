package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.domain.autentication.User;
import com.zanchenko.alex.diploma.domain.enumeration.Urgency;
import com.zanchenko.alex.diploma.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender emailSender;

    private final TemplateEngine templateEngine;

    private final UserRepository userRepository;

    public void sendMessageHtml(String to, String subject, String templateName, Map<String, Object> templateModel) throws IOException {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            Context context = new Context();
            context.setVariables(templateModel);
            String emailContent = templateEngine.process(templateName, context);

            helper.setText(emailContent, true);

            emailSender.send(message);
        } catch (MessagingException e) {
            LOG.info("error info {}", e);
        }
    }

    public void sendEmail(Event event) throws IOException {
        if(event.getUrgency().equals(Urgency.CRITICAL)){
            List<User> users  = userRepository.findAll().stream().toList();
            for (User user : users) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("username", user.getUsername());
                attributes.put("event", event);
                //attributes.put("activationCode", "/registration/activate/" + user.getActivationCode());
                sendMessageHtml(user.getEmail(), "CRITICAL Event #" + event.getId(), "mailtemplate.html", attributes);
            }
        }
    }
}