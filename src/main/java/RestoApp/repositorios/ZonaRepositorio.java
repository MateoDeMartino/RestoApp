/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestoApp.repositorios;

import RestoApp.Entidades.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico
 */
@Repository
public interface ZonaRepositorio extends JpaRepository<Zona, String> {

    @Query("SELECT c FROM Zona c WHERE c.nombre = :nombre")
    public Zona buscarZonaPorNombre(@Param("nombre") String zona);

}
