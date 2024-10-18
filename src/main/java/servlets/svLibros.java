/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Libro;

/**
 *
 * @author Nicolas Moreno
 */
@WebServlet(name = "svLibros", urlPatterns = {"/svLibros"})
public class svLibros extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("entro al get ");
        Controladora controladoraClass = new Controladora();
        
        List<Libro> listaLibros =  controladoraClass.obtenerLibros();
        
        for (Libro listaLibro : listaLibros) {
            System.out.println("lista libros"+ listaLibro.getAutor());
        }
        request.setAttribute("listaLibros", listaLibros);
        request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String autor = request.getParameter("autor");
        int anio = Integer.parseInt(request.getParameter("anio"));
        String isbn = request.getParameter("isbn");
        String genero = request.getParameter("genero");
        boolean disponible =Boolean.parseBoolean(request.getParameter("disponibildad"));
        
        Controladora controladoraClss = new Controladora();
        
        controladoraClss.crearLibros(nombre, autor, anio, isbn, genero, disponible);
        
        response.sendRedirect("registroLibros.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
