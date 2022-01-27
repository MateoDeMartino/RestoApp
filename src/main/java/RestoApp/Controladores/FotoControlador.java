/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestoApp.Controladores;

import RestoApp.entidades.Plato;
import RestoApp.servicios.ErrorServicio;
import RestoApp.servicios.FotoServicio;
import RestoApp.servicios.PlatoServicio;
import com.sun.istack.logging.Logger;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

 
/**
 *
 * @author Federico
 */
@Controller
@RequestMapping("/foto")
public class FotoControlador {

    @Autowired
    private FotoServicio fS;

    @Autowired
    private PlatoServicio pS;

    @GetMapping("/fotoPlato")
    public ResponseEntity<byte[]> fotoPlato(@RequestParam String id) {

        
            
        try {
            Plato plato = pS.buscarPlatoId(id);
            if (plato.getFoto() == null) {
                throw new ErrorServicio("El plato no tiene una foto asignada");
            }
            byte[] foto = plato.getFoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
                    
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (ErrorServicio ex) {
            java.util.logging.Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
          
 

    }

}
