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
     
     @GetMapping("/crearzona")
    public String crearPlato(ModelMap model) {
        model.put("zonas", zS.listarZonas());
        return "zona";
    }
     @PostMapping("/guardarzona")
    public String guardarPlato(ModelMap model, @RequestParam String nombreZona) {
        zS.crearZona(nombreZona);
        return "restoOpciones";
    }
    
    @GetMapping("/modautor")
    public String modAutor(ModelMap model){
        model.put("zonas", zS.listarZonas());
        return "zonamod";
    }
    
    @PostMapping("/modifizona")
    public String modifiZona (@RequestParam String id, @RequestParam String nombreZona) throws ErrorServicio{
        zS.modificarZona(id, nombreZona);
        return "restoOpciones";
    }
    
    @PostMapping("/eliminarzona")
    public String eliminarZona(@RequestParam String id) throws ErrorServicio {
        zS.deshabilitarZona(id);
        return "restoOpciones";
    }

    @GetMapping("/lista")
    public String listarZona(ModelMap model){        
        model.put("zonas", zS.listarZonas());
        return "zona";
    }
    
}
