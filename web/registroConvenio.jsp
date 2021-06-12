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
    </head>
    <body>
        <%
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            List<Convenio> cs = ((List<Convenio>)request.getSession().getAttribute("cs")); 
            List<TipoConvenio> tps = ((List<TipoConvenio>)request.getSession().getAttribute("tps")); 
            Usuario user = ((Usuario)request.getSession().getAttribute("user"));
            if(user==null){response.sendRedirect("login.jsp");}
        %>
        <h1>Opciones para convenio</h1>
        <h2>Registrar</h2>
        <form action="ControlConvenio?q=reg" method="POST">
            <label>Numero</label>
            <input type="text" name="num"><br>
            <label>Fecha</label>
            <input type="Date" name="fe_in"><br
            <label>Vigencia</label>
            <input type="Date" name="fe_out"><br
            <label>Descripción</label>
            <textarea name="descr"></textarea><br>
            <label>Razón del convenio</label>
            <textarea name="razon"></textarea><br>
            
            <input type="checkbox" id="ins_exis" name="ins_exis" onchange="exist_ins()">Institucion registrada<br>
            
            <div id="exist" hidden>
                <label>Institución</label>
                <select id="ins_exi" name="ins_exi">
                </select>
            </div>
            <div id="non_exist">
                <label>Institución</label>
                <input type="text" id="ins_non" name="ins_non">
                <label>Pais</label>
                <select id="pai" name="pai">
                </select>
            </div>
            <label>Tipo de convenio</label>
            <select id="tp_con" name="tp_con">
                <%for(TipoConvenio tp: tps){%>
                <option value="<%=tp.getId()%>"><%=tp.getTipo()%></option>
                <%}%>
            </select>
            
            <input type="submit">
        </form>
        
        <table border="1">
            <tr>
                <th>Numero</th>
                <th>Fecha</th>
                <th>Vigencia</th>
                <th>Institución</th>
                <th>Acción</th>
            </tr>
        <%for(Convenio c: cs){%>
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
            $(document).ready(function (){
                load('ControlInstitucion?q=list', '#ins_exi');
                load('ControlPaises?q=list', '#pai');
            });
            
            function load(control, compnt){
                $.post(control, {}, function(response){
                    $(compnt).html(response);
                });
            }
            
            function exist_ins(){
                if($('#ins_exis').is(':checked')){
                    $('#non_exist').hide();
                    $('#exist').show();
                }else{
                    $('#non_exist').show();
                    $('#exist').hide();
                }
            }
        </script>
    </body>
</html>
