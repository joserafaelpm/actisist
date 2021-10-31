<%-- 
    Document   : registrarActividad
    Created on : 10/06/2021, 05:32:27 PM
    Author     : dunke
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="ufps.edu.co.dto.Actividad"%>
<%@page import="ufps.edu.co.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
        <title>SISRECA | UFPS</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ufps.min.css">
        <script src="<%=request.getContextPath()%>/js/JQuery.js"></script>
    </head>
    <body style="position: relative; height: 100vh;">
        <%
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            List<Actividad> acts = (List<Actividad>) request.getSession().getAttribute("acts");
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            if (user == null) {
                response.sendRedirect("login.jsp");
            }
        %>
        
        <!--HEADER-->
        <jsp:include page="/templates/includes/header.jsp" />
        <!--FIN HEADER-->
        
        <div class="ufps-container-fluid">
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
                            <input type="date"  name="fecha" id="fechaData"  value="<%=sd.format(date)%>" class="ufps-input-line" required>
                            <div class="label"><label class="ufps-title-input">Tipo</label></div>
                            <input type="text"  name="hora" id="horaData" class="ufps-input-line" required>
                            <div class="label"><label class="ufps-title-input">Lugar</label></div>
                            <input type="text"  name="lugar" id="lugarData" class="ufps-input-line" required>
                            <a href="<%=request.getContextPath()%>/ControlActividad?q=redi"  class="ufps-tx-center ufps-btn ufps-width-100 ufps-margin-top-10">Registrar Actividad</a>
                            <form action="<%=request.getContextPath()%>/ControlActividad?q=info" method="POST" target="_blank">
                                <%for (Actividad a : acts) {%>
                                <input type="hidden" name="act" id="<%=a.getId()%>" value="<%=a.getId()%>-false">
                                <%}%>
                                <input type="submit" value="Generar Informe" class="ufps-btn ufps-width-100 ufps-margin-top-10 ufps-tx-center">
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
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for (Actividad a : acts) {%>
                                        <tr>
                                            <td style="text-align: center;"><input type="checkbox" id="<%=a.getId() + "" + a.getId()%>" onchange="editAct(<%=a.getId()%>)"></td>
                                            <td style="text-align: center;"><%=a.getNombre()%></td>
                                            <td style="text-align: center;"><%=sd.format(a.getFechaInicio())%></td>
                                            <td style="text-align: center;"><%=a.getTipoActividadId().getTipo()%></td>
                                            <td style="text-align: center;"><%=a.getLugar()%></td>
                                            <td style="text-align: center;">
                                                <a href="<%=request.getContextPath()%>/ControlActividad?q=edit&id=<%=a.getId()%>"><i class="fa fa-edit"></i></a>
                                                <a href="<%=request.getContextPath()%>/ControlActividad?q=del&id=<%=a.getId()%>"><i class="fa fa-times"></i></a>
                                            </td>
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

        <!--FOOTER-->
        <jsp:include page="/templates/includes/footer.jsp" />
        <!--FIN FOOTER-->

        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

        <script src="<%=request.getContextPath()%>/js/dataTables.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/ufps.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/main.js"></script>

        <script>
                                                function editAct(id) {
                                                    var value = $('#' + id + "" + id).is(":checked");
                                                    $('#' + id).val(id + "-" + value);
                                                }
        </script>
    </body>
</html>
