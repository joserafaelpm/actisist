<%@page import="ufps.edu.co.dto.Usuario"%>
<%
    Usuario user = ((Usuario) request.getSession().getAttribute("user"));
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!--HEADER-->
<div class="ufps-navbar" id="menu">
    <div class="ufps-container-fluid">
        <div class="ufps-navbar-right">
            <%if (user.getIdRol().getId() == 1) {%><a href="dashboard.jsp" class="ufps-navbar-btn">Inicio</a>
            <%} else {%><a href="<%=request.getContextPath()%>/ControlActividad?q=showFor" class="ufps-navbar-btn">Mis Actividades</a><%}%>

            <%if (user.getIdRol().getId() == 1) {%>
            <button  onclick="openDropdown('dropdown4')"  class="ufps-dropdown-btn">
                <%=user.getNombre()%>
                <img class="ufps-perfil-redonde" src="<%=request.getContextPath()%>/img/admin.png">
            </button>
            <%} else {%>
            <button  onclick="openDropdown('dropdown4')"  class="ufps-dropdown-btn">
                <%=user.getNombre() + " " + user.getApellido()%> 
                <img class="ufps-perfil-redonde" src="<%=user.getDocente().encodeImage()%>">
            </button>
            <%}%>

            <div class="ufps-dropdown" id="dropdown4" style="transform: translateX(-160px); z-index: 1;">
                <div class="ufps-dropdown-content">
                    <%if (user.getIdRol().getId() != 1) {%><a href="<%=request.getContextPath()%>/ControlUsuario?q=perfil">Mi Perfil</a><%}%>
                    <a href="<%=request.getContextPath()%>/ControlUsuario?q=log">Cerrar Sesion</a>
                </div>
            </div>
        </div>
        <div class="ufps-navbar-left">
            <div class="ufps-navbar-corporate">
                <img src="<%=request.getContextPath()%>/img/logo_ufps_inverted.png" alt="Logo UFPS">
            </div>
        </div>
    </div>
</div>