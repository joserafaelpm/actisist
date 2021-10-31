<%-- 
    Document   : editarConvenio
    Created on : 16/06/2021, 10:33:49 AM
    Author     : dunke
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ufps.edu.co.dto.Convenio"%>
<%@page import="java.util.List"%>
<%@page import="ufps.edu.co.dto.TipoConvenio"%>
<%@page import="ufps.edu.co.dto.Usuario"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar convenio</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ufps.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <script src="<%=request.getContextPath()%>/js/JQuery.js"></script>
    </head>
    <body style="position: relative;">
        <%
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            Convenio convenio = ((Convenio) request.getSession().getAttribute("convenio"));
            List<TipoConvenio> tps = ((List<TipoConvenio>) request.getSession().getAttribute("tps"));
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            if (user == null) {
                response.sendRedirect("login.jsp");
            }
        %>

        <!--HEADER-->
        <jsp:include page="/templates/includes/header.jsp" />
        <!--FIN HEADER-->

        <div class="ufps-container-fluid ufps-pl-pr-15" style="margin-left: 80px; margin-right: 80px;">
            <div class="ufps-row" >
                <div class="ufps-col-mobile-12 fps-col-netbook-12 ufps-padding-5" >
                    <div class="ufps-section-form">
                        <div class="ufps-title-section">
                            Editar Convenio
                        </div>
                        <div class="ufps-body-section-80 ufps-padding-5">
                            <form action="<%=request.getContextPath()%>/ControlConvenio?q=edit" method="POST" accept-charset="ISO-8859-1">
                                <div class="ufps-row" >
                                    <div class="ufps-col-mobile-2 ufps-col-netbook-2" >Numero de convenio:</div >
                                    <div class="ufps-col-mobile-2 ufps-col-netbook-2" >
                                        <input type="number"  name="num" class="ufps-input-line" value="<%=convenio.getNumero()%>" required>
                                    </div>
                                </div>
                                <div class="ufps-row" >
                                    <div class="ufps-col-mobile-5 ufps-col-netbook-6" >
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-3 ufps-col-netbook-3"> Fecha Inicio:</div >
                                            <div class="ufps-col-mobile-4 ufps-col-netbook-4" > <input type="Date"  id="fe_in" name="fe_in" class="ufps-input-line" value="<%=sd.format(convenio.getFecha())%>" required></div>
                                        </div>
                                    </div >
                                    <div class="ufps-col-mobile-5 ufps-col-netbook-6" >
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-3 ufps-col-netbook-3" > Fecha Fin:</div >
                                            <div class="ufps-col-mobile-4 ufps-col-netbook-4" > <input type="Date"  id="fe_out" name="fe_out" class="ufps-input-line" value="<%=sd.format(convenio.getVigencia())%>" required></div>
                                        </div>    
                                    </div>
                                </div>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15"><label>Descripcion</label></div>
                                <textarea class="ufps-input" style="height: 100px; resize: none;" rows="15" cols="50" id="descr" name="descr"><%=convenio.getDescripcion()%></textarea>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15"><label>Razon del convenio</label></div>
                                <textarea class="ufps-input" style="height: 100px; resize: none;" rows="15" cols="50" id="razon" name="razon"><%=convenio.getRazon()%></textarea>

                                <div class="ufps-margin-top-10 ufps-pl-pr-15">
                                    <div class="ufps-title-h1-red">
                                        Empresa o Institucion Adscrita
                                    </div>
                                    <div class="ufps-row" >
                                        <div class="ufps-col-mobile-12 ufps-col-netbook-6" >
                                            <div id="exist">
                                                <select class="ufps-input-line" id="ins_exi" name="ins_exi">
                                                </select>
                                            </div>
                                        </div>
                                    </div >


                                    <div class="ufps-title-h1-red ">
                                        Tipo de convenio
                                    </div>
                                    <div class="ufps-col-mobile-4 ufps-col-netbook-4 ufps-margin-top-10">
                                        <label>Tipo de convenio</label>
                                        <select id="tp_con" name="tp_con" class="ufps-input-line">
                                            <%
                                                for (TipoConvenio tp : tps) {
                                                    if (tp.getId() == convenio.getTipoConvenio().getId()) {
                                            %>
                                            <option value="<%=tp.getId()%>" selected><%=tp.getTipo()%></option>
                                            <%} else {%>
                                            <option value="<%=tp.getId()%>"><%=tp.getTipo()%></option>
                                            <%}
                                                }%>
                                        </select>
                                    </div>
                                        <input type="submit"  class="ufps-btn ufps-width-100 ufps-margin-top-10" value="Guardar cambios">
                                </div>
                            </form>
                        </div>
                    </div >
                </div >
            </div>
        </div>

        <!--FOOTER-->
        <jsp:include page="/templates/includes/footer.jsp" />
        <!--FIN FOOTER-->

        <script>
            $(document).ready(function () {
                load('<%=request.getContextPath()%>/ControlInstitucion?q=list', '#ins_exi');
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