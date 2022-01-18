package RestoApp.repositorios;

import RestoApp.Entidades.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepositorio extends JpaRepository<Menu, String> {
//    @Query ("SELECT c FROM autor c WHERE c.nombre =:nombre")
//    public Menu buscarMenuPorNombre(@Param("id") String id);

//    public Menu buscarMenuPorId(String id);
}