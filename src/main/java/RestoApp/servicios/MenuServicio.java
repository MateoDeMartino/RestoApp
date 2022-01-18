
package RestoApp.servicios;


import RestoApp.Entidades.Menu;

import RestoApp.servicios.ErrorServicio;
import RestoApp.repositorios.MenuRepositorio;
import RestoApp.servicios.ErrorServicio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuServicio {

    @Autowired
    private MenuRepositorio MenuRepositorio;

  

    @Transactional
    public Menu guardarMenu(String id , String descripcion, Boolean alta , String nombreResto) throws ErrorServicio {

      

        Menu menu = new Menu();
        menu.setId(id);
        menu.setalta(alta);
        menu.setdescripcion(descripcion);
        menu.setnombreResto(nombreResto);
        
        return MenuRepositorio.save(menu);
    }

    @Transactional
    public Menu modificarMenu(String id , String descripcion, Boolean alta , String nombreResto) throws ErrorServicio {
      
        
        Optional<Menu> respuesta = MenuRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Menu menu = respuesta.get();
            menu.setnombreResto(nombreResto);
            menu.setalta(alta);
            menu.setdescripcion(descripcion);

            
           
            return MenuRepositorio.save(menu);
        } else {
            throw new ErrorServicio("El menu no fue encontrado");
        }

    }
    @Transactional
    public void bajaMenu (String id) throws ErrorServicio{
        Optional<Menu> respuesta = MenuRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Menu menu = respuesta.get();
            menu.setalta(false);
            MenuRepositorio.save(menu);
        }else{
            throw new ErrorServicio ("El menu no fue encontrado");
        }
    }
    @Transactional
    public void altaMenu (String id) throws ErrorServicio{
        Optional<Menu> respuesta = MenuRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Menu menu = respuesta.get();
            menu.setalta(true);
            MenuRepositorio.save(menu);
        }else{
            throw new ErrorServicio ("El menu no fue encontrado");
        }
    }
    
    public List <Menu> listarMenu(){
        return MenuRepositorio.findAll();
    }

    public void validar(String nombre, Integer valor, String descripcion) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre es incorrecto");
        }
        if (valor == null) {
            throw new ErrorServicio("El valor es incorrecto");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new ErrorServicio("La descripcion es incorrecta");
        }
    }
}
