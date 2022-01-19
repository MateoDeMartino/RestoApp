package RestoApp.servicios;

import RestoApp.Entidades.Reserva;
import RestoApp.repositorios.ReservaRepositorio;
import java.util.Date;
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

        reservaRepositorio.save(reserva);
    }

}
