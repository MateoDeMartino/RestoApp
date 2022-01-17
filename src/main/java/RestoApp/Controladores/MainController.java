
package RestoApp.Controladores;

import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    
    @Autowired
    UsuarioServicio usuarioServicio;
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model) { //con required false yo indico que no necesariamente va haber un parametro de error, solo hay errod cuando ingreso mal los datos, sino ese paramentro esta vacio
        if (error != null) {
            model.put(error, "Usuario o clave incorrectos");
        }
        return "login.html";
    }
    
    @GetMapping("/registro")
    public String registro() {
        return "registro.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo ,@RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave) {
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
        return "index.html";
    }
    
}
