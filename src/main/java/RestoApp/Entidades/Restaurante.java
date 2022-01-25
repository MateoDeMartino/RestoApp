package RestoApp.Entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private Integer mesas;
    @OneToOne
    private Plato plato;
    @ManyToOne
    private Zona zona;
    private Boolean abierto;

    public Restaurante() {
    }

    public Restaurante(String id, String nombre, Integer mesas, Plato plato, Zona zona, Boolean abierto) {
        this.id = id;
        this.nombre = nombre;
        this.mesas = mesas;
        this.plato = plato;
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

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
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

    @Override
    public String toString() {
        return "Restaurante{" + "id=" + id + ", nombre=" + nombre + ", mesas=" + mesas + ", plato=" + plato + ", zona=" + zona + ", abierto=" + abierto + '}';
    }
    
    
    
    
    
    
    
    
}

 