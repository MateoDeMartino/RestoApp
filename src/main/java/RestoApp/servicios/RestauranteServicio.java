package RestoApp.servicios;

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
    private ZonaRepositorio zonaRepositorio;

    @Transactional
    public void guardarRestaurante(String nombre, Integer mesas, String zona, Boolean abierto) throws ErrorServicio {
  
        abierto = true;
        validar(nombre,mesas, abierto, zona);
        
        Restaurante restaurante = new Restaurante();
        
        restaurante.setNombre(nombre);
        restaurante.setMesas(mesas);
        restaurante.setAbierto(abierto);
        
        String zonaMayusc = zona.toUpperCase();
        Zona respuesta = zonaRepositorio.buscarZonaPorNombre(zonaMayusc);
        if (respuesta.getNombre().equals(zonaMayusc)) {
            restaurante.setZona(respuesta);
        } else {
            throw new ErrorServicio("No se encontro la zona");
        }

        restauranteRepositorio.save(restaurante);

    }

    @Transactional
    public Restaurante buscarRestaurante(String nombre) {

        Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorNombre(nombre);

        return restaurante;

    }

    @Transactional
    public Restaurante buscarRestauranteZona(String zona) {

        Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorZona(zona);

        return restaurante;

    }

    @Transactional
    public void modificarRestaurante(String nombre, Integer mesas, Zona zona, Boolean abierto) {

        Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorNombre(nombre);
        restaurante.setNombre(nombre);
        restaurante.setAbierto(abierto);
        restaurante.setMesas(mesas);
        restaurante.setZona(zona);
        restauranteRepositorio.save(restaurante);

    }

    @Transactional
    public void eliminarRestaurante(String Id) {

        Optional<Restaurante> restaurante = restauranteRepositorio.findById(Id);
        restaurante.get().setAbierto(false);
        restauranteRepositorio.save(restaurante.get());

    }
     @Transactional
    public Restaurante buscarRestauranteId(String Id) {

        Optional<Restaurante> restaurante = restauranteRepositorio.findById(Id);
          Restaurante r =restaurante.get();
        return r;

    }

    public void validar(String nombre, Integer mesas, Boolean abierto, String idZona) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }

        if (mesas == 0 || mesas == null) {
            throw new ErrorServicio("Se necesita la cantidad de mesas");
        }
        if (idZona == null || idZona.isEmpty()) {
            throw new ErrorServicio("Se necesita saber la zona");
        }
        if (abierto == null) {
            throw new ErrorServicio("Se necesita saber si esta abierto o cerrado");
        }
    }

    public List<Restaurante> listaraRestaurantes() {

        return restauranteRepositorio.findAll();

    }

}
