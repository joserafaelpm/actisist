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
        <title>Registrar Docente y/o Conferencista</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ufps.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <script src="<%=request.getContextPath()%>/js/JQuery.js"></script>
    </head>
    <body style="position: relative; height: 100vh;">
        <%
            List<Rol> roles = ((List<Rol>) request.getSession().getAttribute("rjpa"));
            List<Usuario> users = ((List<Usuario>) request.getSession().getAttribute("ujpa"));;
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            if (user == null || !user.getIdRol().getRol().equalsIgnoreCase("admin")) {
                response.sendRedirect("login.jsp");
            }
        %>

        <!--HEADER-->
        <jsp:include page="/templates/includes/header.jsp" />
        <!--FIN HEADER-->

        <div class="ufps-section-form" style="margin-left: 70px; margin-right: 70px;">
            <div class="ufps-title-section">
                Tabla de Docentes y/o Conferencistas Registrados
            </div>
            <div class="ufps-body-section-80 ufps-padding-5">
                <form action="<%=request.getContextPath()%>/ControlUsuario?q=reg_sol" method="POST">
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
                        <th>Acciones</th>
                    </tr>
                    <%
                        for (Usuario u : users) {
                            if (u.getDocente() != null) {
                    %>
                    <tr>
                        <td><%=u.getNombre() + " " + u.getApellido()%></td>
                        <td><%=u.getDocente().getCodigo()%></td>
                        <td><%if(u.getDocente().getActivo()){%>Activo<%}else{%>Inactivo<%}%> </td>
                        <td><a href="<%=request.getContextPath()%>/ControlActividad?q=list&id_user=<%=u.getDni()%>">Ver actividades</a></td>
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
                        <td><a href="<%=request.getContextPath()%>/ControlActividad?q=list&id_user=<%=u.getDni()%>">Ver actividades</a></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
        </div>
                
        <!--FOOTER-->
        <jsp:include page="/templates/includes/footer.jsp" />
        <!--FIN FOOTER-->

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
        <script src="<%=request.getContextPath()%>/js/ufps.min.js"></script>
    </body>
</html>
