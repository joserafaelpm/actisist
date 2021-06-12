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
    </head>
    <body>
        <%
            
            List<Rol> roles = ((List<Rol>)request.getSession().getAttribute("rjpa"));
            List<Usuario> users = ((List<Usuario>)request.getSession().getAttribute("ujpa"));;
            Usuario user = ((Usuario)request.getSession().getAttribute("user"));
            if(user==null || !user.getIdRol().getRol().equalsIgnoreCase("admin")){response.sendRedirect("login.jsp");}
        %>
        <form action="ControlUsuario?q=reg_sol" method="POST">
            <input type="email" placeholder="some_email@mail.co" id="mail_us" name="mail_us">
            <select id="docOrConf" name="docOrConf" onchange="change_table()">
                <%
                    for(Rol r: roles){
                        if(r.getId()!=1){
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
            for(Usuario u : users){
                if(u.getDocente()!=null){
        %>
            <tr>
                <td><%=u.getNombre()+" "+u.getApellido()%></td>
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
            for(Usuario u : users){
                Conferencista c = u.getConferencista();
                if(c!=null){
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
            $(document).ready(function (){
               change_table(); 
            });
            
            function change_table(){
                if($('#docOrConf').val() == 3){
                    $('#tDoc').hide();
                    $('#tConf').show();
                }else{
                    $('#tDoc').show();
                    $('#tConf').hide();
                }
            }
        </script>
    </body>
</html>
