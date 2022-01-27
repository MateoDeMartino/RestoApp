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
     public ResponseEntity<byte[]> fotoPlato(String id){
        
         try{
        Plato plato= pS.buscarPlatoId(id);
         byte[] foto =plato.getFoto().getContenido();
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.IMAGE_JPEG);
         return new ResponseEntity<>(foto,headers,HttpStatus.OK);
         }catch(ErrorServicio ex){
             Logger.getLogger()
         }
     }
             
     
}
