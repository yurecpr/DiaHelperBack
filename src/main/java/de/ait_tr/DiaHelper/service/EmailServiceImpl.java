package de.ait_tr.DiaHelper.service;

import de.ait_tr.DiaHelper.domain.entity.User;
import de.ait_tr.DiaHelper.service.interfaces.ConfirmationService;
import de.ait_tr.DiaHelper.service.interfaces.EmailService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender sender;
    private Configuration mailConfiguration;
    private ConfirmationService confirmationService;

    public EmailServiceImpl(JavaMailSender sender, Configuration mailConfiguration, ConfirmationService confirmationService) {
        this.sender = sender;
        this.mailConfiguration = mailConfiguration;
        this.confirmationService = confirmationService;

        mailConfiguration.setDefaultEncoding("UTF-8");
        mailConfiguration.setTemplateLoader(new ClassTemplateLoader(EmailServiceImpl.class, "/mail/"));
    }

    @Override
    public void sendConfirmationEmail(User user, String password) {

        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String text = generateMessageText(user, password);


        try {
            helper.setFrom("svitlanajpaitstudent@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Registration");
            helper.setText(text, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sender.send(message);
    }

    @Override
    public void sendUpdateToPassword(User user, String password) {
        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String text = generateMessageNewPasswordText(user, password);


        try {
            helper.setFrom("svitlanajpaitstudent@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("New password");
            helper.setText(text, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sender.send(message);

    }

    private String generateMessageText(User user, String password) {
        try {
            Template template = mailConfiguration.getTemplate("confirm_registration_mail.ftlh");

            String code = confirmationService.generateConfirmationCode(user);

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("password", password);
//            model.put("link", "http://localhost:8080/register?code=" + code);


            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateMessageNewPasswordText(User user, String password) {
        try {
            Template template = mailConfiguration.getTemplate("update_password_mail.ftlh");

            String code = confirmationService.generateConfirmationCode(user);

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("password", password);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
