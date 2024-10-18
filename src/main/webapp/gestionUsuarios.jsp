<%@page import="java.util.List"%>
<%@page import="logica.Usuario"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file='layouts/header.jsp'%>
    <body class="sb-nav-fixed">
        <%@include file='layouts/nav.jsp'%>

        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <!-- Navegación lateral -->
                    <%@include file="layouts/sidebar.jsp" %>
                </nav>
            </div>

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Gestión de Usuarios</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Lista de Usuarios
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Email</th>
                                            <th>Rol</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            // Obtener la lista de usuarios enviada desde el servlet
                                            List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
                                            if (listaUsuarios != null) {
                                                for (Usuario usuario : listaUsuarios) {
                                        %>
                                        <tr>
                                            <td><%= usuario.getIdUsuario() %></td>
                                            <td><%= usuario.getNombre() %></td>
                                            <td><%= usuario.getEmail() %></td>
                                            <td><%= usuario.getRol() %></td>
                                            <td>
                                                <a href="SvEditarUsuario?id=<%= usuario.getIdUsuario() %>" class="btn btn-primary">Editar</a>
                                                <a href="SvEliminarUsuario?id=<%= usuario.getIdUsuario() %>" class="btn btn-danger">Eliminar</a>
                                            </td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <%@include file="layouts/footer.jsp" %>
            </div>
        </div>

        <!-- Scripts para la tabla -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
