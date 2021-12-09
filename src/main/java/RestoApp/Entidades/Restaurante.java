package RestoApp.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private Menu menu;
    private Integer mesas;
    private Zona zona;
    private Boolean abierto;

    public Restaurante() {
    }

    public Restaurante(String id, String nombre, Menu menu, Integer mesas, Zona zona, Boolean abierto) {
        this.id = id;
        this.nombre = nombre;
        this.menu = menu;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
        return Abierto;
    }

    public void setAbierto(Boolean Abierto) {
        this.Abierto = Abierto;
    }

    @Override
    public String toString() {
        return "Restaurante{" + "id=" + id + ", nombre=" + nombre + ", menu=" + menu + ", mesas=" + mesas + ", zona=" + zona + ", Abierto=" + abierto + '}';
    }
    
    
}
