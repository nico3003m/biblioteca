package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Usuario;

@WebServlet(name = "SvListarUsuarios", urlPatterns = {"/SvListarUsuarios"})
public class SvListarUsuarios extends HttpServlet {

    // Instancia de la controladora que maneja la lógica
    Controladora controladora = new Controladora();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la lista de usuarios desde la base de datos a través de la controladora
        List<Usuario> listaUsuarios = controladora.obtenerUsuarios();
        
        // Guardar la lista de usuarios como atributo en la petición
        request.setAttribute("listaUsuarios", listaUsuarios);
        
        // Redirigir al JSP de gestión de usuarios
        request.getRequestDispatcher("gestionUsuarios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // También podemos manejar el POST, pero en este caso no es necesario
        doGet(request, response);
    }
}
