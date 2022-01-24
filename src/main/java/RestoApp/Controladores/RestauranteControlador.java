package RestoApp.Controladores;

import RestoApp.Entidades.Menu;
import RestoApp.Entidades.Zona;
import RestoApp.servicios.RestauranteServicio;
import RestoApp.servicios.ZonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Restaurante")
public class RestauranteControlador {

    @Autowired
    private RestauranteServicio restauranteServicio;

    @Autowired
    private ZonaServicio zS;

//    @GetMapping("/Restaurante")
//    public String index() {
//        return "Restaurante";
//    }
    
//    @PostMapping("/Restaurante/guardarRestaurante")
//    public String guardarRestaurante(String nombre, Menu menu, Integer mesas, String zona, Boolean abierto) {
//
//        try {
//            restauranteServicio.guardarRestaurante(nombre, menu, mesas, zona, abierto);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return "index";
//    }

    @PostMapping("/modificarRestaurante")
    public String modificarRestaurante(String Id, @RequestParam String nombre, @RequestParam Menu menu, @RequestParam Integer mesas, @RequestParam Zona zona, @RequestParam Boolean abierto) {

        try {
            restauranteServicio.modificarRestaurante(nombre, menu, mesas, zona, abierto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "index";//cambiar index
    }

    @GetMapping("/buscarRestaurante")
    public String buscarRestaurante(String nombre) {

        try {
            restauranteServicio.buscarRestaurante(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "index";//cambiar index

    }

    @GetMapping("/mostarTodos")
    public String mostrarTodos() {

        try {
            restauranteServicio.listaraRestaurantes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "index";//cambiar index
    }

    @PostMapping("/eliminarRestaurante")
    public String eliminarRestaurante(String Id) {

        try {
            restauranteServicio.eliminarRestaurante(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "index";//cambiar index
    }

//    @PostMapping("/modificarMenu")
//    public String modificarMenu(){
//        
//        
//        return"index";//cambiar index 
//    }
//    @PostMapping("/eliminarMenu")
//    public String eliminarMenu(String Id){
//        
//        restauranteServicio.eliminarMenu(Id);
//        
//        return "index";
//    }
}
