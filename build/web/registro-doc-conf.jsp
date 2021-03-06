<%-- 
    Document   : registrarDocConf
    Created on : 9/06/2021, 05:49:04 PM
    Author     : dunke
--%>

<%@page import="ufps.edu.co.dto.Rol"%>
<%@page import="ufps.edu.co.util.Conexion"%>
<%@page import="ufps.edu.co.dao.TipoConferencistaJpaController"%>
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
        <title>Registro SISRECA</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ufps.min.css">
        <script src="<%=request.getContextPath()%>/js/JQuery.js"></script>
    </head>
    <body style="position: relative;">
        <%
            SolicitudRegistro sr = ((SolicitudRegistro) request.getSession().getAttribute("sol"));
            if (sr == null) {
                response.sendRedirect("index.jsp");
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
                    <a href="index.jsp" class="ufps-navbar-btn">Inicio</a>
                    <a href="login.jsp" class="ufps-navbar-btn">Inicio Sesion</a>
                </div>
                <div class="ufps-navbar-left">
                    <div class="ufps-navbar-corporate">
                        <img src="img/logo_ufps_inverted.png" alt="Logo UFPS">
                    </div>
                </div>
            </div>
        </div>

        <div class="ufps-container-fluid" style="padding: 0 200px 0 200px;">
            <div class="ufps-section-form">
                <div class="ufps-title-section">
                    <%if (sr.getTypeUs().getId() == 2) {%>
                    Registrar Docente
                    <%}else{%>
                    Registro de Conferencistas
                    <%}%>
                </div>
                <div class="ufps-body-section ufps-padding-5">
                    <form action="<%=request.getContextPath()%>/ControlUsuario?q=reg" method="POST" enctype="multipart/form-data" accept-charset="ISO-8859-1">
                        <div class="label"><label class="ufps-title-input" for="name">Nombre</label></div>
                        <input type="text" id="nam" name="nam" class="ufps-input-line" required>
                        <div class="label"><label class="ufps-title-input" for="ape">Apellidos</label></div>
                        <input type="text"  id="ape" name="ape" class="ufps-input-line" required>
                        <div class="label"><label class="ufps-title-input" for="doc">Documento</label></div>
                        <input type="text"   id="doc" name="doc" class="ufps-input-line" required>
                        <%if (sr.getTypeUs().getId() == 2) {%>
                        <div class="label"><label class="ufps-title-input" for="cod">Codigo</label></div>
                        <input type="text"  id="cod" name="cod" class="ufps-input-line" required>
                        <%}%>

                        <div class="label"><label class="ufps-title-input">Tipo</label></div>
                        <select class="ufps-input-line" id="types" name="types">
                            <%
                                List<Tipo> types = ((List<Tipo>) request.getSession().getAttribute("types"));
                                for (Tipo tp : types) {
                            %>
                            <option value="<%=tp.getId()%>"><%=tp.getTipo()%></option>
                            <%
                                }
                            %>
                        </select>
                        <%if (sr.getTypeUs().getId() == 2) {%>
                        <label class="ufps-title-input" for="pw">Contrase??a</label>
                        <input type="password" class="ufps-input-line" id="pw" name="pw" ><br>
                        <label class="ufps-title-input" for="image">Imagen de perfil</label>
                        <input type="file" class="ufps-input-line" name="image" id="image">
                        <%} else {%>
                        <label class="ufps-title-input">Afiliaci??n</label>
                        <select class="ufps-input-line" id="ins_exi" name="ins_exi">
                        </select>
                        <label class="ufps-title-input">Pa??s de origen</label>
                        <select class="ufps-input-line" id="pai" name="pai">
                        </select>
                        <%}%>
                        <input type="submit"  class="ufps-btn ufps-width-100 ufps-margin-top-10" value="Registrar">
                    </form>
                </div>
            </div>
        </div >

        <!--FOOTER-->
        <jsp:include page="/templates/includes/footer.jsp" />
        <!--FIN FOOTER-->
        
        <script>
            $(document).ready(function () {
                load('<%=request.getContextPath()%>/ControlInstitucion?q=list', '#ins_exi');
                load('<%=request.getContextPath()%>/ControlPaises?q=list', '#pai');
            });

            function load(control, compnt) {
                $.post(control, {}, function (response) {
                    $(compnt).html(response);
                });
            }
        </script>
        
        <script src="<%=request.getContextPath()%>/js/ufps.min.js"></script>
    </body>
</html>
