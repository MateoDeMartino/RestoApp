package RestoApp.Controladores;

import RestoApp.entidades.Plato;
import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.PlatoServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/plato")
public class PlatoControlador {

    @Autowired
    private PlatoServicio pS;

    List<Plato> platos = new ArrayList<>();

    @GetMapping("/pagplato")
    public String pagPlato() {
        return "";
    }

    @GetMapping("/crearplato")
    public String crearPlato(ModelMap model) {
        model.put("platos", pS.listarPlatos());
        return "plato";
    }

    @PostMapping("/guardarplato")
    public String guardarPlato(ModelMap model, MultipartFile archivo, @RequestParam String nombre, @RequestParam Integer valor, @RequestParam String descripcion) {
//<form action = "/guardarplato" method="POST" enctype="multipart/form-data">      
//adentro del form de html usar  <p th:if= "${error != null}" "th:text = ${error}"></p>
//para mostrar el error en pantalla
//Aca se podria poner a que restaurante pertenece el plato, pensaba para simplificarlo poner una lista desplegable tambien hay que modificar la entidad plato//
        try {
            pS.guardarPlato(archivo, nombre, valor, descripcion);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("valor", valor);
            model.put("descripcion", descripcion);
            //en el form poner th:value="${nombre.variable}" para conservar los datos llenados
            return "plato";
        }

        return "redirect:/plato/crearplato";
    }

    @GetMapping("/modplato")
    public String modPlato(ModelMap model) {
        model.put("platos", pS.listarPlatos());
        return "modplato";
    }

    @PostMapping("/modifiplato")
    public String modifiPlato(ModelMap model, MultipartFile archivo, @RequestParam String idPlato, @RequestParam String nombre, @RequestParam Integer valor, @RequestParam String descripcion) throws ErrorServicio {
        try {
            pS.modificarPlato(archivo, idPlato, nombre, valor, descripcion);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("valor", valor);
            model.put("descripcion", descripcion);
            //en el form poner th:value="${nombre.variable}" para conservar los datos llenados
            return "";
        }
        return "redirect:/plato/pagplato";
    }

    @PostMapping("/eliminarplato")
    public String eliminarPlato(ModelMap model, @RequestParam String idPlato) {
        try {
            pS.bajaPlato(idPlato);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            return "";
        }
        return "redirect:/plato/pagplato";
    }

    @PostMapping("/altaplato")
    public String altaPlato(ModelMap model, @RequestParam String idPlato) {
        try {
            pS.altaPlato(idPlato);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            return "";
        }
        return "redirect:/plato/pagplato";
    }

    @GetMapping("/lista")
    public String listarPlatos(ModelMap model) {
        model.put("platos", pS.listarPlatos());
        return "";
    }
}
