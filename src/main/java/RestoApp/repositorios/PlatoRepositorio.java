package RestoApp.repositorios;

import RestoApp.entidades.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepositorio extends JpaRepository<Plato, String> {
    
      @Query("SELECT e FROM Plato e  WHERE e.id = :id")/*hacemos la busqueda para que al buscar un plato me traiga un objeto y no un optional*/
    public Plato buscarPlatoId(@Param("id") String id);
    
  
    
}
