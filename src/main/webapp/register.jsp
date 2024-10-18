<%-- 
    Document   : register
    Created on : 16/10/2024, 8:41:41 p. m.
    Author     : Nicolas Moreno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Register - SB Admin</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Crear Cuenta</h3></div>
                                    <div class="card-body">
                                        <form action="svUsuario" method="POST">
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputFirstName" name="nombre" type="text" placeholder="Enter your first name" />
                                                        <label for="inputFirstName">Nombre</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="inputLastName" name="apellido"type="text" placeholder="Enter your last name" />
                                                        <label for="inputLastName">Apeliido</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputEmail" name="correo" type="email" placeholder="name@example.com" />
                                                <label for="inputEmail">Correo Electronico</label>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input 
                                                            class="form-control" 
                                                            id="inputPassword" 
                                                            name="contrasenia1" 
                                                            type="password" 
                                                            placeholder="Create a password" 
                                                            pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}" 
                                                            title="La contraseña debe tener al menos 8 caracteres, incluyendo al menos una letra mayúscula, una letra minúscula, un número y un carácter especial (@#$%^&+=!)" 
                                                            required
                                                            />
                                                        <label for="inputPassword">Contraseña</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPasswordConfirm" name="contrasenia2"  type="password" placeholder="Confirm password" />
                                                        <label for="inputPasswordConfirm">Contraseña</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <%
                                                String error = (String) request.getAttribute("error");
                                                if (error != null) {
                                            %>
                                            <div class="alert alert-danger" role="alert" style="margin: 20px">
                                                Las contraseñas nos son iguales 
                                            </div>
                                            <%
                                                }
                                            %>
                                            <div class="mt-4 mb-0">
                                                <button type="submit" class="btn btn-primary">Crear Cuenta</button>
                                            </div>
                                            <%
                                                String errorEmail = (String) request.getAttribute("errorEmail");
                                                if (errorEmail != null) {
                                            %>
                                            <div class="alert alert-danger" role="alert" style="margin: 20px">
                                                <%= errorEmail%>
                                            </div>
                                            <%
                                                }
                                            %>

                                        </form>


                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="login.jsp">Ya estas Registrado , Entra aca !</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
