package tcucl.back_tcucl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import tcucl.back_tcucl.service.EmailService;

import static tcucl.back_tcucl.Constante.*;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${app.mail.provider:internal}")
    private String provider;

    @Value("${app.mail.fallback-to-gmail:true}")
    private boolean fallbackToGmail;

    // Internal relay
    @Value("${app.mail.internal.host:10.240.0.17}")
    private String internalHost;

    @Value("${app.mail.internal.port:25}")
    private int internalPort;

    @Value("${app.mail.internal.localpart:TCUCL-no-reply}")
    private String internalLocalPart;

    // Gmail
    @Value("${app.mail.gmail.host:smtp.gmail.com}")
    private String gmailHost;

    @Value("${app.mail.gmail.port:587}")
    private int gmailPort;

    @Value("${app.mail.gmail.username:}")
    private String gmailUsername;

    @Value("${app.mail.gmail.password:}")
    private String gmailPassword;

    @Value("${app.mail.gmail.from:}")
    private String gmailFrom;

    @Value("${app.mail.gmail.starttls:true}")
    private boolean gmailStarttls;

    @Value("${app.mail.gmail.auth:true}")
    private boolean gmailAuth;

    private static final String INTERNAL_DOMAIN = "@univ-catholille.fr";

    public EmailServiceImpl() {
        // No injected JavaMailSender needed; we build providers on demand
    }

    @Override
    public void sendSimpleEmail(String prenom, String nom, String to, String mdp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(MAIL_SUJET_INSCRIPTION_DEBUT);
            message.setTo(to);
            message.setText(ecrireMail(prenom, nom, mdp));
            sendWithFallback(message);
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
            message.setTo(to);
            message.setText(ecrireMailReset(prenom, nom, resetLink));
            sendWithFallback(message);
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

    private void sendWithFallback(SimpleMailMessage message) {
        String normalizedProvider = provider != null ? provider.trim().toLowerCase() : "internal";
        if ("internal".equals(normalizedProvider)) {
            try {
                message.setFrom(resolveInternalFromAddress());
                internalSender().send(message);
                return;
            } catch (Exception e) {
                logger.error("Echec envoi via relais interne, fallback gmail ? {} - {}", fallbackToGmail, e.getMessage());
                if (!fallbackToGmail) {
                    throw e;
                }
            }
        }

        if (!isGmailConfigured()) {
            throw new IllegalStateException("Configuration Gmail incomplète pour l'envoi/fallback");
        }
        message.setFrom(resolveGmailFromAddress());
        gmailSender().send(message);
    }

    private JavaMailSender internalSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(internalHost);
        sender.setPort(internalPort);
        sender.getJavaMailProperties().put("mail.smtp.auth", "false");
        sender.getJavaMailProperties().put("mail.smtp.starttls.enable", "false");
        sender.getJavaMailProperties().put("mail.smtp.starttls.required", "false");
        sender.getJavaMailProperties().put("mail.smtp.connectiontimeout", "10000");
        sender.getJavaMailProperties().put("mail.smtp.writetimeout", "10000");
        return sender;
    }

    private JavaMailSender gmailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(gmailHost);
        sender.setPort(gmailPort);
        sender.setUsername(gmailUsername);
        sender.setPassword(gmailPassword);
        sender.getJavaMailProperties().put("mail.smtp.auth", Boolean.toString(gmailAuth));
        sender.getJavaMailProperties().put("mail.smtp.starttls.enable", Boolean.toString(gmailStarttls));
        sender.getJavaMailProperties().put("mail.smtp.starttls.required", Boolean.toString(gmailStarttls));
        sender.getJavaMailProperties().put("mail.smtp.connectiontimeout", "10000");
        sender.getJavaMailProperties().put("mail.smtp.writetimeout", "10000");
        sender.getJavaMailProperties().put("mail.smtp.ssl.trust", "*");
        return sender;
    }

    private boolean isGmailConfigured() {
        return gmailHost != null && !gmailHost.isBlank()
                && gmailUsername != null && !gmailUsername.isBlank()
                && gmailPassword != null && !gmailPassword.isBlank();
    }

    private String resolveInternalFromAddress() {
        String localPart = (internalLocalPart != null && !internalLocalPart.isBlank()) ? internalLocalPart.trim() : "TCUCL-no-reply";
        return localPart + INTERNAL_DOMAIN;
    }

    private String resolveGmailFromAddress() {
        if (gmailFrom != null && !gmailFrom.isBlank()) {
            return gmailFrom;
        }
        if (gmailUsername != null && !gmailUsername.isBlank()) {
            return gmailUsername;
        }
        throw new IllegalStateException("Aucune adresse expéditeur configurée pour Gmail");
    }


}

