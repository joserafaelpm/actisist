<%@page import="ufps.edu.co.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Software | Dashboard</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <link href="css/ufps.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/main.css">

    </head>
    <body>
        <!--HEADER-->
        <div class="ufps-navbar" id="menu">
            <div class="ufps-container-fluid">
                <div class="ufps-navbar-brand">
                    <div class="ufps-btn-menu" onclick="toggleMenu('menu')">
                        <div class="ufps-btn-menu-bar"></div>
                        <div class="ufps-btn-menu-bar"> </div>
                        <div class="ufps-btn-menu-bar"> </div>
                    </div>
                </div><%
                    Usuario user = ((Usuario) request.getSession().getAttribute("user"));
                    if (user == null) {
                        response.sendRedirect("login.jsp");
                    }
                %>
                <div class="ufps-navbar-right">
                    <a href="dashboard.jsp" class="ufps-navbar-btn">Inicio</a>
                    <%if (user.getIdRol().getId() != 1) {%><a href="ControlActividad?q=showFor" class="ufps-navbar-btn">Mis Actividades</a><%}%>
                    <div class="ufps-dropdown" id="dropdown4">
                        <div class="ufps-dropdown-content">
                            <%if (user.getIdRol().getId() != 1) {%><a href="miPerfil.jsp">Mi Perfil</a><%}%>
                            <a href="ControlUsuario?q=log">Cerrar Sesion</a>
                        </div>
                    </div>
                    <%if (user.getIdRol().getId() == 1) {%>
                    <a onclick="openDropdown('dropdown4')"  class="ufps-navbar-btn ufps-dropdown-btn"><%=user.getNombre()%><img class="ufps-perfil-redonde" src="img/admin.png"/></a>
                        <%} else {%>
                    <a onclick="openDropdown('dropdown4')"  class="ufps-navbar-btn ufps-dropdown-btn"><%=user.getNombre() + " " + user.getApellido()%> <img class="ufps-perfil-redonde" src="<%=user.getDocente().encodeImage() %>"/></a>
                        <%}%>
                </div>
                <div class="ufps-navbar-left">
                    <div class="ufps-navbar-corporate">
                        <img src="img/logo_ufps_inverted.png" alt="Logo UFPS">
                    </div>
                </div>
            </div>
        </div>
        <!--FIN HEADER-->
        <div class="ufps-container ">  
            <%
                if (user.getIdRol().getRol().equalsIgnoreCase("admin")) {
            %>
            <div class="ufps-rafael-titulo-adming ">
                <h1>DASHBOARD ADMINISTRATIVA</h1>
            </div>
            <div class="ufps-row" >
                <div class="ufps-col-mobile-12 ufps-col-netbook-4" >
                    <a href="ControlUsuario?q=list">
                        <div class="ufps-option-adm">
                            <div class="icono"><i class="fa fa-user fa-4x"></i></div>
                            <div class="titulo">Registrar docente y/o conferencista</div>
                        </div>
                    </a>

                </div >
                <div class="ufps-col-mobile-12 ufps-col-netbook-4" >

                    <a href="ControlActividad?q=show">
                        <div class="ufps-option-adm">
                            <div class="icono"><i class="fa fa-list fa-4x"></i></div>
                            <div class="titulo">Ver actividades registradas</div>
                        </div>
                    </a>
                </div >
                <div class="ufps-col-mobile-12 ufps-col-netbook-4" > 
                    <a href="ControlConvenio?q=list">
                        <div class="ufps-option-adm">
                            <div class="icono"><i class="fa fa-handshake fa-4x"></i></div>
                            <div class="titulo">Registrar Convenio</div>
                        </div>
                    </a>
                </div >
            </div >
            <%
                }
            %>
        </div>
    </div>
    <div class="ufps-footer ufps-footer-2">
        <h3>Universidad Francisco de Paula Santander</h3>
        <p>Programa Ingeniería de Sistemas</p>
        <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
    </div>
    <script src="js/ufps.min.js"></script>
</body>
</html>
