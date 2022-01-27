
package RestoApp.Controladores;

import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.ReservaServicio;
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
@RequestMapping("/Reserva")
public class ReservaControlador {
    
    @Autowired
    private ReservaServicio reservaServicio;
    
    @GetMapping("/crearReserva")
    public String crearReserva() {
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
    
}
