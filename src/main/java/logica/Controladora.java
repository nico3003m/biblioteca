package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;
import persistencia.ControladoraJpa;
import static utilitis.seguridad.decrypt;

/**
 *
 * @author Nicolas Moreno
 */
public class Controladora {

    ControladoraJpa persis = new ControladoraJpa();

    public void crearUsuario(String nombres, String apellido, String correo, String contrasenia1, String rol) {
        Usuario usu = new Usuario();
        // Setteo en clase 
        usu.setNombre(nombres);
        usu.setApellido(apellido);
        usu.setEmail(correo);
        usu.setContrasena(contrasenia1);
        usu.setRol(rol);
        System.out.println("Entro aca al primero controladora 1");
        persis.crearUsuarios(usu);
    }

    public boolean obtenerUsuarios(String correo, String contrasenia) throws Exception {
        Usuario usu = new Usuario();
        usu.setEmail(correo);

        usu = persis.buscarUsuario(correo);
        System.out.println("entro al obtener usuaiio");

        if (usu != null) {
            // Sistema de encriptar contraseña
            String encryptedPassword = usu.getContrasena();
            System.out.println("contraseña " + encryptedPassword);
            String decryptedPassword = null;
            String secretKey = "1234567890123456";

            decryptedPassword = decrypt(encryptedPassword, secretKey);

            if (decryptedPassword.equals(contrasenia)) {
                return true;

            }
        }
        return false;
    }

    public Usuario ObtenerUsuarioSession(String correo, String contrasenia) {
        Usuario usu = new Usuario();
        usu.setEmail(correo);

        return persis.buscarUsuario(correo);
    }

    public Usuario actualizarUsuario(int id_usuario, String nombre, String apellido, String correo, String contrasenia1) {
        System.out.println("entro a mostrar el usuario id usuario : " + id_usuario);
        Usuario usu = persis.findUsuarioById(id_usuario);  // Método que obtiene el usuario desde la base de datos
        System.out.println("entro a mostrar el usuario id usuario : " + usu.getId_usuario());
        if (usu != null) {
            // Actualizar los datos del usuario
            usu.setNombre(nombre);
            usu.setApellido(apellido);
            usu.setEmail(correo);
            usu.setContrasena(contrasenia1);

            // Persistir los cambios en la base de datos
            persis.editarUsuario(usu);
        }

        return usu;
    }

    public void crearLibros(String nombre, String autor, int anio, String bn, String genero, boolean disponibilidad) {
        Libro libros = new Libro();
        libros.setTitulo(nombre);
        libros.setAutor(autor);
        libros.setAno(anio);
        libros.setISBN(bn);
        libros.setGenero(genero);
        libros.setDisponibilidad(true);
        persis.createBooks(libros);
    }

    public List<Libro> obtenerLibros() {
        System.out.println("entro a obtener libro en class");
        return persis.obtenerLibros();
    }

    public boolean actualizarDisponibilidad(int libroId, boolean disponibilidad) {
        return actualizarDisponibilidad(libroId, disponibilidad);
    }

    public boolean actualizarDisponibilidad(Long libroId, boolean disponibilidad) {
        return persis.actualizarDisponibilidad(libroId, disponibilidad);
    }

    public void crearPrestamo(Long usuarioId, Long libroId) {
        Usuario usuario = persis.findUsuarioById(usuarioId.intValue());
        Libro libro = persis.findLibro(libroId);

        if (usuario != null && libro != null) {
            Prestamo prestamo = new Prestamo();
            prestamo.setUsuario(usuario);
            prestamo.setLibro(libro);
            prestamo.setFecha_prestamo(new Date()); // Establecer la fecha de préstamo
            // Aquí podrías establecer la fecha de devolución, por ejemplo, a 14 días después
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 14);
            prestamo.setFecha_devolucion(calendar.getTime());
            prestamo.setEstado("Prestado");
            persis.crearPrestamo(prestamo);
        }
    }

    public Libro obtenerLibroPorId(Long libroId) {
        return persis.findLibro(libroId);
    }

    public void actualizarLibro(Long idLibro, String titulo, String autor, int anio, String isbn, String genero, boolean disponibilidad) {
        Libro libro = persis.findLibro(idLibro);

        if (libro != null) {
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setAno(anio);
            libro.setISBN(isbn);
            libro.setGenero(genero);
            libro.setDisponibilidad(disponibilidad);

            persis.editarLibro(libro);
        }
    }
}
