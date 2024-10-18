package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;
import static utilitis.seguridad.decrypt;
import static utilitis.seguridad.encrypt;

/**
 *
 * @author Nicolas Moreno
 */
@WebServlet(name = "svUsuario", urlPatterns = {"/svUsuario"})
public class svUsuario extends HttpServlet {

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
            out.println("<title>Servlet svUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet svUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");

        Controladora controladoraClass = new Controladora();
        boolean acceso = false;

        try {
            acceso = controladoraClass.obtenerUsuarios(correo, contrasenia);
        } catch (Exception ex) {
            Logger.getLogger(svUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (acceso) {
            HttpSession session = request.getSession();
            Usuario usu = controladoraClass.ObtenerUsuarioSession(correo, contrasenia);
            session.setAttribute("datosUsuario", usu);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("credencial", "Credenciales invalidas");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombres = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String contrasenia1 = request.getParameter("contrasenia1");
        String contrasenia2 = request.getParameter("contrasenia2");
        String rol = ""; // Asigna el rol que desees

        Controladora controladoraClass = new Controladora();

        // Verificar si el correo ya está en uso
        if (controladoraClass.existeUsuarioPorEmail(correo)) {
            request.setAttribute("errorEmail", "El correo electrónico ya está en uso.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!contrasenia1.equals(contrasenia2)) {
            request.setAttribute("error", "Las contraseñas no son iguales");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            String secretKey = "1234567890123456";  // La clave debe tener 16 caracteres (128 bits)

            // Encriptar la contraseña
            String encryptedPassword = null;
            try {
                encryptedPassword = encrypt(contrasenia1, secretKey);
            } catch (Exception ex) {
                Logger.getLogger(svUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            controladoraClass.crearUsuario(nombres, apellido, correo, encryptedPassword, rol);
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
