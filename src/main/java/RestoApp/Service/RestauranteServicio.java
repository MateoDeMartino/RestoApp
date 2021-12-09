
package RestoApp.Service;

import RestoApp.Entidades.Restaurante;
import RestoApp.Repository.RestauranteRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteServicio {
    
    @Autowired
    private RestauranteRepositorio restauranteRepositorio;
    
    @Transactional
    public void guardarRestaurante(String nombre, Menu menu,Integer mesas,Zona zona,Boolean abierto){
        
        Restaurante restaurante = new Restaurante();
        
        restaurante.setNombre(nombre);
        restaurante.setMenu(menu);
        restaurante.setMesas(mesas);
        restaurante.setZona(zona);
        restaurante.setAbierto(abierto);
        
        restauranteRepositorio.save(restaurante);
        
    }
    
    @Transactional
    public Restaurante buscarRestaurante(String nombre){
        
       Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorNombre(nombre);
       
       return restaurante;
        
    }
    
    @Transactional
    public void modificarRestaurante(String nombre) {

        Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorNombre(nombre);
        //restaurante.setNombre();
        restauranteRepositorio.save(restaurante);

    }
    
    @Transactional
    public void eliminarAutor(String Id){
        
        Optional<Restaurante> restaurante = restauranteRepositorio.findById(Id);
        restaurante.get().setAbierto(false);
        restauranteRepositorio.save(restaurante.get());
        
    }
    
    @Transactional
    public List<Restaurante> listaraAutores() {
        
        return restauranteRepositorio.findAll();
    
    }
}
