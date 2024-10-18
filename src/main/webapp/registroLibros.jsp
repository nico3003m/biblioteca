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
                                    <a class="nav-link" href="svReserva">Gestion Prestamos</a>
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
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Registro Libros </h3></div>
                                    <div class="card-body">
                                        <form action="svLibros" method="POST">
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputFirstName" name="nombre" type="text"   />
                                                        <label for="inputFirstName">Nombre del libro</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="inputLastName" name="autor"type="text"    />
                                                        <label for="inputLastName">Autor</label>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                            <div class="form-floating mb-3">
                                                <div class="form-floating">
                                                    <input class="form-control" id="inputLastName" name="anio"type="text"   />
                                                    <label for="inputLastName">Año de publicación</label>
                                                </div>

                                            </div>
                                            <div class="form-floating mb-3">
                                                <div class="form-floating">
                                                    <input class="form-control" id="inputLastName" name="isbn"type="text"   />
                                                    <label for="inputLastName">ISBN</label>
                                                </div>

                                            </div>

                                            <div class="form-floating mb-3">
                                                <select class="form-select"  name="genero"aria-label="Default select example" lab>
                                                    <option selected>Seleccione un Genero</option>
                                                    <option value="comedia">Comedia</option>
                                                    <option value="accion">Accion</option>
                                                    <option value="investigacion">Investigacion</option>                
                                                    <option value="suspenso">Suspenso</option>

                                                </select>
                                                <label for="inputLastName">Seleccione una opcion</label>
                                            </div>
                                          <div class="form-floating mb-3">
                                              <select class="form-select" name="disponibildad"aria-label="Default select example" lab>
                                                    <option selected>Disponibilidad</option>
                                                    <option value="true">Si</option>
                                                    <option value="false">No</option>
                                                </select>
                                                <label for="inputLastName">Seleccione una opcion</label>
                                            </div>

                                            <div class="mt-4 mb-0">

                                                <button type="submit" class="btn btn-primary">Crear Libro</button>

                                            </div>

                                        </form>


                                    </div>

                                </div>
                            </div>
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
