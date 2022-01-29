package RestoApp.Controladores;

import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.ReservaServicio;
import RestoApp.servicios.RestauranteServicio;
import RestoApp.servicios.UsuarioServicio;
import RestoApp.servicios.ZonaServicio;
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

    @Autowired
    RestauranteServicio restauranteServicio;

    @Autowired
    ZonaServicio zS;

    @GetMapping("/")
    public String index(ModelMap model) {
        model.put("restos", restauranteServicio.listaraRestaurantes());
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
            return "registro";
        }
        modelo.put("exito", "El usuario fue ingresado exitosamente");
        return "registro";
    }

    @GetMapping("/restoOpciones")
    /*En este get se colocan la lista de opciones que tendrán los dueños de los restos para cargar su información*/

    public String restoOpciones() {
        return "restoOpciones";
    }

    @GetMapping("/zona")
    public String zona() {
        return "zona";
    }

}
