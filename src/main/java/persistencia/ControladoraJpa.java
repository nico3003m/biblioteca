package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import logica.Libro;
import logica.Prestamo;
import logica.Usuario;

/**
 *
 * @author Nicolas Moreno
 */
public class ControladoraJpa {

    UsuarioJpaController usuJpa = new UsuarioJpaController();
    LibroJpaController libroJap = new LibroJpaController();

    public void crearUsuarios(Usuario usu) {
        System.out.println("Entro aca al primero controladora 2");
        usuJpa.create(usu);
    }

    public Usuario buscarUsuario(String Correo) {
        List<Usuario> usu = usuJpa.findUsuarioEntities();

        for (Usuario usuario : usu) {
            if (usuario.getEmail().equalsIgnoreCase(Correo)) {
                System.out.println("entro al buscar Usuario");

                return usuario;
            }
        }
        return null;

    }

    public void editarUsuario(Usuario usuario) {
        try {
            usuJpa.edit(usuario);

        } catch (Exception ex) {
            Logger.getLogger(ControladoraJpa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario findUsuarioById(int id) {
        System.out.println("entro a findUsario");
        return usuJpa.findUsuario(id);  // Método existente en tu JPA para encontrar el usuario
    }

    public void createBooks(Libro libros) {
        libroJap.create(libros);
    }

    public List<Libro> obtenerLibros() {
        System.out.println("entro a obtener libro en cotroladora jpa");
        List<Libro> listaLibros = libroJap.findLibroEntities();
        return listaLibros;
    }

    public boolean actualizarDisponibilidad(Long libroId, boolean disponibilidad) {
        try {
            Libro libro = libroJap.findLibro(libroId); // Encuentra el libro por ID
            if (libro != null) {
                libro.setDisponibilidad(disponibilidad); // Actualiza la disponibilidad
                libroJap.edit(libro); // Persistir cambios
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(ControladoraJpa.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public void crearPrestamo(Prestamo prestamo) {
        PrestamoJpaController prestamoJpa = new PrestamoJpaController();
        prestamoJpa.create(prestamo);
    }

    public Libro findLibro(Long id) {
        return libroJap.findLibro(id);
    }

    public void editarLibro(Libro libro) {
        try {
            libroJap.edit(libro);
        } catch (Exception e) {
            Logger.getLogger(ControladoraJpa.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
