package tcucl.back_tcucl.service;


public interface EmailService {

    void sendSimpleEmail(String name,String nom, String to, String token);

    void sendPasswordResetEmail(String prenom, String nom, String to, String resetLink);
//    void sendMime(String name, String to, String token);
//    void sendSimpleEmail(String name, String to, String token);
//    void sendSimpleEmail(String name, String to, String token);
//    void sendSimpleEmail(String name, String to, String token);
}
