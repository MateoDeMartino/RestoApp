package RestoApp.Controladores;

import RestoApp.Entidades.Restaurante;
import RestoApp.Entidades.Zona;
import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.RestauranteServicio;
import RestoApp.servicios.ZonaServicio;
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
@RequestMapping("/Restaurante")
public class RestauranteControlador {

    @Autowired
    private RestauranteServicio restauranteServicio;

    @Autowired
    private ZonaServicio zS;

    @GetMapping("/crearResto")
    public String crearResto(ModelMap model) {
        model.put("zonas", zS.listarZonas());
        return "Restaurante";
    }

    @PostMapping("/guardarRestaurante")
    public String guardarRestaurante(ModelMap model,MultipartFile archivo,String nombre, Integer mesas, String zona, Boolean abierto) {

        try {
            restauranteServicio.guardarRestaurante(archivo,nombre, mesas, zona, abierto);
            model.put("exito", "El restaurante fue ingresado exitosamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return "Restaurante";
    }
    
    @GetMapping("/verPlatos/{id}")
    public String verPlatos(@PathVariable("id") String id, ModelMap model){        
        model.put("platos", restauranteServicio.listarPlatos(id));        
        return "resto1";
    }    

    @PostMapping("/modificarRestaurante")
    public String modificarRestaurante(String Id,MultipartFile archivo, @RequestParam String nombre, @RequestParam Integer mesas, @RequestParam Zona zona, @RequestParam Boolean abierto) {

        try {
            restauranteServicio.modificarRestaurante(archivo,nombre, mesas, zona, abierto);
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
