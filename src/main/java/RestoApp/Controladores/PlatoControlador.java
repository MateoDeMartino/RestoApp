package RestoApp.Controladores;

import RestoApp.entidades.Plato;
import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.PlatoServicio;
import RestoApp.servicios.RestauranteServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/plato")
public class PlatoControlador {

    @Autowired
    private PlatoServicio pS;
    
    @Autowired
    private RestauranteServicio rS;

    List<Plato> platos = new ArrayList<>();

    @GetMapping("/crearplato")
    public String crearPlato(ModelMap modelo) {
        modelo.put("restos", rS.listaraRestaurantes());
        return "plato";
    }

    @PostMapping("/guardarplato")
    public String guardarPlato(ModelMap model, MultipartFile archivo, @RequestParam String nombre, @RequestParam Integer valor, @RequestParam String descripcion,String idresto) {
        try {
            pS.guardarPlato(archivo, nombre, valor, descripcion,idresto);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("valor", valor);
            model.put("descripcion", descripcion);
            //en el form poner th:value="${nombre.variable}" para conservar los datos llenados

            return "plato";
        }
        model.put("exito", "El plato fue ingresado exitosamente");
        return "plato";
    }

    @GetMapping("/listar")
    public String listarPlatos(ModelMap model) {

        model.put("platos", pS.listarPlatos());

        return "listaPlatos";
    }

    @GetMapping("/modplato/{id}")
    public String modPlato(@PathVariable("id") String id, ModelMap model) {
        Plato plato = pS.buscarPlatoId(id);
        model.put("id", id);
        model.put("nombre1", plato.getNombre());
        model.put("valor1", plato.getValor());
        model.put("descripcion1", plato.getDescripcion());

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
            return "modplato";
            //en el form poner th:value="${nombre.variable}" para conservar los datos l
        }
        model.put("exito", "El plato fue modificado con éxito");
        return "redirect:/plato/listar";
    }

    @GetMapping("/bajaplato/{id}")
    public String eliminarPlato(ModelMap model, @PathVariable("id")String id) {
        try {
            pS.bajaPlato(id);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            return "redirect:/plato/listar";
        }
        model.put("exito", "Plato dado de baja correctamente");
        return "redirect:/plato/listar";
    }
    
      @GetMapping("/eliminarplato/{id}")
    public String eliminarPlato(@PathVariable("id") String id,ModelMap model) {
        try {
           pS.eliminarPlatoId(id);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            return "redirect:/plato/listar";
        }
        model.put("exito", "Plato eliminado correctamente");
       return "redirect:/plato/listar";
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

}
