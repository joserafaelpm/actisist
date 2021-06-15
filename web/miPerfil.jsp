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

        <link href="css/ufps.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/main.css">

    </head>
    <body>
        <div class="ufps-navbar" id="menu">
            <div class="ufps-container-fluid">
                <div class="ufps-navbar-brand">
                    <div class="ufps-btn-menu" onclick="toggleMenu('menu')">
                        <div class="ufps-btn-menu-bar"></div>
                        <div class="ufps-btn-menu-bar"> </div>
                        <div class="ufps-btn-menu-bar"> </div>
                    </div>
                </div><%
                    Usuario user = ((Usuario) request.getSession().getAttribute("user"));
                    if (user == null) {
                        response.sendRedirect("login.jsp");
                    }
                %>
                <div class="ufps-navbar-right">
                    <a href="dashboard.jsp" class="ufps-navbar-btn">Inicio</a>
                    <a href="misActividades.jsp" class="ufps-navbar-btn">Mis Actividades</a>
                    <div class="ufps-dropdown" id="dropdown4">
            <div class="ufps-dropdown-content">
                <a href="#">Mi Perfil</a>
                <a href="ControlUsuario?q=log">Cerrar Sesion</a>
            </div>
        </div>
                    <a onclick="openDropdown('dropdown4')"  class="ufps-navbar-btn ufps-dropdown-btn">Hector Parra<img class="ufps-perfil-redonde" src="img/user.jpg"/>
                    </a>
                </div>
                <div class="ufps-navbar-left">
                    <div class="ufps-navbar-corporate">
                        <img src="img/logo_ufps_inverted.png" alt="Logo UFPS">
                    </div>
                </div>
            </div>
        </div>

        <div class="ufps-container ">  
<form action="ControlUsuario?q=log" method="POST"><button type="submit" class="ufps-margin-top-10">Cerrar sesión</button></form>
        
<div class="ufps-card ufps-margin-top-30">
    <div class="ufps-title-color ufps-text-center ufps-font-s">Mi Perfil</div>
    <div class="ufps-card-caption">
        <div class="img-foto-perfil">
            <img class="ufps-perfil-redonde2" src="img/user.jpg"/>
        </div>
         
 <div class="ufps-title-color ufps-text-center ufps-font-s">Información Personal</div>
 <div class="ufps-card-caption">
     <div class="ufps-row ufps-margin-top-30" >
             <div class="ufps-col-mobile-12 ufps-col-netbook-6" >
                        <label class="ufps-title-input">Nombres:</label>
                    <input type="text" class="ufps-input-line" required>
                      <label class="ufps-title-input">Documento:</label>
                    <input type="text" class="ufps-input-line" required>
                    <label class="ufps-title-input">Tipo</label>
                    <input type="text" class="ufps-input-line" required>
    
             </div >
     <div class="ufps-col-mobile-12 ufps-col-netbook-6" > 
         <label class="ufps-title-input">Apellidos</label>
                    <input type="text" class="ufps-input-line" required>
      <label class="ufps-title-input">Codigo</label>
                    <input type="text" class="ufps-input-line" required>
                    <div class="ufps-documentos ufps-margin-top-10">
                       <div class="ufps-title-color ufps-text-center ufps-font-s">Documentos acreditados</div>
                       <div class="ufps-card-caption">
                           <a href="">Curriculum </a> <a href="">Certificacion Doctorado </a>
                         <input type="file" class="ufps-input-line" required>  
                       </div>
                    </div>
     </div >
 </div >
 <input type="button" class="ufps-btn w-100 ufps-margin-top-30" value="Guardar">
 </div>
</div>
</div>

           
    </div>
    <div class="ufps-footer ufps-footer-3">
        <h3>Universidad Francisco de Paula Santander</h3>
        <p>Programa Ingeniería de Sistemas</p>
        <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
    </div>
    <script src="js/ufps.min.js"></script>
</body>
</html>