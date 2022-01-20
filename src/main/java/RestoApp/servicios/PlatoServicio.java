package RestoApp.servicios;

import RestoApp.entidades.Foto;
import RestoApp.entidades.Plato;
import RestoApp.servicios.ErrorServicio;
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

    @Transactional
    public Plato guardarPlato(MultipartFile archivo, String nombre, Integer valor, String descripcion) throws ErrorServicio {

        validar(nombre, valor, descripcion);

        Plato plato = new Plato();
        plato.setNombre(nombre);
        plato.setValor(valor);
        plato.setDescripcion(descripcion);

       // Foto foto = fS.guardar(archivo);
        //plato.setFoto(foto);

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
            if (plato.getFoto() != null ) {
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
    public void bajaPlato (String id) throws ErrorServicio{
        Optional<Plato> respuesta = platoRepo.findById(id);
        if (respuesta.isPresent()) {
            Plato plato = respuesta.get();
            plato.setAlta(false);
            platoRepo.save(plato);
        }else{
            throw new ErrorServicio ("El plato no fue encontrado");
        }
    }
    @Transactional
    public void altaPlato (String id) throws ErrorServicio{
        Optional<Plato> respuesta = platoRepo.findById(id);
        if (respuesta.isPresent()) {
            Plato plato = respuesta.get();
            plato.setAlta(true);
            platoRepo.save(plato);
        }else{
            throw new ErrorServicio ("El plato no fue encontrado");
        }
    }
    
    public List <Plato> listarPlatos(){
        return platoRepo.findAll();
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
