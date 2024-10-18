/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Libro;
import logica.Prestamo;
import logica.Usuario;

/**
 *
 * @author Nicolas Moreno
 */
@WebServlet(name = "SvReservaLibros", urlPatterns = {"/SvReservaLibros"})
public class svReservaLibros extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entró al GET en SvReservaLibros");
        Controladora controladoraClass = new Controladora();

        // Obtener la lista completa de libros
        List<Libro> listaLibros = controladoraClass.obtenerLibros();

        // Crear una nueva lista para almacenar solo los libros disponibles
        List<Libro> listaLibrosDisponibles = new ArrayList<>();

        // Filtrar los libros disponibles
        for (Libro libro : listaLibros) {
            if (libro.isDisponibilidad()) { // Asumiendo que isDisponibilidad() es un booleano
                listaLibrosDisponibles.add(libro); // Agregar solo los libros disponibles
                System.out.println("Libro disponible: " + libro.getTitulo());
            }
        }

        // Pasar la lista filtrada al JSP
        request.setAttribute("listaLibrosReserva", listaLibrosDisponibles);
        request.getRequestDispatcher("prestamoLibros.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long libroIdStr = Long.valueOf(request.getParameter("libroSeleccionado")); // Obtener el ID del libro seleccionado
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("datosUsuario"); // Obtener el usuario logueado

        if (usuarioLogueado != null) {
            Long usuarioId = Long.valueOf(usuarioLogueado.getId_usuario()); // Convierte el int a Long


            Controladora controladoraClass = new Controladora();
            if (libroIdStr != null) {
                // Crear el préstamo
                controladoraClass.crearPrestamo(usuarioId, libroIdStr); // Llamar al método con los IDs

                // Actualizar la disponibilidad del libro
                boolean exito = controladoraClass.actualizarDisponibilidad(libroIdStr, false); // Seteamos la disponibilidad a false

                // Redirigir según el resultado de la operación
                if (exito) {
                    request.setAttribute("mensaje", "El libro ha sido reservado exitosamente.");
                } else {
                    request.setAttribute("mensaje", "No se pudo reservar el libro. Intente nuevamente.");
                }
            } else {
                request.setAttribute("mensaje", "No se seleccionó ningún libro.");
            }

            // Volver a mostrar la lista de libros o redirigir a otra página
            List<Libro> listaLibros = controladoraClass.obtenerLibros();
            request.setAttribute("listaLibrosReserva", listaLibros);
            request.getRequestDispatcher("prestamoLibros.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "Debe iniciar sesión para reservar un libro.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
