
package RestoApp.Controladores;

import RestoApp.repositorios.ReservaRepositorio;
import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.ReservaServicio;
import RestoApp.servicios.UsuarioServicio;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Reserva")
public class ReservaControlador {
    
    @Autowired
    private ReservaServicio reservaServicio;
    @Autowired
    private UsuarioServicio uS;
    
    @GetMapping("/crearReserva")
    public String crearReserva() {
        return "Reserva";
    }

    @PostMapping("/guardarReserva")
    public String guardarReserva(ModelMap model,@RequestParam  String nombre, @RequestParam Integer cantidad, @DateTimeFormat(pattern = "YYYY-dd-MM") Date dia) {
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
        return "redirect:/";
    }
    
    
       @GetMapping("/listarReservas/{id}")
    public String listarReservas(@PathVariable("id") String id,ModelMap model) {
        try {
            String nombre = uS.buscarPorId(id).getNombre();
            System.out.println(nombre);
            model.put("reservas", reservaServicio.buscarReservas(nombre));
        } catch (ErrorServicio ex) {
            Logger.getLogger(ReservaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return "listaReserva";
    }
    
    @GetMapping("/bajaReserva/{id}")
    public String bajaReserva(ModelMap model, @PathVariable("id")String id) {
        try {
            reservaServicio.bajaReserva(id);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            return "redirect:/Reserva/listarReservas/";
        }
        model.put("exito", "La reserva ha si dado de baja correctamente");
        return "redirect:/";
    }

}
