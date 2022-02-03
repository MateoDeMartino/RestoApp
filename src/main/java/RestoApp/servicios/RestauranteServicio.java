package RestoApp.servicios;

import RestoApp.Entidades.Foto;
import RestoApp.Entidades.Plato;
import RestoApp.Entidades.Restaurante;
import RestoApp.Entidades.Usuario;
import RestoApp.Entidades.Zona;
import RestoApp.repositorios.PlatoRepositorio;
import RestoApp.repositorios.RestauranteRepositorio;
import RestoApp.repositorios.ZonaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RestauranteServicio {

    @Autowired
    private RestauranteRepositorio restauranteRepositorio;

    @Autowired
    private ZonaRepositorio zonaRepositorio;

    @Autowired
    private PlatoRepositorio platoRepo;
    
    @Autowired
    private FotoServicio fS;

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Transactional
    public void guardarRestaurante(MultipartFile archivo,String nombre, Integer mesas, String zona, Boolean abierto, String idUsuario) throws ErrorServicio {
  
        abierto = true;
        validar(nombre,mesas, abierto, zona, idUsuario);
        
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
        Foto foto =fS.guardar(archivo);
        restaurante.setFoto(foto);
        
        
        restaurante.setIdUsuario(idUsuario);
        restauranteRepositorio.save(restaurante);

    }
    @Transactional
    public void guardarPlatos(String idUsuario){
        Restaurante resto = restauranteRepositorio.buscarRestaurantePorIdusuario(idUsuario);
        resto.setPlatos(platoRepo.buscarPlatoidResto(resto.getId()));
    }
    public List<Plato> listarPlatos(String idresto){
        Restaurante resto = buscarRestauranteId(idresto);
        return resto.getPlatos();        
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
    public void modificarRestaurante(MultipartFile archivo,String nombre, Integer mesas, Zona zona, Boolean abierto) {

        Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorNombre(nombre);
        restaurante.setNombre(nombre);
        restaurante.setAbierto(abierto);
        restaurante.setMesas(mesas);
        restaurante.setZona(zona);
        Foto foto=fS.guardar(archivo);
        restaurante.setFoto(foto);
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
    @Transactional
    public Restaurante buscarRestauranteIdUsuario(String idUsuario){
        
        Restaurante restaurante = restauranteRepositorio.buscarRestaurantePorIdusuario(idUsuario);
        
        return restaurante;
    }

    public void validar(String nombre, Integer mesas, Boolean abierto, String idZona, String idUsuario) throws ErrorServicio {
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
        if (idUsuario == null || idUsuario.isEmpty()) {
            throw new ErrorServicio("Se necesita saber la zona");
        }
    }

    public List<Restaurante> listaraRestaurantes() {

        return restauranteRepositorio.findAll();

    }

}
