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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/zona")
public class ZonaControlador {

    @Autowired
    ZonaServicio zS = new ZonaServicio();

    List<Zona> zonas = new ArrayList<>();
    
    @PostMapping("/crearzona")
    public String crearZona(ModelMap model) {
        model.put("zonas", zS.listarZonas());
        return "zona";
    }

    @PostMapping("/guardarzona")
    public String guardarZona(ModelMap model, @RequestParam String nombre) {
        zS.crearZona(nombre);
        return "zona";
    }

    @GetMapping("/modzona")
    public String modZona(ModelMap model) {
        model.put("zonas", zS.listarZonas());
        return "zonamod";
    }

    @PostMapping("/modifizona")
    public String modifiZona(@RequestParam String id, @RequestParam String nombre) throws ErrorServicio {
        zS.modificarZona(id, nombre);
        return "restoOpciones";
    }

    @PostMapping("/eliminarzona")
    public String eliminarZona(@RequestParam String id) throws ErrorServicio {
        zS.deshabilitarZona(id);
        return "restoOpciones";
    }

    @GetMapping("/lista")
    public String listarZona(ModelMap model) {
        model.put("zonas", zS.listarZonas());
        return "zona";
    }

}
