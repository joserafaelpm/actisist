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
        <title>Ver actividades</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.transfer.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/icon_font/css/icon_font.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ufps.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <script src="<%=request.getContextPath()%>/js/JQuery.js"></script>
    </head>
    <body style="position: relative;">
        <%
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            List<Actividad> acts = (List<Actividad>) request.getSession().getAttribute("acts");
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            if (user == null) {
                response.sendRedirect("login.jsp");
            }
        %>
        
        <!--HEADER-->
        <jsp:include page="/templates/includes/header.jsp" />
        <!--FIN HEADER-->
        
        <div class="ufps-container-fluid ">
            <div class="ufps-row ufps-margin-top-10" >
                <div class="ufps-col-mobile-12 ufps-col-netbook-3" > 
                    <div class="ufps-card">
                        <div class="ufps-card-caption ufps-padding-30" style="margin-bottom: 100px;">
                            
                            <!-- FILTER-TABLE-->
                            <jsp:include page="/templates/includes/filter-table.jsp"/>
                            <!-- END FILTER-TABLE-->
                            
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
                                            <th>Docente Encargado</th>
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
                                            <td style="text-align: center;"><%=a.getUsuarioDni().getNombre() + " " + a.getUsuarioDni().getApellido()%></td>
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