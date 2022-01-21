/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestoApp.servicios;

import RestoApp.Entidades.Zona;
import RestoApp.repositorios.ZonaRepositorio;
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
    public Zona crearZona(String nombreZona) {
        Zona zona = new Zona();
        zona.setNombreZona(nombreZona);
        return zonarepositorio.save(zona);
    }
    @Transactional
    public Zona crearZona(Zona zona) throws ErrorServicio{
        if (zona == null) {
            throw new ErrorServicio ("La zona no puede ser nula");
        }        
        return zonarepositorio.save(zona);
    }

    @Transactional
    public Zona modificarZona(String id, String nombreZona) throws ErrorServicio {
        if (id.isEmpty()) {

            throw new ErrorServicio("La zona no existe");
        }

        Zona zona = new Zona();
        Optional<Zona> respuesta = zonarepositorio.findById(id);
        if (respuesta.isPresent()) {
            zona = respuesta.get();
            zona.setNombreZona(nombreZona);
        }
        return zonarepositorio.save(zona);
    }
    
    
    @Transactional
    public void deshabilitarZona(String id) throws ErrorServicio{
        
        Optional<Zona> respuesta = zonarepositorio.findById(id);
        if (respuesta.isPresent()) {
            Zona zona = respuesta.get();
            zonarepositorio.delete(zona);           
        } else {
            throw new ErrorServicio("La zona no fue encontrado");
        }
    }
    
    public List<Zona> listarZonas(){
        return zonarepositorio.findAll();
    }
}
