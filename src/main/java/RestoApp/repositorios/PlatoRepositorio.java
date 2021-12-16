package RestoApp.repositorios;

import RestoApp.entidades.Plato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatoRepositorio extends JpaRepository<Plato, String> {
    
}
