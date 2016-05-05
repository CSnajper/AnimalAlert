package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

@Configuration
//pakiet z kontrolerami
@ComponentScan("spring.controller")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private Environment env;

    //dodawanie plikow css (i innych)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    //komunikaty z pliku wlasciwosci
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    //przekierowanie zadan???
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*@Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mailserver.host"));
        mailSender.setPort(587);
        mailSender.setUsername(env.getProperty("mailserver.user"));
        mailSender.setPassword(env.getProperty("mailserver.password"));
        return mailSender;
    }*/

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("xxx@gmail.com");
        mailSender.setPassword("xxx");

        Properties props = new Properties();
        /*props.put("mail.transport.protocol", "smtp");
        props.put("mail.host", "smtp.gmail.com");
        props.put("mail.user", "kondzixd@gmail.com");
        props.put("mail.password", "c140b18M@IL");
        props.put("mail.port", "587");*/
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");


        mailSender.setJavaMailProperties(props);

        return mailSender;
    }

}
