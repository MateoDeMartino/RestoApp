
package RestoApp.Entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;


public class Zona {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String nombreZona;

    public Zona() {
    }

    public Zona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    @Override
    public String toString() {
        return "Zona{" + "nombreZona=" + nombreZona + '}';
    }
    
    
    
    
    
}
