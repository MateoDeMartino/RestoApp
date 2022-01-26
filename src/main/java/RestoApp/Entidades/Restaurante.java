package RestoApp.Entidades;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Restaurante implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private Integer mesas;
    @ManyToOne
    private Zona zona;
    private Boolean abierto;
    
    
    public Restaurante() {
    }

    public Restaurante(String id, String nombre, Integer mesas, Zona zona, Boolean abierto) {
        this.id = id;
        this.nombre = nombre;
        this.mesas = mesas;
        this.zona = zona;
        this.abierto = abierto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMesas() {
        return mesas;
    }

    public void setMesas(Integer mesas) {
        this.mesas = mesas;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Boolean getAbierto() {
        return abierto;
    }

    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }


    

    
    
    
    
    
    
    
    
    
}

 