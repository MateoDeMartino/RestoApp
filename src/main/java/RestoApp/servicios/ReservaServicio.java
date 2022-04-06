package RestoApp.servicios;

import RestoApp.Entidades.Reserva;
import RestoApp.repositorios.ReservaRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;
    
            

    @Transactional
    public void guardarReserva(String nombre, Integer cantidad, Date dia) throws ErrorServicio {

        
        Reserva reserva = new Reserva();

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if (cantidad == 0) {
            throw new ErrorServicio("Ingrese la cantidad de comensales");
        }
        if (dia == null) {
            throw new ErrorServicio("Ingrese el dia");
        }

        
        reserva.setNombre(nombre);
        reserva.setCantidad(cantidad);
        reserva.setDia(dia);
        reserva.setAlta(true);
        reservaRepositorio.save(reserva);
    }
 
     public List<Reserva> buscarReservas(String id){
         List<Reserva> lista = reservaRepositorio.buscarReservas(id);
         return lista;
     }
     
     
        @Transactional
    public void bajaReserva(String id) throws ErrorServicio {
        Optional<Reserva> respuesta = reservaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Reserva reserva = respuesta.get();
            reserva.setAlta(false);
            reservaRepositorio.save(reserva);
        } else {
            throw new ErrorServicio("La reserva no fue encontrada");
        }
    }
}
