package Email;

import javax.net.ssl.*;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {
    private List<Observer> observers = new ArrayList<>();

    private final String username = "ifpbtestemonteiro@gmail.com"; // Substitua pelo seu e-mail
    private final String password = "wpfh fghx bplv cpvz"; // Substitua pela sua senha

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void enviarEmail(String senha, String remetente, String destinatario, String msg, String assunto) {
        Properties prop = new Properties();
        prop.put("mail.smtp.ssl.trust", "*");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);
            message.setText(msg);

            Transport.send(message);
            notifyObservers("Email enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            notifyObservers("Falha ao enviar e-mail: " + e.getMessage());
        }
    }

}

   