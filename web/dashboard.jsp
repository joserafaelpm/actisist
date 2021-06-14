<%-- 
    Document   : dashboard
    Created on : 8/06/2021, 02:26:19 PM
    Author     : dunke
--%>

<%@page import="ufps.edu.co.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/ufps.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>

        <div class="ufps-navbar" id="menu">
            <div class="ufps-container-fluid">
                <div class="ufps-navbar-brand">
                    <div class="ufps-btn-menu" onclick="toggleMenu('menu')">
                        <div class="ufps-btn-menu-bar"></div>
                        <div class="ufps-btn-menu-bar"> </div>
                        <div class="ufps-btn-menu-bar"> </div>
                    </div>
                </div>
                <div class="ufps-navbar-right">
                    <a href="index.jsp" class="ufps-navbar-btn">Inicio</a>
                    <a href="misActividades.jsp" class="ufps-navbar-btn">Mis Actividades</a>
                    <a onclick="openDropdown('dropdown4')"  class="ufps-navbar-btn ufps-dropdown-btn">Hecttor Parra <img class="ufps-perfil-redonde" src="img/user.jpg"/></a>
                </div>
                <div class="ufps-dropdown" id="dropdown4">
                    <div class="ufps-dropdown-content">
                        <a href="#">Opción 1</a>
                        <a href="#">Opción 2</a>
                        <a href="#">Opción 3</a>
                    </div>
                </div>
                <div class="ufps-navbar-left">
                    <div class="ufps-navbar-corporate">
                        <img  src="img/logo_ufps_inverted.png" alt="Logo UFPS">
                    </div>
                </div>

            </div>
        </div>


        
        <div class="ufps-title">
            <h1>DASHBOARD</h1>
        </div>

        <%
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            if (user == null) {
                response.sendRedirect("login.jsp");
            }
        %>
        <h1><%=user.getNombre() + " " + user.getApellido()%></h1>
        <%
            if (user.getIdRol().getRol().equalsIgnoreCase("admin")) {
        %>
        <div class="ufps-card-caption"> 
            <li> <a href="ControlUsuario?q=list"><i class="fa fa-user"></i>Registrar Docente y/o Conferencistas</a></li>
            <li><a href="verActividades.jsp"><i class="fa fa-list"></i> Ver actividades Registradas</a></li>
            <li><a href="ControlConvenio?q=redi"><i class="fa fa-handshake"></i> Registrar Convenio</a></li>
            <li><a href=""><i class="fa fa-signal"></i> Generar Graficas</a></li>
        </div>
        <%
            }
        %>
        <form action="ControlUsuario?q=log" method="POST"><button type="submit" class="ufps-margin-top-10">Cerrar sesión</button></form>


        <div class="ufps-footer">
            <h3>Universidad Francisco de Paula Santander</h3>
            <p>Programa Ingeniería de Sistemas</p>
            <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
        </div> 
    </body>
</html>
