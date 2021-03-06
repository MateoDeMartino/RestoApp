
package RestoApp.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**@author Salo
 */

@Service
public class NotificacionServicio {
    
    @Autowired
    private JavaMailSender mailSender;
    
    //con Async indico que el hilo de ejecucion no espera a que se termine de enviar el mail
    //lo ejecuta en un hilo paralelo
    @Async 
    public void enviar(String cuerpo, String titulo, String mail){
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(mail);
        mensaje.setFrom("noreply@restoapp.com");
        mensaje.setSubject(titulo);
        mensaje.setText(cuerpo);
        
        mailSender.send(mensaje);
    }
}
