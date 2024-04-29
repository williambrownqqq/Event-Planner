package com.zanchenko.alex.diploma.service;


//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;


//@Service
//@RequiredArgsConstructor
//public class MailService {
//
//    private final JavaMailSender mailSender;
//    private final SpringTemplateEngine thymeleafTemplateEngine;
//
//    @Value("${spring.mail.username}")
//    private String username;
//
//    @Value("${hostname}")
//    private String hostname;
//
//    public void sendMessageHtml(String to, String subject, String template, Map<String, Object> attributes) {
//        ExecutorService executor = Executors.newFixedThreadPool(10);
//        executor.execute(() -> {
//            attributes.put("url", "http://" + hostname);
//            Context thymeleafContext = new Context();
//            thymeleafContext.setVariables(attributes);
//            String htmlBody = thymeleafTemplateEngine.process("email/" + template, thymeleafContext);
//            MimeMessage message = mailSender.createMimeMessage();
//            try {
//                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//                helper.setFrom(username);
//                helper.setTo(to);
//                helper.setSubject(subject);
//                helper.setText(htmlBody, true);
//                mailSender.send(message);
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        executor.shutdown();
//    }
//}


import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.domain.autentication.User;
import com.zanchenko.alex.diploma.domain.enumeration.Urgency;
import com.zanchenko.alex.diploma.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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