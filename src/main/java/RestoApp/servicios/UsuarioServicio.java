package RestoApp.servicios;

import RestoApp.Entidades.Usuario;
import RestoApp.enums.Role;
import RestoApp.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Salo
 */
@Service                    //hacemos este implemetens para poder autentificar usuarios en la plataforma
public class UsuarioServicio implements UserDetailsService { //esta interface UserDetailsService nos obliga a implemetar un metodo abstracto

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private NotificacionServicio notificacionServicio;

    //Se llama cuando un usuario completa el formulario de registro
    @Transactional
    public void registrar(String nombre, String apellido, String mail, String clave, Integer role) throws ErrorServicio { //recibimos los datos de un formulario

        //antes de persistir deberiamos validar si los datos que se envían no vienen vacios
        validar(nombre, apellido, mail, clave);

        //recibimos los datos y los transformamos en una entidad en el sistema
        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);              //esos datos se van a transformar en una entidad de tipo usuario
        usuario.setApellido(apellido);
        usuario.setMail(mail);

        //adaptamos el servicio de usuario a Spring security y usamos el mismo enciptador que va 
        //a usar el servicio central de seguridad
        String encriptada = new BCryptPasswordEncoder().encode(clave); //Spring Security toma la calve en txt plano y la encipta a traves del encoder
        usuario.setClave(encriptada);

        usuario.setAlta(new Date());
        
        if (role == 1) {
            usuario.setRol(Role.USER);
        }
        if (role == 2) {
            usuario.setRol(Role.SELLER);
        }

        usuarioRepositorio.save(usuario);       //por ultimo le decimos al repositorio que lo guarde en la BD

        //notificacionServicio.enviar("Bienvenido a RestoApp", "RestoApp", usuario.getMail());
    }

    public void validar(String nombre, String apellido, String mail, String clave) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("Nombre no puede ser vacio");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("Apellido no puede ser vacio");
        }

        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("Mail no puede ser vacio");
        }

        if (clave == null || clave.isEmpty()) {
            throw new ErrorServicio("Clave no puede ser vacio o tener menos de 6 caracteres");
        }

    }

    @Transactional
    public void modificar(String id, String nombre, String apellido,
            String mail, String clave) throws ErrorServicio {//en este caso enviamos el id para identificar cual es el suario que queremos modificar

        //validamos que los datos no vengan vacios
        validar(nombre, apellido, mail, clave);

        //Si el id es incorrecto o está vacio lo que devuelve JPA es un Optional para le metodo findById
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {    //dentro del optional yo puedo ver si vino un usuario como respuesta de ese id que enviamos
            Usuario usuario = respuesta.get();  //si lo encuentra, lo busca y lo modifica
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setMail(mail);

            String encriptada = new BCryptPasswordEncoder().encode(clave);
            usuario.setClave(encriptada);

            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontró el usuario");   //si no encuentra el id enviado por paramentro, tira la exception
        }

    }

    @Transactional
    public void deshabilitar(String id) throws ErrorServicio {
        //Vamos a buscar el usuario con ese id
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {    //si esta presente 
            Usuario usuario = respuesta.get();
            usuario.setBaja(new Date());    //se le settea una fecha de baja

            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontró el usuario");   //si no esta ese usuario, se tira un error de servicio
        }
    }

    @Transactional
    public void habilitar(String id) throws ErrorServicio {
        //Vamos a buscar el usuario con ese id
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {    //si esta presente 
            Usuario usuario = respuesta.get();
            usuario.setBaja(null);  //se le settea una fecha de baja nula, es decir, borrarle la fecha de baja

            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontró el usuario");
        }
    }

    public Usuario buscarPorId(String id) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            return usuario;
        } else {
            throw new ErrorServicio("No se encontró el usuario");
        }
    }

    // este metodo recibe el nombre de usuario (q en este caso es el mail)  
    // busquemos en la BD el usuario de nustro dominio 
    //y lo transformemos en un usuairo del dominio de Spring Security
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        //declaramos una variable usuario de nustro dominio y usamos el metodo buscar por mail
        //este metodo busca en nuestro almacenmaiento un usuario que tenga ese mail  
        Usuario usuario = usuarioRepositorio.buscarPorMail(mail);

        if (usuario != null) {

            //listado de permisos que me pide la linea 148
            List<GrantedAuthority> permisos = new ArrayList();  //la clase GrantedAuthority es la clase que tiene el listado de permisos que tiene ese usuario
            permisos.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
//            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_"+usuario.getRol());
//            permisos.add(p1);

            //Con esta llamada guardo al usuario de la BD que se que esta autenticado
            //y lo meto en esta sesion web
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            session.setAttribute("usuariosession", usuario);
            //en la variable de sesion usariosession yo tengo guardado mi objeto con todos los datos del usuario que esta logueado

            //con esto transofrmamos en un usuario del dominio de Spring
            User user = new User(usuario.getMail(), usuario.getClave(), permisos);  //el constructor pide 
            return user;
        } else {
            return null;
        }
    }
}
