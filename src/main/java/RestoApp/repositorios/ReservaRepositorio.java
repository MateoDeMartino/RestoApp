
package RestoApp.repositorios;

import RestoApp.Entidades.Plato;
import RestoApp.Entidades.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository <Reserva,String>{
    
       @Query("SELECT e FROM Reserva e  WHERE e.nombre = :nombre")/*hacemos la busqueda para que al buscar un plato me traiga un objeto y no un optional*/
      public List<Reserva> buscarReservas(@Param("nombre") String nombre);
    
}
