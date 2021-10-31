<%-- 
    Document   : registroConvenio
    Created on : 8/06/2021, 03:16:22 PM
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
        <title>Registrar convenio</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ufps.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <script src="<%=request.getContextPath()%>/js/JQuery.js"></script>
    </head>
    <body style="position: relative;">
        <%
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            List<Convenio> cs = ((List<Convenio>) request.getSession().getAttribute("cs"));
            List<TipoConvenio> tps = ((List<TipoConvenio>) request.getSession().getAttribute("tps"));
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            if (user == null) {
                response.sendRedirect("login.jsp");
            }
        %>

        <!--HEADER-->
        <jsp:include page="/templates/includes/header.jsp" />
        <!--FIN HEADER-->

        <div class="ufps-container-fluid" style="height: 850px;">
            <div class="ufps-row" >
                <div class="ufps-col-mobile-7 ufps-col-netbook-7" >
                    <div class="ufps-section-form">
                        <div class="ufps-title-section">
                            Registrar Convenio
                        </div>
                        <div class="ufps-body-section-80 ufps-padding-5">
                            <form action="<%=request.getContextPath()%>/ControlConvenio?q=reg" method="POST" accept-charset="ISO-8859-1">
                                <div class="ufps-row" >
                                    <div class="ufps-col-mobile-3 ufps-col-netbook-3" >Numero de convenio:</div >
                                    <div class="ufps-col-mobile-2 ufps-col-netbook-2" >
                                        <input type="number"  name="num" class="ufps-input-line" required>
                                    </div>
                                </div>
                                <div class="ufps-row" >
                                    <div class="ufps-col-mobile-6 ufps-col-netbook-6" >
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-4 ufps-col-netbook-6" > Fecha Inicio:</div >
                                            <div class="ufps-col-mobile-5 ufps-col-netbook-6" > <input type="date"  name="fe_in" class="ufps-input-line" value="<%=sd.format(date)%>" required></div>
                                        </div>
                                    </div >
                                    <div class="ufps-col-mobile-6 ufps-col-netbook-6" >
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-4 ufps-col-netbook-6" > Fecha Fin:</div >
                                            <div class="ufps-col-mobile-5 ufps-col-netbook-6" > <input type="date"  name="fe_out" class="ufps-input-line" value="<%=sd.format(date)%>" required></div>
                                        </div>    
                                    </div>
                                </div>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15"><label>Descripcion</label></div>
                                <textarea class="ufps-input" style="height: 100px; resize: none;" rows="15" cols="50" name="descr"></textarea>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15"><label>Razon del convenio</label></div>
                                <textarea class="ufps-input" style="height: 100px; resize: none;" rows="15" cols="50" name="razon"></textarea>

                                <div class="ufps-margin-top-10 ufps-pl-pr-15">
                                    <div class="ufps-title-h1-red">
                                        Empresa o Institucion
                                    </div>
                                    <div class="ufps-row" >
                                        <div class="ufps-col-mobile-8 ufps-col-netbook-8" >
                                            <div class="label"><label class="ufps-title-input">La institucion se encuentra registrada</label></div>
                                            <input type="checkbox" id="ins_exis" name="ins_exis" onchange="exist_ins()"><label for="ins_exis">Institucion registrada</label><br>
                                            <div id="exist" hidden>
                                                <select class="ufps-input-line" id="ins_exi" name="ins_exi">
                                                </select>
                                            </div>
                                            <div id="inputIns" class="ufps-margin-botton-10">
                                                <input  class="ufps-input ufps-margin-top-10" type="text" id="ins_non" name="ins_non">
                                            </div>
                                        </div>
                                        <div id="non_exist" class="ufps-col-mobile-4 ufps-col-netbook-4">
                                            <div class="label"><label class="ufps-title-input">Pais</label></div>
                                                <select class="ufps-input-line"  id="pai" name="pai">
                                                </select>
                                        </div>
                                    </div>


                                    <div class="ufps-title-h1-red ">
                                        Tipo de convenio
                                    </div>
                                    <div class="ufps-col-mobile-4 ufps-col-netbook-4  ufps-margin-top-10">
                                        <label>Tipo de convenio</label>
                                        <select id="tp_con" name="tp_con" class="ufps-input-line">
                                            <%for (TipoConvenio tp : tps) {%>
                                            <option value="<%=tp.getId()%>"><%=tp.getTipo()%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                    <input type="submit"  class="ufps-btn ufps-width-100 ufps-margin-top-10" value="Registrar">
                                </div>
                            </form>
                        </div>
                    </div >
                </div >
                <div class="ufps-col-mobile-5 ufps-col-netbook-5" >
                    <div class="ufps-section-form">
                        <div class="ufps-title-section">
                            Tabla de convenios Registrados
                        </div>
                        <div class="ufps-body-section-80 ufps-padding-5">

                            <!--TABLA  DE CONVENIOS REGISTRADOS-->
                            <table border="1" class="ufps-table ufps-table-inserted"">
                                <tr>
                                    <th>Numero</th>
                                    <th>Fecha</th>
                                    <th>Vigencia</th>
                                    <th>Institución</th>
                                    <th>Acción</th>
                                </tr>
                                <%for (Convenio c : cs) {%>
                                <tr> 
                                    <td><%=c.getNumero()%></td>
                                    <td><%=sd.format(c.getFecha())%></td>
                                    <td><%=sd.format(c.getVigencia())%></td>
                                    <td><%=c.getEmpresa().getNombre()%></td>
                                    <td><a href="<%=request.getContextPath()%>/ControlConvenio?q=show&n_c=<%=c.getId()%>">Editar</a></td>
                                </tr>
                                <%}%>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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

            function exist_ins() {
                if ($('#ins_exis').is(':checked')) {
                    $('#non_exist').hide();
                    $('#exist').show();
                    $('#inputIns').hide();
                } else {
                    $('#non_exist').show();
                    $('#exist').hide();
                    $('#inputIns').show();
                }
            }
        </script>
        <script src="<%=request.getContextPath()%>/js/ufps.min.js"></script>
    </body>
</html>
