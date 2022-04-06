package RestoApp.repositorios;

import RestoApp.Entidades.Plato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepositorio extends JpaRepository<Plato, String> {
    
      @Query("SELECT e FROM Plato e  WHERE e.id = :id")/*hacemos la busqueda para que al buscar un plato me traiga un objeto y no un optional*/
    public Plato buscarPlatoId(@Param("id") String id);
    
    @Query("SELECT e FROM Plato e WHERE e.idresto = :idresto")
    public List<Plato> buscarPlatoidResto(@Param("idresto") String id);
    
  
    
}
