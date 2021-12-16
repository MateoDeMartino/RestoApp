package RestoApp.error;

public class ErrorService extends Exception {
    //creamos esta clase para diferenciara los errores
    //de nuestra logica, de los q ocurren el sistema
    public ErrorService (String msj){
        super(msj);//recibimos un msj y se lo pasamos a la super clase
    }
            
}
