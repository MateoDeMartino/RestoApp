package RestoApp.servicios;

import RestoApp.entidades.Foto;
import RestoApp.repositorios.FotoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepo;
    @Transactional
    public Foto guardar(MultipartFile archivo) {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
              
                foto.setContenido(archivo.getBytes());

                return fotoRepo.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }
        return null;
    }
    @Transactional
    public Foto actualizar(String idFoto, MultipartFile archivo) {

        if (archivo != null) {
            try {
                Foto foto = new Foto();
                Optional<Foto> respuesta = fotoRepo.findById(idFoto);
                if (respuesta.isPresent()) {
                    foto = respuesta.get();
                }

                foto.setMime(archivo.getContentType());
            
                foto.setContenido(archivo.getBytes());

                return fotoRepo.save(foto);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return null;
    }
}
