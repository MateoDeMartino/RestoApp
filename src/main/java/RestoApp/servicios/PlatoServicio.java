package RestoApp.servicios;

import RestoApp.Entidades.Foto;
import RestoApp.Entidades.Plato;
import RestoApp.Entidades.Restaurante;
import RestoApp.repositorios.PlatoRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PlatoServicio {

    @Autowired
    private PlatoRepositorio platoRepo;

    @Autowired
    private FotoServicio fS;

    @Autowired
    private RestauranteServicio rS;

    @Transactional
    public Plato guardarPlato(MultipartFile archivo, String nombre, Integer valor, String descripcion, String idUsuario) throws ErrorServicio {

        validar(nombre, valor, descripcion);

        Plato plato = new Plato();
        plato.setNombre(nombre);
        plato.setValor(valor);
        plato.setDescripcion(descripcion);
        Restaurante restaurante = rS.buscarRestauranteIdUsuario(idUsuario);
        plato.setIdresto(restaurante.getId());
        plato.setAlta(true);

        Foto foto = fS.guardar(archivo);
        plato.setFoto(foto);

        return platoRepo.save(plato);
    }

    @Transactional
    public Plato modificarPlato(MultipartFile archivo, String id, String nombre, Integer valor, String descripcion) throws ErrorServicio {
        validar(nombre, valor, descripcion);
        Optional<Plato> respuesta = platoRepo.findById(id);
        if (respuesta.isPresent()) {
            Plato plato = respuesta.get();
            plato.setNombre(nombre);
            plato.setValor(valor);
            plato.setDescripcion(descripcion);

            String idFoto = null;
            if (plato.getFoto() != null) {
                idFoto = plato.getFoto().getId();
            }
            Foto foto = fS.actualizar(idFoto, archivo);
            plato.setFoto(foto);
            return platoRepo.save(plato);
        } else {
            throw new ErrorServicio("El plato no fue encontrado");
        }

    }

    @Transactional
    public void bajaPlato(String id) throws ErrorServicio {
        Optional<Plato> respuesta = platoRepo.findById(id);
        if (respuesta.isPresent()) {
            Plato plato = respuesta.get();
            plato.setAlta(false);
            platoRepo.save(plato);
        } else {
            throw new ErrorServicio("El plato no fue encontrado");
        }
    }

    @Transactional
    public void altaPlato(String id) throws ErrorServicio {
        Optional<Plato> respuesta = platoRepo.findById(id);
        if (respuesta.isPresent()) {
            Plato plato = respuesta.get();
            plato.setAlta(true);
            platoRepo.save(plato);
        } else {
            throw new ErrorServicio("El plato no fue encontrado");
        }
    }

    @Transactional
    public void eliminarPlatoId(String id) throws ErrorServicio {
        Optional<Plato> respuesta = platoRepo.findById(id);
        if (respuesta.isPresent()) {
            Plato plato = respuesta.get();
            platoRepo.delete(plato);
            System.out.println("SE EJECUTO LA ELIMINACION");
        } else {
            throw new ErrorServicio("El plato a eliminar no fue encontrado");
        }
    }

    @Transactional
    public List<Plato> listarPlatos() {

        return platoRepo.findAll();
    }

    @Transactional
    public Plato buscarPlatoId(String id) throws ErrorServicio {

        Optional<Plato> respuesta = platoRepo.findById(id);
        if (respuesta.isPresent()) {
            Plato plato = respuesta.get();
            return plato;
        } else {
            throw new ErrorServicio("El plato no fue encontrado");
        }

    }

    public void validar(String nombre, Integer valor, String descripcion) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacio");
        }
        if (valor == null || valor < 0) {
            throw new ErrorServicio("El valor no puede ser nulo ni negativo");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new ErrorServicio("Agregue una descripción");
        }
    }
}
