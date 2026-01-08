package tcucl.back_tcucl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tcucl.back_tcucl.service.EmailService;

import static tcucl.back_tcucl.Constante.*;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }

    @Override
    public void sendSimpleEmail(String prenom, String nom, String to, String mdp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(MAIL_SUJET_INSCRIPTION_DEBUT);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(ecrireMail(prenom, nom, mdp));
            emailSender.send(message);
            logger.info("Email envoyé à : " + to);
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi de l'email à : " + to + "\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void sendPasswordResetEmail(String prenom, String nom, String to, String resetLink) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(MAIL_SUJET_RESET_PASSWORD);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(ecrireMailReset(prenom, nom, resetLink));
            emailSender.send(message);
            logger.info("Email de réinitialisation envoyé à : " + to);
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi de l'email de réinitialisation à : " + to + "\n" + e.getMessage());
        }
    }


    private String ecrireMail(String prenom, String nom, String mdp){

        return MAIL_MESSAGE_INSCRIPTION_DEBUT + prenom + " " + nom + MAIL_MESSAGE_INSCRIPTION_MILIEU + mdp + MAIL_MESSAGE_INSCRIPTION_FIN;

    }

    private String ecrireMailReset(String prenom, String nom, String resetLink) {
        return MAIL_MESSAGE_RESET_PASSWORD_PREFIX + prenom + " " + nom + MAIL_MESSAGE_RESET_PASSWORD_BODY + resetLink + MAIL_MESSAGE_RESET_PASSWORD_SUFFIX;
    }


}

