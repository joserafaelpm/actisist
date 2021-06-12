<%-- 
    Document   : registrarDocConf
    Created on : 9/06/2021, 05:49:04 PM
    Author     : dunke
--%>

<%@page import="ufps.edu.co.dto.Tipo"%>
<%@page import="ufps.edu.co.dto.TipoConferencista"%>
<%@page import="java.util.List"%>
<%@page import="ufps.edu.co.dto.TipoDocente"%>
<%@page import="ufps.edu.co.dto.SolicitudRegistro"%>
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
            SolicitudRegistro sr = ((SolicitudRegistro)request.getSession().getAttribute("sol"));
            if(sr==null){
                response.sendRedirect("index.jsp");
            }
        %>
        
        <form action="ControlUsuario?q=reg<%=sr.getTypeUs().getRol()%>" method="POST" enctype="multipart/form-data">
            <label for="name">Nombre</label>
            <input type="text" id="nam" name="nam"><br>
            <label for="ape">Apellido</label>
            <input type="text" id="ape" name="ape"><br>
            <label for="doc">Documento</label>
            <input type="text" id="doc" name="doc"><br>
            <%if(sr.getTypeUs().getId()==2){%>
            <label for="cod">Codigo</label>
            <input type="text" id="cod" name="cod"><br>
            <%}%>
            <select id="types" name="types">
            <%
                List<Tipo> types = ((List<Tipo>)request.getSession().getAttribute("types"));
                for(Tipo tp: types){
            %>
                <option value="<%=tp.getId()%>"><%=tp.getTipo()%></option>
            <%
                }
            %>
            </select><br>
            <%if(sr.getTypeUs().getId() == 2){%>
                <label for="pw">Contraseña</label>
                <input type="password" id="pw" name="pw"><br>
                <label for="image">Imagen de perfil</label>
                <input type="file" name="image" id="image">
            <%}else{%>
                <label>Afiliación</label>
                <select id="ins_exi" name="ins_exi">
                </select>
                <label>País de origen</label>
                <select id="pai" name="pai">
                </select>
            <%}%>
            <input type="submit">
        </form>
            
            <script>
                $(document).ready(function (){
                    load('ControlInstitucion?q=list', '#ins_exi');
                    load('ControlPaises?q=list', '#pai');
                });
                
                function load(control, compnt){
                $.post(control, {}, function(response){
                    $(compnt).html(response);
                });
            }
            </script>    
    </body>
</html>
