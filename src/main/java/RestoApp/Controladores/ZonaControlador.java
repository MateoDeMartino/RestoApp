package RestoApp.Controladores;

import RestoApp.Entidades.Zona;
import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.ZonaServicio;
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

@Controller
@RequestMapping("/zona")
public class ZonaControlador {

    @Autowired
    ZonaServicio zS = new ZonaServicio();

    List<Zona> zonas = new ArrayList<>();

    @GetMapping("/crearzona")
    public String crearZona(ModelMap model) {
        
        return "zona";
    }

    @PostMapping("/guardarzona")
    public String guardarZona(ModelMap model, @RequestParam String nombre) {
        zS.crearZona(nombre);
        return "zona";
    }

    @GetMapping("/lista")
    public String listarZona(ModelMap model) {
        model.put("zonas", zS.listarZonas());
        return "listaZona";
    }

    @GetMapping("/modzona/{id}")
    public String modZona(@PathVariable("id") String id, ModelMap model) {
        try {
            Zona zona = zS.buscarZonaId(id);
            model.put("zona", zona);
            } catch (ErrorServicio e) {
            model.put("error", e.getMessage());
        }
        return "zonamod";
    }

    @PostMapping("/modifizona")
    public String modifiZona(ModelMap modelo, @RequestParam String id, @RequestParam String nombre) throws ErrorServicio {
        try {
            zS.modificarZona(id, nombre);
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            return "zonamod";
        }
        return "redirect:/zona/lista";
    }

    @GetMapping("/eliminarzona/{id}")
    public String eliminarZona(@PathVariable("id") String id,ModelMap model)  {
        try{ 
        zS.deshabilitarZona(id);
        }catch(ErrorServicio e){
         model.put("error",e); 
         return "redirect:/zona/lista";
        }
        model.put("exito","La zona fue dada de baja con éxito");
        return "redirect:/zona/lista";
    }

}
