<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ufps.edu.co.dto.Tipo"%>
<%@page import="java.util.List"%>
<%@page import="ufps.edu.co.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
        <title>SISRECA | UFPS</title>
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
            Date date = new Date();
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            List<Tipo> typeAct = ((List<Tipo>) request.getSession().getAttribute("typeAct"));
            List<Tipo> typeMov = ((List<Tipo>) request.getSession().getAttribute("typeMov"));
            if (user == null) {
                response.sendRedirect("login.jsp");
            }
        %>

        <!--HEADER-->
        <jsp:include page="/templates/includes/header.jsp" />
        <!--FIN HEADER-->

        <div class="ufps-container">
            <div class="ufps-card">
                <form action="<%=request.getContextPath()%>/ControlActividad?q=reg" method="POST" enctype="multipart/form-data" accept-charset="ISO-8859-1">
                    <div class="ufps-card-caption ufps-padding-5 ">
                        <div class="ufps-row">
                            <div class="ufps-col-netbook-6 ufps-col-mobile-12 ">
                                <div class="ufps-div-title ufps-title-color">
                                    Imagen Representativa
                                </div>
                                <div class="ufps-row ufps-margin-top-10" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-8" >
                                        <input type="file" accept="image/png,image/jpeg" name="imagen" id="fileImagen" onchange="srcFrom()" class="ufps-input-line">
                                        <input type="hidden" id="vmg" name="vmg" value="0">
                                    </div>
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-4" >
                                        <img id="previsualizaImagen" src="<%=request.getContextPath()%>/img/logo_ufps.png" class="ufps-img-responsive" />
                                    </div>
                                </div>
                            </div>
                            <div class="ufps-col-netbook-6 ufps-col-mobile-12">
                                <div class="ufps-div-title ufps-title-color">
                                    Información
                                </div>
                                <div class="ufps-row ufps-margin-top-10" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-5" ><label>Nombre de la actividad:</label> </div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-7" > <input type="text"  name="nombre" class="ufps-input-line" required></div>
                                </div>
                                <div class="ufps-row ufps-margin-top-20" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-10" > 
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-5" > Fecha Inicio:</div >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-7" > <input type="date"  name="fe_in" value="<%=sd.format(date)%>" class="ufps-input-line" required></div>
                                        </div>
                                    </div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-10" > 
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-5" > Fecha Fin:</div >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-7" > <input type="date"  name="fe_out" value="<%=sd.format(date)%>" class="ufps-input-line" required></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="ufps-row ufps-margin-top-20" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-2" >Tipo:</div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-10" >
                                        <select class="ufps-input-line" name="typeAct">
                                            <%for (Tipo t : typeAct) {%>
                                            <option value="<%=t.getId()%>"><%=t.getTipo()%></option>
                                            <%}%>
                                        </select>
                                    </div >
                                </div >
                                <div class="ufps-row ufps-margin-top-20" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-2" >Semestre:</div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-10" >
                                        <select class="ufps-input-line" name="sem">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="ufps-pl-pr-15 ufps-margin-top-20">
                                    Tematica:
                                    <textarea class="ufps-input" style="height: 100px; resize: none;" rows="15" cols="50" name="tema"></textarea>
                                    Descripción:
                                    <textarea class="ufps-input" style="height: 100px; resize: none;" rows="15" cols="50" name="desc"></textarea>
                                    Lugar:
                                    <input type="text" class="ufps-input" rows="4" cols="50" name="lugar">
                                    <div class="ufps-margin-top-10">
                                        <div class="ufps-col-mobile-12 ufps-col-netbook-3" ><label>Movilidad</label> </div >
                                        <div class="ufps-col-mobile-12 ufps-col-netbook-9" >
                                            <select class="ufps-input-line" id="movilidadSelect" name="typeMov">
                                                <%for (Tipo t : typeMov) {%>
                                                <option value="<%=t.getId()%>"><%=t.getTipo()%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="ufps-row">
                            <div class="ufps-col-netbook-6 ufps-col-mobile-12 ">
                                <div class="ufps-margin-top-30">
                                    <div class="ufps-div-title ufps-title-color">
                                        Conferencistas
                                    </div>
                                    <!--Informacion: 
                                    https://www.jqueryscript.net/form/Groupable-Searchable-Dual-Listbox-Transfer.jsp
                                    -->
                                    <div class="ufps-conferencistas-div">
                                        <div id="transfer1"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="ufps-col-netbook-6 ufps-col-mobile-12 ">
                                <div class="ufps-margin-top-30">
                                    <div class="ufps-div-title ufps-title-color">
                                        Convenios
                                    </div>
                                    <!--Informacion: 
                                    https://www.jqueryscript.net/form/Groupable-Searchable-Dual-Listbox-Transfer.jsp
                                    -->
                                    <div class=" ufps-conferencistas-div">
                                        <div id="transfer2"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="conf" name="conf">
                        <input type="hidden" id="conv" name="conv">
                        <input type="submit"  class="ufps-btn ufps-width-100 ufps-margin-top-20" value="Registrar Actividad"></input>
                    </div>
                </form>
            </div>
        </div>


        <!--FOOTER-->
        <jsp:include page="/templates/includes/footer.jsp" />
        <!--FIN FOOTER-->

        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.bootstrap-duallistbox.js"></script>
        <script src="<%=request.getContextPath()%>/js/dataTables.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.transfer.js"></script>
        <script src="<%=request.getContextPath()%>/js/ufps.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/main.js"></script>

        <script>
                                            $(document).ready(function () {
                                                $.post("<%=request.getContextPath()%>/ControlUsuario?q=liste", {}, function (response) {
                                                    if(response)resolveResponse(response, "#transfer1", "#conf", "Conferencistas");
                                                });

                                                $.post("<%=request.getContextPath()%>/ControlConvenio?q=liste", {}, function (response) {
                                                    if(response)resolveResponse(response, "#transfer2", "#conv", "Convenios");
                                                });

                                                function resolveResponse(response, component, id, col) {
                                                    var arr = response.split(",");
                                                    var res = [];
                                                    for (var i = 0; i < arr.length; i++) {
                                                        var temp = arr[i].split(":");
                                                        res.push({"data": temp[0], "value": temp[1]});
                                                    }
                                                    var settings = {
                                                        "dataArray": res,
                                                        "itemName": "data",
                                                        "valueName": "value",
                                                        "tabNameText": col,
                                                        "rightTabNameText": col + " selecionados",
                                                        "callable": function (items) {
                                                            var r = "";
                                                            items.forEach((element) => {
                                                                r += element.value + ",";
                                                            });
                                                            if (r.length > 0) {
                                                                r = r.substring(0, r.length - 1);
                                                            }
                                                            ;
                                                            $(id).val(r);
                                                        }
                                                    };
                                                    $(component).transfer(settings);
                                                }
                                            });

                                            function srcFrom() {
                                                $('#vmg').val(1);
                                            }
        </script>
    </body>
</html>
