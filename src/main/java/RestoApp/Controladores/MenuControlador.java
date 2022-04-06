
package RestoApp.Controladores;


import RestoApp.Entidades.Menu;
import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.MenuServicio;
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
@RequestMapping("/Menu")
public class MenuControlador {
    
    
@Autowired   
private MenuServicio mS;

List<Menu> menu = new ArrayList<>();

@GetMapping("/pagmenu")
public String pagMenu(){
    return "";
}
    
@GetMapping("/crearmenu")
public String crearMenu(ModelMap model){
    model.put("menus" , mS.listarMenu());
    return "";
}
@PostMapping("/guardarmenu")
public String guardarMenu(ModelMap model , @RequestParam String nombreResto , @RequestParam String id , @RequestParam Boolean alta , @RequestParam String descripcion){
    try {
        mS.guardarMenu(id, descripcion, alta, nombreResto);
        
    }catch (ErrorServicio ex) {
        model.put("error", ex.getMessage());
        model.put("nombre", nombreResto);
        model.put("id", id);
        model.put("alta", alta);
        model.put("descripcion", descripcion);
        return "";
    }
    return "redirect:/menu/pagmenu";
}
@GetMapping("/modmenu")
public String modMenu(ModelMap model){
    model.put("menu", mS.listarMenu());
    return "";
}
@PostMapping("/modificarmenu")
public String modificarMenu (ModelMap model , @RequestParam String nombreResto , @RequestParam String id , @RequestParam Boolean alta , @RequestParam String descripcion){
    try{
        mS.modificarMenu(id, descripcion, alta, nombreResto);
    }catch (ErrorServicio ex){
         model.put("error", ex.getMessage());
        model.put("nombre", nombreResto);
        model.put("id", id);
        model.put("alta", alta);
        model.put("descripcion", descripcion);
        return "";
    }
    return "redirect:/menu/pagmenu";
}
@PostMapping ("/eliminarmenu")
public String eliminarMenu(ModelMap model , @RequestParam String idmenu){
    try{
        mS.bajaMenu(idmenu);
    }catch (ErrorServicio ex){
        model.put("error", ex.getMessage());
        return "";
    }
    return "redirect:/menu/pagmenu";
}
@PostMapping("/altamenu")
public String altaMenu(ModelMap model , @RequestParam String idmenu){
    try{
        mS.altaMenu(idmenu);
    }catch (ErrorServicio ex){
        model.put("error", ex.getMessage());
        return "";
    }
    return "redirect:/menu/pagmenu";
}

@GetMapping("/listamenus")
public String ListarMenus(ModelMap model){
    model.put("menu",mS.listarMenu());
    return "";
}
}
