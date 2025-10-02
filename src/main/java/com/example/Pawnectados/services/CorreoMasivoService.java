package com.example.Pawnectados.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorreoMasivoService {

    private static final Logger logger = LoggerFactory.getLogger(CorreoMasivoService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String remitente;

    /**
     * Envía correo masivo (HTML permitido).
     * No lanza excepción para fallos individuales; los registra y continúa.
     */
    public void enviarMasivo(List<String> destinatarios, String asunto, String mensajeHtml) {
        if (destinatarios == null || destinatarios.isEmpty()) {
            logger.warn("No hay destinatarios para enviar correos masivos.");
            return;
        }
        if (remitente == null || remitente.isBlank()) {
            logger.warn("Remitente (spring.mail.username) no configurado. Abortando envío.");
            return;
        }

        for (String correo : destinatarios) {
            if (correo == null || correo.isBlank()) {
                continue;
            }
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                helper.setTo(correo);
                helper.setSubject(asunto);
                helper.setText(mensajeHtml, true); // true = HTML
                helper.setFrom(remitente); // usa la cuenta definida en properties

                mailSender.send(mimeMessage);
                logger.info("Enviado correo a {}", correo);
            } catch (MessagingException ex) {
                // registra el error y sigue con el siguiente destinatario
                logger.error("Error enviando correo a {}: {}", correo, ex.getMessage());
            } catch (Exception ex) {
                logger.error("Error inesperado al enviar a {}: {}", correo, ex.getMessage(), ex);
            }
        }
    }
}
