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
    </head>
    <body>
        <%
            Usuario user = ((Usuario)request.getSession().getAttribute("user"));
            if(user==null){response.sendRedirect("login.jsp");}
        %>
        <h1><%=user.getNombre()+" "+user.getApellido()%></h1>
        <%
            if(user.getIdRol().getRol().equalsIgnoreCase("admin")){
        %>
            <a href="ControlUsuario?q=list">Registrar docente y/o conferencistas</a>
            <a href="ControlConvenio?q=redi">Registrar convenios</a>
            <a href="#">Generar graficas</a>
        <%
            }
        %>
        <form action="ControlUsuario?q=log" method="POST"><button type="submit">Cerrar sesión</button></form>
    </body>
</html>
