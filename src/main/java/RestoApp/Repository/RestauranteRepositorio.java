
package RestoApp.Repository;

import RestoApp.Entidades.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepositorio extends JpaRepository<Restaurante, String> {

    @Query("SELECT c FROM Autor c WHERE c.nombre = :nombre")
    public Restaurante buscarRestaurantePorNombre(@Param("nombre") String nombre);

}

