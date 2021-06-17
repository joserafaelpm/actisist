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
        <%
            List<Rol> roles = ((List<Rol>) request.getSession().getAttribute("rjpa"));
            List<Usuario> users = ((List<Usuario>) request.getSession().getAttribute("ujpa"));;
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            if (user == null || !user.getIdRol().getRol().equalsIgnoreCase("admin")) {
                response.sendRedirect("login.jsp");
            }
        %>
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
                    <a href="dashboard.jsp" class="ufps-navbar-btn">Inicio</a>
                    <%if (user.getIdRol().getId() != 1) {%><a href="misActividades.jsp" class="ufps-navbar-btn">Mis Actividades</a><%}%>
                    <div class="ufps-dropdown" id="dropdown4">
                        <div class="ufps-dropdown-content">
                            <%if (user.getIdRol().getId() != 1) {%><a href="#">Mi Perfil</a><%}%>
                            <a href="ControlUsuario?q=log">Cerrar Sesion</a>
                        </div>
                    </div>
                    <%if (user.getIdRol().getId() == 1) {%>
                    <a onclick="openDropdown('dropdown4')"  class="ufps-navbar-btn ufps-dropdown-btn"><%=user.getNombre()%><img class="ufps-perfil-redonde" src="img/admin.png"/></a>
                        <%} else {%>
                    <a onclick="openDropdown('dropdown4')"  class="ufps-navbar-btn ufps-dropdown-btn"><%=user.getNombre() + " " + user.getApellido()%> <img class="ufps-perfil-redonde" src="<%=user.getDocente().getImagenPerfil()%>"/></a>
                        <%}%>
                </div>
                <div class="ufps-navbar-left">
                    <div class="ufps-navbar-corporate">
                        <img src="img/logo_ufps_inverted.png" alt="Logo UFPS">
                    </div>
                </div>
            </div>
        </div>

        <div class="ufps-row" >
            <div class="ufps-col-mobile-12 ufps-margin-top-10 ufps-col-netbook-9" >
                <div class="ufps-section-form">
                    <div class="ufps-title-section">
                        Tabla de Docentes y/o Conferencistas Registrados
                    </div>
                    <div class="ufps-body-section-80 ufps-padding-5">
                        <form action="ControlUsuario?q=reg_sol" method="POST">
                            <div class="ufps-tooltip ufps-input-line">
                                <span class="ufps-tooltip-content-bottom">Ingresar el email del usuario a registrar</span>
                                <input type="email" placeholder="some_email@mail.co" id="mail_us" name="mail_us" class="ufps-input-line">
                            </div>
                            <select id="docOrConf" name="docOrConf" onchange="change_table()" class="ufps-btn">
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
                            <input type="submit" class="ufps-btn" value="Registrar">
                        </form>

                        <table id="tDoc" border="1" style="width: 100%" class="ufps-margin-top-20  ufps-table ufps-table-responsive ufps-table-inserted ufps-text-center">
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
                                <td><%=u.getDocente().getActivo()%> </td>
                                <td><a href="ControlActividad?q=list&id_user=<%=u.getDni()%>">Ver actividades</a></td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </table>
                        <table id="tConf" border="1" hidden border="1" style="width: 100%" class="ufps-margin-top-20  ufps-table ufps-table-responsive ufps-table-inserted ufps-text-center">
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

                    </div>
                </div>
            </div>
        </div>

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
        <div class="ufps-footer ">
            <h3>Universidad Francisco de Paula Santander</h3>
            <p>Programa Ingeniería de Sistemas</p>
            <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
        </div> 
        <script src="js/ufps.min.js"></script>
    </body>
</html>
