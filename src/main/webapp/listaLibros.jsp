<%@page import="utilitis.seguridad"%>
<%@page import="logica.Libro"%>
<%@page import="java.util.List"%>
<%@page import="logica.Usuario"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file='layouts/header.jsp'%>
    <body class="sb-nav-fixed">
        <%@include file='layouts/nav.jsp'%>

        <%
            // Inicializar el usuario una sola vez al inicio
            Usuario usuarioLogueado = (Usuario) session.getAttribute("datosUsuario");
        %>

        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <a class="nav-link" href="index.jsp">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Perfil
                            </a>

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseCasos" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Libros
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseCasos" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="registroLibros.jsp">Registro Libros</a>
                                    <a class="nav-link" href="svLibros">Listado Libros</a>
                                </nav>
                            </div>

                            <a class="nav-link" href="SvReservaLibros">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Solicitar Prestamos
                            </a>

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseAbogados" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Administración
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseAbogados" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="layout-static.html">Gestion Usuarios</a>
                                    <a class="nav-link" href="layout-sidenav-light.html">Gestion Prestamos</a>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">
                            Usuario Nombre : 
                            <%= (usuarioLogueado != null) ? usuarioLogueado.getNombre() : "Usuario no identificado"%>
                        </div>
                        Correo Electronico <%=usuarioLogueado.getEmail()%>
                    </div>
                </nav>
            </div>

            <div id="layoutSidenav_content">
                <main>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            Lista de libros
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Autor</th>
                                        <th>Año</th>
                                        <th>Isb</th>
                                        <th>Genero </th>
                                        <th>Disponible </th>

                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Autor</th>
                                        <th>Año</th>
                                        <th>Isb</th>
                                        <th>Genero </th>
                                        <th>Disponible </th>

                                    </tr>
                                </tfoot>
                                <tbody>
                                    <%
                                        // Obtén la lista de libros que fue enviada desde el servlet
                                        List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros");

                                        if (listaLibros != null) {
                                            for (Libro libro : listaLibros) {
                                                String disponible = String.valueOf(libro.isDisponibilidad());
                                                seguridad mascaras = new seguridad();
                                                String dataMostrar = mascaras.mascara(disponible);

                                    %>
                                    <tr>
                                        <td><%= libro.getTitulo()%></td>
                                        <td><%= libro.getAutor()%></td>
                                        <td><%= libro.getAno()%></td>
                                        <td><%= libro.getISBN()%></td>
                                        <td><%= libro.getGenero()%></td>
                                        <td><%=dataMostrar%></td>

                                    </tr>
                                    <%
                                            }
                                        }
                                    %>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </main>

                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Biblioteca La esquina 2024</div>

                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
