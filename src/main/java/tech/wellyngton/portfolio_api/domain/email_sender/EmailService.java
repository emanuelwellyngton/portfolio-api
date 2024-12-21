package tech.wellyngton.portfolio_api.domain.email_sender;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendVerificationCodeEmail(String email, String code) {
        try {
            var message = sender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreplay@codenestsolucoes.xyz");
            helper.setTo(email);
            helper.setSubject("Confirmação de e-mail");
            var mailText = getEmailTemplate().replace("{#verification_code}", code);
            helper.setText(mailText, true);
            sender.send(message);
        } catch (MessagingException | IOException e) {
            //TODO Criar exceção para falha no envio do email
            throw new RuntimeException(e);
        }
    }

    public String getEmailTemplate() throws IOException {
        var classPathResource = new ClassPathResource("email_template.html");
        return new String(classPathResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

}
