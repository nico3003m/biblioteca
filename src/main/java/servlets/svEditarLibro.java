package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Libro;
import logica.Usuario;

/**
 *
 * @author Nicolas Moreno
 */
@WebServlet(name = "svEditarLibro", urlPatterns = {"/svEditarLibro"})
public class svEditarLibro extends HttpServlet {
// Instacia Controladora class
    private Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos los parameros
        Long idLibro = Long.parseLong(request.getParameter("id"));
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        request.setAttribute("usuarioLogueado", usuarioLogueado);
        Libro libro = control.obtenerLibroPorId(idLibro);

        // Guardar el libro en el request para enviarlo a la vista de edición
        request.setAttribute("libro", libro);
        request.getRequestDispatcher("editarLibros.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Actualización de los datos del libro
        // Obtenemos los datos Del jsp
        Long idLibro = Long.parseLong(request.getParameter("id"));
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        request.setAttribute("usuarioLogueado", usuarioLogueado);
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int anio = Integer.parseInt(request.getParameter("anio"));
        String isbn = request.getParameter("isbn");
        String genero = request.getParameter("genero");
        boolean disponibilidad = Boolean.parseBoolean(request.getParameter("disponibilidad"));

        // Actualizar el libro
        control.actualizarLibro(idLibro, titulo, autor, anio, isbn, genero, disponibilidad);

        // Redirigir a la lista de libros
        response.sendRedirect("svLibros");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
