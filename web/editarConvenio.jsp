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
        <title>JSP Page</title>
        <script src="js/JQuery.js"></script>
        <link href="css/ufps.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
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
                </div><%
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                    Convenio convenio = ((Convenio) request.getSession().getAttribute("convenio"));
                    List<TipoConvenio> tps = ((List<TipoConvenio>) request.getSession().getAttribute("tps"));
                    Usuario user = ((Usuario) request.getSession().getAttribute("user"));
                    if (user == null) {
                        response.sendRedirect("login.jsp");
                    }
                %>
                <div class="ufps-navbar-right">
                    <%if (user.getIdRol().getId() == 1) {%><a href="dashboard.jsp" class="ufps-navbar-btn">Inicio</a>
                    <%}else{%><a href="ControlActividad?q=showFor" class="ufps-navbar-btn">Mis Actividades</a><%}%>
                    <div class="ufps-dropdown" id="dropdown4">
                        <div class="ufps-dropdown-content">
                            <%if (user.getIdRol().getId() != 1) {%><a href="ControlUsuario?q=perfil">Mi Perfil</a><%}%>
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

        <div class="ufps-container-fluid ufps-margin-top-10">
            <div class="ufps-row" >
                <div class="ufps-col-mobile-12 ufps-col-netbook-3" >
                    <ul class="ufps-list-ul">
                        <li><a href="misActividades.jsp"><i class="fa fa-list"></i> Registrar Actividad</a></li>
                        <li><a href="registroDocConf.jsp"><i class="fa fa-user"></i> Registrar Docente</a></li>
                        <li><a href="registroConvenio.jsp"><i class="fa fa-handshake"></i> Registrar Convenio</a></li>
                        <li><a href=""><i class="fa fa-signal"></i> Generar Graficas</a></li>
                    </ul>
                </div >
                <div class="ufps-col-mobile-12 ufps-margin-top-10 ufps-col-netbook-9" >
                    <div class="ufps-section-form">
                        <div class="ufps-title-section">
                            Editar Convenio
                        </div>
                        <div class="ufps-body-section-80 ufps-padding-5">


                            <form action="ControlConvenio?q=edit" method="POST" accept-charset="ISO-8859-1">
                                <div class="ufps-row" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-3" >Numero de convenio:</div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-9" >
                                        <input type="number"  name="num" class="ufps-input-line" value="<%=convenio.getNumero()%>" required>
                                    </div>
                                </div>
                                <div class="ufps-row" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-6" >
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-3"> Fecha Inicio:</div >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-9" > <input type="Date"  id="fe_in" name="fe_in" class="ufps-input-line" value="<%=sd.format(convenio.getFecha())%>" required></div>
                                        </div>
                                    </div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-6" >
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-3" > Fecha Fin:</div >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-9" > <input type="Date"  id="fe_out" name="fe_out" class="ufps-input-line" value="<%=sd.format(convenio.getVigencia())%>" required></div>
                                        </div>    
                                    </div>
                                </div>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15"><label>Descripcion</label></div>
                                <textarea class="ufps-input" rows="4" cols="50" id="descr" name="descr"><%=convenio.getDescripcion()%></textarea>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15"><label>Razon del convenio</label></div>
                                <textarea class="ufps-input" rows="4" cols="50" id="razon" name="razon"><%=convenio.getRazon()%></textarea>

                                <div class="ufps-title-h1-red">
                                    La institucion se encuentra registrada
                                </div>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15">
                                    <div class="ufps-row" >
                                        <div class="ufps-col-mobile-12 ufps-col-netbook-6" >
                                            <div class="label"><label class="ufps-title-input">Empresa o Institucion Adscrita</label></div>
                                            <div id="exist">
                                                <select class="ufps-input-line" id="ins_exi" name="ins_exi">
                                                </select>
                                            </div>
                                        </div>
                                    </div >


                                    <div class="ufps-title-h1-red ">
                                        Tipo de convenio
                                    </div>
                                    <div class="ufps-margin-top-10">
                                        <label>Tipo de convenio</label>
                                        <select id="tp_con" name="tp_con" class="ufps-input-line">
                                            <%
                                                for (TipoConvenio tp : tps) {
                                                    if (tp.getId() == convenio.getId()) {
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

            <script>
                $(document).ready(function () {
                    load('ControlInstitucion?q=list', '#ins_exi');
                });

                function load(control, compnt) {
                    $.post(control, {}, function (response) {
                        $(compnt).html(response);
                    });
                }
            </script>
            <div class="ufps-footer">
                <h3>Universidad Francisco de Paula Santander</h3>
                <p>Programa Ingeniería de Sistemas</p>
                <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
            </div> 
            <script src="js/ufps.min.js"></script>
    </body>
</html>