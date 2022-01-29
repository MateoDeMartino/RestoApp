
package RestoApp.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Menu implements Serializable {
@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid", strategy = "uuid2")
private String id;
private Boolean alta;
private String nombreResto;
@OneToOne
private Plato plato;
private String descripcion;

public Menu() {
}
public Menu(String id, Plato plato, String descripcion, Boolean alta , String nombreRsto){
    this.id = id;
    this.nombreResto = nombreResto;
    this.plato = plato;
    this.descripcion = descripcion;
    this.alta = true;
} 
public String getId(){
    return id;
}
public void setId(String id){
    this.id = id;
}


public Plato getPlato(){
    return plato;
}
public void setPlato(Plato plato){
    this.plato = plato;
}
public String descripcion(){
    return descripcion;
}
public void setdescripcion(String descripcion){
    this.descripcion = descripcion;
}
public Boolean getalta(){
    return alta;
}
public void setalta(Boolean alta){
    this.alta = alta;
}

public String getnombreResto(){
    return nombreResto;
}
public void setnombreResto(String nombreResto){
    this.nombreResto = nombreResto;
}

@Override
public String toString(){
    return "Menu{" + id  + ", Descripcion" + descripcion + ", alta" + alta + "nombre" + nombreResto + "}";
}


  
}
