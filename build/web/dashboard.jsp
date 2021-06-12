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
        <a href="ControlUsuario?q=list">Registrar docente y/o conferencistas</a>
        <a href="ControlConvenio?q=redi">Registrar convenios</a>
        <a href="#">Generar graficas</a>
        <a href="misActividades.jsp">Registrar Actividades</a>
        <%
            }
        %>
        <form action="ControlUsuario?q=log" method="POST"><button type="submit">Cerrar sesión</button></form>
    </body>
</html>
