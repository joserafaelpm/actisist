<%-- 
    Document   : registroDocConf
    Created on : 9/06/2021, 02:27:01 PM
    Author     : dunke
--%>

<%@page import="ufps.edu.co.dto.Rol"%>
<%@page import="ufps.edu.co.dao.RolJpaController"%>
<%@page import="ufps.edu.co.dto.Conferencista"%>
<%@page import="java.util.List"%>
<%@page import="ufps.edu.co.util.Conexion"%>
<%@page import="ufps.edu.co.dao.UsuarioJpaController"%>
<%@page import="ufps.edu.co.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/JQuery.js"></script>
        <link href="css/ufps.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="ufps-navbar ufps-navbar-delete_margin" id="menu">
            <div class="ufps-container-fluid">
                <div class="ufps-navbar-brand">
                    <div class="ufps-btn-menu" onclick="toggleMenu('menu')">
                        <div class="ufps-btn-menu-bar"></div>
                        <div class="ufps-btn-menu-bar"> </div>
                        <div class="ufps-btn-menu-bar"> </div>
                    </div>
                </div>
                <div class="ufps-navbar-right">
                    <a href="dashboard.jsp" class="ufps-navbar-btn">Inicio</a>
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

        <%
            List<Rol> roles = ((List<Rol>) request.getSession().getAttribute("rjpa"));
            List<Usuario> users = ((List<Usuario>) request.getSession().getAttribute("ujpa"));;
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            if (user == null || !user.getIdRol().getRol().equalsIgnoreCase("admin")) {
                response.sendRedirect("login.jsp");
            }
        %>
        <form action="ControlUsuario?q=reg_sol" method="POST">
            <input type="email" placeholder="some_email@mail.co" id="mail_us" name="mail_us">
            <select id="docOrConf" name="docOrConf" onchange="change_table()">
                <%
                    for (Rol r : roles) {
                        if (r.getId() != 1) {
                %>
                <option value=<%=r.getId()%>><%=r.getRol()%></option>
                <%
                        }
                    }
                %>
            </select>
            <input type="submit">
        </form>

        <table id="tDoc" border="1">
            <tr>
                <th>Nombre</th>
                <th>Codigo</th>
                <th>Estado</th>
                <th>Ver actividades</th>
            </tr>
            <%
                for (Usuario u : users) {
                    if (u.getDocente() != null) {
            %>
            <tr>
                <td><%=u.getNombre() + " " + u.getApellido()%></td>
                <td><%=u.getDocente().getCodigo()%></td>
                <td><%=u.getDocente().getActivo()%></td>
                <td><a href="ControlActividad?q=list&id_user=<%=u.getDni()%>">Ver actividades</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <table id="tConf" border="1" hidden>
            <tr>
                <th>Nombre</th>
                <th>Afiliación</th>
                <th>Pais</th>
                <th>Tipo</th>
                <th>Ver actividades</th>
            </tr>
            <%
                for (Usuario u : users) {
                    Conferencista c = u.getConferencista();
                    if (c != null) {
            %>
            <tr>
                <td><%=u.getNombre()%></td>
                <td><%=c.getInstitucionId().getNombre()%></td>
                <td><%=c.getPaisOrigen().getNombre()%></td>
                <td><%=c.getTipoConferencistaId().getTipo()%></td>
                <td><a href="ControlActividad?q=list&id_user=<%=u.getDni()%>">Ver actividades</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>

        <script>
            $(document).ready(function () {
                change_table();
            });

            function change_table() {
                if ($('#docOrConf').val() == 3) {
                    $('#tDoc').hide();
                    $('#tConf').show();
                } else {
                    $('#tDoc').show();
                    $('#tConf').hide();
                }
            }
        </script>
        <div class="ufps-footer">
            <h3>Universidad Francisco de Paula Santander</h3>
            <p>Programa Ingeniería de Sistemas</p>
            <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
        </div> 
    </body>
</html>
