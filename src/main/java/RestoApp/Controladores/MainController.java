    package RestoApp.Controladores;

import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.ReservaServicio;
import RestoApp.servicios.UsuarioServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    ReservaServicio reservaServicio;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model) { //con required false yo indico que no necesariamente va haber un parametro de error, solo hay errod cuando ingreso mal los datos, sino ese paramentro esta vacio
        if (error != null) {
            model.put(error, "Usuario o clave incorrectos");
        }
        return "login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave) {
        try {
            usuarioServicio.registrar(nombre, apellido, mail, clave);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("clave", clave);
            return "registroUsuario.html";
        }
        modelo.put("exito", "El usuario fue ingresado exitosamente");
        return "registroUsuario.html";
    }

    @GetMapping("/Reserva")
    public String Reserva() {

        return "Reserva";
    }

    @PostMapping("/guardarReserva")
    public String guardarReserva(ModelMap model, @RequestParam String nombre, @RequestParam Integer cantidad, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dia) {
        try {
            reservaServicio.guardarReserva(nombre, cantidad, dia);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("cantidad", cantidad);
            model.put("dia", dia);
            return "Reserva";
        }
        model.put("exito", "Se realizo su reserva exitosamente");
        return "Reserva";
    }

    @GetMapping("/restoOpciones")
    /*En este get se colocan la lista de opciones que tendrán los dueños de los restos para cargar su información*/
    
    public String restoOpciones() {
        return "restoOpciones";
    }
}
