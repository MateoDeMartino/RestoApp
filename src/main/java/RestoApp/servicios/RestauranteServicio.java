
package RestoApp.servicios;

import RestoApp.Entidades.Menu;
import RestoApp.Entidades.Restaurante;
import RestoApp.Entidades.Zona;
import RestoApp.repositorios.RestauranteRepositorio;
import RestoApp.repositorios.ZonaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteServicio {
    
    @Autowired
    private RestauranteRepositorio restauranteRepositorio;
    
    @Autowired
    private ZonaRepositorio zonaRepo;
    
    @Transactional
    public void guardarRestaurante(String nombre, Menu menu,Integer mesas,Boolean abierto, String idZona)throws ErrorServicio{
        
        validar(nombre,menu,mesas, abierto, idZona);
        
        Restaurante restaurante = new Restaurante();      
        restaurante.setNombre(nombre);
        restaurante.setMenu(menu);
        restaurante.setMesas(mesas);        
        restaurante.setAbierto(abierto);
        
        Optional<Zona> respuesta = zonaRepo.findById(idZona);
        if (respuesta.isPresent()) {
            Zona zona = respuesta.get();
            restaurante.setZona(zona);
        } else {
            throw new ErrorServicio("No se encontro la zona");
        }
        
        restauranteRepositorio.save(restaurante);
        
    }
    
    @Transactional
    public Restaurante buscarRestaurante(String nombre){
        
       Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorNombre(nombre);
       
       return restaurante;
        
    }
    
    @Transactional
    public Restaurante buscarRestauranteZona(String zona){
        
       Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorZona(zona);
       
       return restaurante;
        
    }
    
    @Transactional
    public void modificarRestaurante(String nombre,Menu menu,Integer mesas,Zona zona,Boolean abierto) {

        Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorNombre(nombre);
        restaurante.setNombre(nombre);
        restaurante.setAbierto(abierto);
        restaurante.setMenu(menu);
        restaurante.setMesas(mesas);
        restaurante.setZona(zona);
        restauranteRepositorio.save(restaurante);

    }
    
    @Transactional
    public void eliminarRestaurante(String Id){
        
        Optional<Restaurante> restaurante = restauranteRepositorio.findById(Id);
        restaurante.get().setAbierto(false);
        restauranteRepositorio.save(restaurante.get());
        
    }
    
    public void validar(String nombre, Menu menu,Integer mesas,Boolean abierto, String idZona) throws ErrorServicio{
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if ( menu.getPlato().getNombre().isEmpty() || menu == null ) {
            throw new ErrorServicio("Se necesita un menu");
        }
        if ( mesas == 0 || mesas == null) {
            throw new ErrorServicio("Se necesita la cantidad de mesas");
        } 
        if (idZona ==null || idZona.isEmpty()) {
            throw new ErrorServicio("Se necesita saber la zona");
        }
        if (abierto == null ) {
            throw new ErrorServicio("Se necesita saber si esta abierto o cerrado");
        }
    }
    
    public List<Restaurante> listaraRestaurantes() {
        
        return restauranteRepositorio.findAll();
    
    }
  
    
}
