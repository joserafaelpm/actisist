<%@page import="ufps.edu.co.dto.Usuario"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    Usuario user = ((Usuario) request.getSession().getAttribute("user"));
%>
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
<%if(user.getIdRol().getId() == 1) {%>
<div class="label"><label class="ufps-title-input">Docente</label></div>
<input type="text"  name="docente" id="docenteData" class="ufps-input-line" required>
<%}%>