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
    PrestamoJpaController prestaJap = new PrestamoJpaController();
    

    public void crearUsuarios(Usuario usu) {
        
        usuJpa.create(usu);
    }

    public Usuario buscarUsuario(String Correo) {
        List<Usuario> usu = usuJpa.findUsuarioEntities();

        for (Usuario usuario : usu) {
            if (usuario.getEmail().equalsIgnoreCase(Correo)) {
                

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
        
        return usuJpa.findUsuario(id);  // Método existente en tu JPA para encontrar el usuario
    }

    public void createBooks(Libro libros) {
        libroJap.create(libros);
    }

    public List<Libro> obtenerLibros() {
        
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

    public List<Prestamo> obtenerReserva() {
        // Esto devolvera los préstamos con los datos de usuario y libro cargados
        List<Prestamo> listaReservas = prestaJap.findPrestamoEntities();

        // Cargar las relaciones si es necesario (puede que ya estén cargadas automáticamente)
        for (Prestamo prestamo : listaReservas) {
            prestamo.getUsuario(); // Acceder a la relación con Usuario para asegurar que se cargue
            prestamo.getLibro();   // Acceder a la relación con Libro para asegurar que se cargue
        }

        return listaReservas;
    }
    public List<Usuario> obtenerUsuarios() {
        return usuJpa.findUsuarioEntities();
    }

}
