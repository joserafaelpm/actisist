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
        <title>JSP Page</title>
        <script src="js/JQuery.js"></script>
        <link href="css/ufps.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="ufps-navbar ufps-navbar-delete_margin" id="menu">
            <div class="ufps-container-fluid">
                <div class="ufps-navbar-brand">
                    <div class="ufps-btn-menu" onclick="toggleMenu('menu')">
                        <div class="ufps-btn-menu-bar"></div>
                        <div class="ufps-btn-menu-bar"> </div>
                        <div class="ufps-btn-menu-bar"> </div>
                    </div>
                </div>
                <div class="ufps-navbar-right">
                    <a href="misActividades.jsp" class="ufps-navbar-btn">Inicio</a>
                    <a href="index.jsp" class="ufps-navbar-btn">Mis Actividades</a>
                    <a onclick="openDropdown('dropdown4')"  class="ufps-navbar-btn ufps-dropdown-btn">Hecttor Parra <img class="ufps-perfil-redonde" src="img/user.jpg"/></a>
                </div>
                <div class="ufps-dropdown" id="dropdown4">
                    <div class="ufps-dropdown-content">
                        <a href="#">Opción 1</a>
                        <a href="#">Opción 2</a>
                        <a href="#">Opción 3</a>
                    </div>
                </div>
                <div class="ufps-navbar-left">
                    <div class="ufps-navbar-corporate">
                        <img  src="img/logo_ufps_inverted.png" alt="Logo UFPS">
                    </div>
                </div>

            </div>
        </div>

        <div class="ufps-container-fluid ufps-margin-top-10">
            <div class="ufps-row" >
                <div class="ufps-col-mobile-12 ufps-col-netbook-3" >
                    <ul class="ufps-list-ul">
                        <li><a href="registrarActividad.jsp"><i class="fa fa-list"></i> Registrar Actividad</a></li>
                        <li><a href="registroDocConf.jsp"><i class="fa fa-user"></i> Registrar Docente</a></li>
                        <li><a href="registroConvenio.jsp"><i class="fa fa-handshake"></i> Registrar Convenio</a></li>
                        <li><a href=""><i class="fa fa-signal"></i> Generar Graficas</a></li>
                    </ul>
                </div >

                <%
                    SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
                    List<Convenio> cs = ((List<Convenio>) request.getSession().getAttribute("cs"));
                    List<TipoConvenio> tps = ((List<TipoConvenio>) request.getSession().getAttribute("tps"));
                    Usuario user = ((Usuario) request.getSession().getAttribute("user"));
                    if (user == null) {
                        response.sendRedirect("login.jsp");
                    }
                %>

                <div class="ufps-col-mobile-12 ufps-margin-top-10 ufps-col-netbook-9" >
                    <div class="ufps-section-form">
                        <div class="ufps-title-section">
                            Registrar Convenio
                        </div>
                        <div class="ufps-body-section-80 ufps-padding-5">


                            <form action="ControlConvenio?q=reg" method="POST">
                                <div class="ufps-row" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-3" >Numero de convenio:</div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-9" >
                                        <input type="number"  name="num" class="ufps-input-line" required>
                                    </div>
                                </div>
                                <div class="ufps-row" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-6" >
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-3" > Fecha Inicio:</div >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-9" > <input type="Date"  name="fe_in" class="ufps-input-line" required></div>
                                        </div>
                                    </div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-6" >
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-3" > Fecha Fin:</div >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-9" > <input type="Date"  nname="fe_out" class="ufps-input-line" required></div>
                                        </div>    
                                    </div>
                                </div>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15"><label>Descripcion</label></div>
                                <textarea class="ufps-input" rows="4" cols="50" name="descr"></textarea>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15"><label>Razon del convenio</label></div>
                                <textarea class="ufps-input" rows="4" cols="50" name="razon"></textarea>

                                <div class="ufps-title-h1-red">
                                    La institucion se encuentra registrada
                                </div>
                                <div class="ufps-margin-top-10 ufps-pl-pr-15">
                                    <div class="ufps-row" >
                                        <div class="ufps-col-mobile-12 ufps-col-netbook-6" >
                                            <div class="label"><label class="ufps-title-input">Empresa o Institucion</label></div>
                                            <input type="checkbox" id="ins_exis" name="ins_exis" onchange="exist_ins()">Institucion registrada<br>
                                            <div id="exist" hidden>
                                                <select class="ufps-input-line" id="ins_exi" name="ins_exi">
                                                </select>
                                            </div>
                                            <div id="inputIns" class="ufps-margin-botton-10">
                                                <input  class="ufps-input ufps-margin-top-10" type="text" id="ins_non" name="ins_non">
                                            </div>
                                             
                                        </div>
                                            <div id="non_exist" class="ufps-col-mobile-12 ufps-col-netbook-6">
                                               
                                                <div class="ufps-row">
                                                    <div class="ufps-col-mobile-12 ufps-col-netbook-6" > 
                                                    <div class="label"><label class="ufps-title-input">Pais</label></div>
                                                    <select class="ufps-input-line"  id="pai" name="pai">
                                                    </select>
                                                </div >
                                                <div class="ufps-col-mobile-12 ufps-col-netbook-6" > 
                                                    <div class="label"><label class="ufps-title-input">Pais</label></div>
                                                    <select class="ufps-input-line"  id="pai" name="pai">
                                                    </select>
                                                </div >
                                                </div>
                                            </div>
                                        </div >


                                    <div class="ufps-title-h1-red ">
                                        Tipo de convenio
                                    </div>
                                    <div class="ufps-margin-top-10">
                                    <label>Tipo de convenio</label>
                                    <select id="tp_con" name="tp_con" class="ufps-input-line">
                                        <%for (TipoConvenio tp : tps) {%>
                                        <option value="<%=tp.getId()%>"><%=tp.getTipo()%></option>
                                        <%}%>
                                    </select>
                                </div>
                                <input type="submit"  class="ufps-btn ufps-width-100 ufps-margin-top-10" value="Registrar">
                            </form>

                        </div>
                    </div>
                </div >
            </div >
        </div>

        <!--TABLA  DE CONVENIOS REGISTRADOS-->
        <table border="1">
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
                <td><a href="ControlConvenio?q=edit&n_c=<%=c.getId()%>">Editar</a></td>
            </tr>
            <%}%>
        </table>

        <script>
            $(document).ready(function () {
                load('ControlInstitucion?q=list', '#ins_exi');
                load('ControlPaises?q=list', '#pai');
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
        <div class="ufps-footer">
            <h3>Universidad Francisco de Paula Santander</h3>
            <p>Programa Ingeniería de Sistemas</p>
            <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
        </div>  
    </body>
</html>
