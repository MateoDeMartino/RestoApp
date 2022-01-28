
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestoApp.servicios;

import RestoApp.Entidades.Zona;
import RestoApp.repositorios.ZonaRepositorio;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Federico
 */
@Service
public class ZonaServicio {

    @Autowired
    ZonaRepositorio zonarepositorio;

    @Transactional
    public Zona crearZona(String nombre) {
        Zona zona = new Zona();
        String nombreMayusc = nombre.toUpperCase();
        zona.setNombre(nombreMayusc);
        zona.setAlta(true);
        return zonarepositorio.save(zona);
    }
//    @Transactional
//    public Zona crearZona(Zona zona) throws ErrorServicio{
//        if (zona == null) {
//            throw new ErrorServicio ("La zona no puede ser nula");
//        }        
//        return zonarepositorio.save(zona);
//    }

    @Transactional
    public Zona modificarZona(String id, String nombre) throws ErrorServicio {
        if (id.isEmpty() || id == null) {

            throw new ErrorServicio("No se envio id de Zona");
        }
        
        //String nombreMayusc = nombre.toUpperCase();
        Optional<Zona> respuesta = zonarepositorio.findById(id);
        if (respuesta.isPresent()) {
            Zona zona = new Zona();
            zona = respuesta.get();
            zona.setNombre(nombre.toUpperCase());
            zona.setAlta(true);
            
            return zonarepositorio.save(zona);
        }else{
            throw new ErrorServicio("La zona no fue encontrada");
        }  
    }
    
    
    @Transactional
    public void deshabilitarZona(String id) throws ErrorServicio{
        
        Optional<Zona> respuesta = zonarepositorio.findById(id);
        if (respuesta.isPresent()) {
            Zona zona = respuesta.get();
            zona.setAlta(false);
             zonarepositorio.save(zona);
        } else {
            throw new ErrorServicio("La zona no fue encontrado");
        }
    }
    
    public List<Zona> listarZonas(){
        return zonarepositorio.findAll();
    }
    
    @Transactional
    public Zona buscarZonaId(String id) throws ErrorServicio {

        Optional<Zona> respuesta = zonarepositorio.findById(id);
        if (respuesta.isPresent()) {
            Zona zona = respuesta.get();
            return zona;
        } else {
            throw new ErrorServicio("La zona no fue encontrado");
        }

    }
}
