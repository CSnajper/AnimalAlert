package spring.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.DeprecatedOngoingStubbing;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by Konrad on 09.06.2016.
 */
public class MailServiceTest {
    private SpringTemplateEngine thymeleaf;
    private JavaMailSender javaMailSender;
    private MailService mailService;

    @Before
    public void setUp() throws Exception {
        mailService = new MailService();
        thymeleaf = mock(SpringTemplateEngine.class);
        javaMailSender = mock(JavaMailSender.class);
        mailService.setMailSender(javaMailSender);
        mailService.setThymeleaf(thymeleaf);

    }

    @Test
    public void createMimeMessage() {
        //given
        Session stub = Session.getDefaultInstance(new Properties());
        when(
                javaMailSender.createMimeMessage()

        ).thenReturn(new MimeMessage(stub));
        //when
        mailService.sendEmail("to@mail.com", "Temat", "zawartosc", false, true);
        //then
        verify(javaMailSender, times(1)).send(Matchers.any(MimeMessage.class));

    }

    @Test(expected = RuntimeException.class)
    public void throwsExceptionWhenSendMailFails() {
        Session stub = Session.getDefaultInstance(new Properties());
        when(
                javaMailSender.createMimeMessage()
        ).thenReturn(new MimeMessage(stub));

        doThrow(MailSendException.class).
        when(
                javaMailSender).send(Matchers.any(MimeMessage.class));
        mailService.sendEmail("to@mail.com", "Temat", "zawartosc", false, true);

    }
}