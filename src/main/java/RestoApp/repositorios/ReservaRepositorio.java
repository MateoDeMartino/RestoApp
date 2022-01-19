
package RestoApp.repositorios;

import RestoApp.Entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository <Reserva,String>{
    
    
}
