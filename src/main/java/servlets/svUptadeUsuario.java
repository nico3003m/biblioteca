/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Usuario;
import static utilitis.seguridad.encrypt;

/**
 *
 * @author Nicolas Moreno
 */
@WebServlet(name = "svUptadeUsuario", urlPatterns = {"/svUptadeUsuario"})
public class svUptadeUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet svUptadeUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet svUptadeUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String correo = request.getParameter("correo");
    String contrasenia1 = request.getParameter("contrasenia1");

    // Obtener el usuario logueado de la sesión
    Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("datosUsuario");
    
    // Asegurarse de que el usuario está logueado
    if (usuarioLogueado != null) {
        String secretKey = "1234567890123456";  // La clave debe tener 16 caracteres (128 bits)

        // Encriptar la contraseña si se ha ingresado una nueva
        String encryptedPassword = null;
        if (contrasenia1 != null && !contrasenia1.isEmpty()) {
            try {
                encryptedPassword = encrypt(contrasenia1, secretKey);
            } catch (Exception ex) {
                Logger.getLogger(svUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Actualizar los datos del usuario logueado
        Controladora controladoraClass = new Controladora();
        Usuario usuarioActualizado = controladoraClass.actualizarUsuario(
            usuarioLogueado.getId_usuario(),  // Asegúrate de pasar el ID del usuario existente
            nombre,
            apellido,
            correo,
            encryptedPassword != null ? encryptedPassword : usuarioLogueado.getContrasena()  // Mantener la contraseña actual si no se cambió
        );

        // Actualizar la sesión con el usuario modificado
        request.getSession().setAttribute("datosUsuario", usuarioActualizado);
        response.sendRedirect("index.jsp?status=success");
    } else {
        response.sendRedirect("login.jsp");  // Redirigir a la página de inicio de sesión si el usuario no está logueado
    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
