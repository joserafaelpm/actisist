<%@page import="ufps.edu.co.dto.Titulo"%>
<%@page import="java.util.List"%>
<%@page import="ufps.edu.co.dto.TipoDocente"%>
<%@page import="ufps.edu.co.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Software | Mi Perfil</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ufps.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <script src="<%=request.getContextPath()%>/js/ufps.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/JQuery.js"></script>

    </head>
    <body style="position: relative;">
        <%
            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
            if (user == null) {
                response.sendRedirect("login.jsp");
            }
            List<TipoDocente> tp = (List<TipoDocente>) request.getSession().getAttribute("type");
            List<Titulo> ts = user.getTituloList();
        %>

        <!--HEADER-->
        <jsp:include page="/templates/includes/header.jsp" />
        <!--FIN HEADER-->

        <div class="ufps-container ">  
            <div class="ufps-card ufps-margin-top-30">

                <div class="ufps-card-caption">
                    <form action="<%=request.getContextPath()%>/ControlUsuario?q=edit" method="POST" enctype="multipart/form-data" accept-charset="ISO-8859-1">
                        <div class="ufps-col-mobile-6 ufps-col-netbook-6 ufps-row" >
                            <div class="ufps-title-color ufps-text-center ufps-font-s">Mi Perfil</div>

                            <div class="ufps-row ufps-margin-top-10" >
                                <div class="ufps-col-mobile-12 ufps-col-netbook-8" >
                                    <input type="file" accept="image/png,image/jpeg" name="imagen" id="fileImagen" onchange="srcFrom()" class="ufps-input-line">
                                    <input type="hidden" id="vmg" name="vmg" value="0">
                                </div>
                                <div class="ufps-col-mobile-12 ufps-col-netbook-4" >
                                    <img id="previsualizaImagen" class="ufps-img-responsive" style="object-fit: contain; border-radius: 180px;" src="<%=user.getDocente().encodeImage()%>" />
                                </div>
                            </div>
                        </div>


                        <div class="ufps-col-mobile-6 ufps-col-netbook-6 ufps-row" >
                            <div class="ufps-title-color ufps-text-center ufps-font-s">Información Personal</div>
                            <div class="ufps-row ufps-margin-top-30" >
                                <div class="ufps-col-mobile-12 ufps-col-netbook-12" >
                                    <label class="ufps-title-input">Nombres:</label>
                                    <input type="text" class="ufps-input-line" name="nombre" value="<%=user.getNombre()%>" required>
                                    <label class="ufps-title-input">Apellidos</label>
                                    <input type="text" class="ufps-input-line" name="ape" value="<%=user.getApellido()%>" required>
                                    <label class="ufps-title-input">Documento:</label>
                                    <input type="text" class="ufps-input-line" value="<%=user.getDni()%>" disabled>
                                    <label class="ufps-title-input">Codigo</label>
                                    <input type="text" class="ufps-input-line" value="<%=user.getDocente().getCodigo()%>" disabled>
                                    <label class="ufps-title-input">Tipo</label>
                                    <select class="ufps-input-line" name="type">
                                        <%
                                            for (TipoDocente t : tp) {
                                                if (t.getId().equals(user.getDocente().getIdTipoDocente().getId())) {
                                        %>
                                        <option value="<%=t.getId()%>" selected><%=t.getTipo()%></option>
                                        <%} else {%>
                                        <option value="<%=t.getId()%>"><%=t.getTipo()%></option>
                                        <%}
                                            }%>
                                    </select>
                                </div >
                            </div >
                        </div>

                        <div class="ufps-col-mobile-12 ufps-col-netbook-12 ufps-row ufps-margin-top-20" >
                            <div class="ufps-title-color ufps-text-center ufps-font-s">Titulos</div>
                            <div class="ufps-col-mobile-6 ufps-col-netbook-6 ufps-margin-top-10">
                                <label class="ufps-title-input">Titulo: </label>
                                <input type="text" class="ufps-input-line" id="titulo">
                                <a onclick="addFila()" class="ufps-btn ufps-margin-top-10">Agregar Titulo</a>
                            </div>

                            <div class="ufps-col-mobile-6 ufps-col-netbook-6"> 
                                <div class="ufps-documentos ufps-margin-top-10" style="height: 320px; overflow-y: scroll;">
                                    <div class="ufps-title-color ufps-text-center ufps-font-s">Documentos acreditados</div>
                                    <div class="ufps-padding-5">
                                        <table id="tablaprofe" class="ufps-table ufps-text-left" >
                                            <thead>
                                                <tr><th style="text-align: center;">Nombre</th></tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    int i = 0;
                                                    for (Titulo t : ts) {
                                                        System.out.println(t);
                                                        if (t != null) {
                                                %>
                                                <tr><td id="<%=++i%>" style="text-align: center;"><input id="<%=(i - 1) + "-" + (i - 1)%>" type="text" disabled value="<%=t.getDescripcion()%>"><a onclick="remove(<%=i - 1%>)"><i class="fa fa-times"></i></td></tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                            </tbody>
                                            <input type="hidden" id="data" name="titlesDoc" value="">
                                        </table>  
                                    </div>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" id="titles" name="titles">
                        <input type="submit" class="ufps-btn w-100 ufps-margin-top-30" value="Guardar">
                    </form>
                </div>
            </div>
        </div>

        <!--FOOTER-->
        <jsp:include page="/templates/includes/footer.jsp" />
        <!--FIN FOOTER-->

        <script>
            function readURL2(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        // Asignamos el atributo src a la tag de imagen
                        $('#previsualizaImagen').removeAttr("src");
                        $('#previsualizaImagen').attr('src', e.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                }
            }

            $('#fileImagen').change(function () {
                readURL2(this);
            });

            function srcFrom() {
                $('#vmg').val(1);
            }

            function remove(num) {
                var cont = $('#' + num + "-" + num).val();
                var arr = $('#titles').val().split("#");
                var newval = "";
                arr.forEach(elem => {
                    if (elem != cont && elem.length > 0)
                        newval += "#" + elem;
                });
                $('#titles').val(newval);
                $('#' + num).remove();
            }

            function addFila() {
                var tabla = document.getElementById('tablaprofe');
                var filas = tabla.rows.length;
                var cont = $('#titulo').val();
                tabla.insertRow(-1).innerHTML = "<tr><td id=" + filas + " style='text-align: center;'><input id=" + filas + "-" + filas + " type='text' disabled value='" + cont + "'><a onclick='remove(" + filas + ")'><i class='fa fa-times'></i></td></tr>";
                var newval = $('#titles').val() + "#" + cont;
                $('#titles').val(newval);
                $('#titulo').val("");
            }
        </script>
    </body>
</html>