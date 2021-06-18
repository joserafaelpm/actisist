<%-- 
    Document   : verActividades
    Created on : 13-jun-2021, 20:35:44
    Author     : Familia Pena Mena
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="ufps.edu.co.dto.Actividad"%>
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
        <link rel="stylesheet" href="css/jquery.transfer.css">
        <link rel="stylesheet" href="icon_font/css/icon_font.css">
        <script src="js/JQuery.js"></script>
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
                </div>
                <%
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    List<Actividad> acts = (List<Actividad>)request.getSession().getAttribute("acts");
                    Usuario user = ((Usuario) request.getSession().getAttribute("user"));
                    if (user == null) {
                        response.sendRedirect("login.jsp");
                    }
                %>
                <div class="ufps-navbar-right">
                    <a href="dashboard.jsp" class="ufps-navbar-btn">Inicio</a>
                    <%if (user.getIdRol().getId() != 1) {%><a href="misActividades.jsp" class="ufps-navbar-btn">Mis Actividades</a><%}%>
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
        
        <div class="ufps-container-fluid ">
            <div class="ufps-row ufps-margin-top-10" >
                <div class="ufps-col-mobile-12 ufps-col-netbook-3" > 
                    <div class="ufps-card">
                        <div class="ufps-card-caption ufps-padding-30">
                            <div class="ufps-div-title">
                                <label>Buscar</label>
                            </div>
                            <div class="label"><label class="ufps-title-input">Nombre</label></div>
                            <input type="text"  name="nombre" id="nombreData" class="ufps-input-line" required>
                            <div class="label"><label class="ufps-title-input">Fecha</label></div>
                            <input type="date"  name="fecha" id="fechaData"  value="<%=sd.format(date) %>" class="ufps-input-line" required>
                            <div class="label"><label class="ufps-title-input">Tipo</label></div>
                            <input type="text"  name="hora" id="horaData" class="ufps-input-line" required>
                            <div class="label"><label class="ufps-title-input">Lugar</label></div>
                            <input type="text"  name="lugar" id="lugarData" class="ufps-input-line" required>
                            <div class="label"><label class="ufps-title-input">Docente</label></div>
                            <input type="text"  name="docente" id="docenteData" class="ufps-input-line" required>
                            <a href="registrarActividad.jsp"  class="ufps-tx-center ufps-btn ufps-width-100 ufps-margin-top-10">Registrar Actividad</a>
                            <form action="ControlActivida?q=info" method="POST">
                                <%for(Actividad a: acts){%>
                                <input type="hidden" name="act" id="<%=a.getId() %>" value="<%=a.getId() %>-false">
                                <%}%>
                                <input value="Generar Informe" class="ufps-btn ufps-width-100 ufps-margin-top-10 ufps-tx-center">
                            </form>

                        </div>
                    </div>   
                </div >
                <div class="ufps-col-mobile-12 ufps-col-netbook-9" >
                    <div class="ufps-card">
                        <div class="ufps-card-caption ufps-padding-5">
                            <div class="ufps-table-responsive">
                                <table id="example" class="display" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Nombre</th>
                                            <th>Fecha</th>
                                            <th>Tipo</th>
                                            <th>Lugar</th>
                                            <th>Docente Encargado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for(Actividad a: acts){%>
                                        <tr>
                                            <td style="text-align: center;"><input type="checkbox" id="<%=a.getId()+""+a.getId() %>" onchange="editAct(<%=a.getId() %>)"></td>
                                            <td style="text-align: center;"><%=a.getNombre() %></td>
                                            <td style="text-align: center;"><%=sd.format(a.getFechaInicio()) %></td>
                                            <td style="text-align: center;"><%=a.getTipoActividadId().getTipo() %></td>
                                            <td style="text-align: center;"><%=a.getLugar() %></td>
                                            <td style="text-align: center;"><%=a.getUsuarioDni().getNombre()+" "+a.getUsuarioDni().getApellido() %></td>
                                        </tr>    
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>  
                        </div>
                    </div>
                </div >
            </div >
        </div>
        <div class="ufps-footer">
            <h3>Universidad Francisco de Paula Santander</h3>
            <p>Programa Ingeniería de Sistemas</p>
            <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
        </div>  
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
        <script src="js/dataTables.min.js"></script>
        <script src="js/ufps.min.js"></script>
        <script src="js/main.js"></script>
        
        <script>
            function editAct(id){
                var value = $('#'+id+""+id).is(":checked");
                $('#'+id).val(id+"-"+value);
            }
        </script>
    </body>
</html>
