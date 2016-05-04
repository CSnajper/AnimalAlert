package spring.service;


import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import spring.domain.User;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    private final Logger log = LoggerFactory.getLogger(MailService.class);

    @Inject
    JavaMailSender mailSender;

    @Inject
    SpringTemplateEngine thymeleaf;

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom("noreplay@domain.com");
            message.setSubject(subject);
            message.setText(content, isHtml);
            mailSender.send(mimeMessage);
            log.debug("Sent e-mail to User '{}'", to);
        } catch (Exception e) {
            log.warn("E-mail could not be sent to user '{}', exception is: {}", to, e.getMessage());
        }
    }

    @Async
    public void sendActivationEmail(String to, User user) {
        Context ctx = new Context();
        ctx.setVariable("username", user.getUsername());
        ctx.setVariable("message", "Witaj " + user.getUsername());

        String content = thymeleaf.process("activationEmail", ctx);
        String subject = "Witaj " + user.getUsername();
        sendEmail(user.getEmail(), subject, content, false, true);
    }

}
