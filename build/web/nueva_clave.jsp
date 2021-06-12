<%-- 
    Document   : nueva_clave
    Created on : 05-jun-2021, 21:00:36
    Author     : Familia Pena Mena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <title>SISRECA | UFPS</title>
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
        </div>
        <div class="ufps-navbar-right">
            <a href="index.jsp" class="ufps-navbar-btn">Inicio</a>
            <a href="login.jsp" class="ufps-navbar-btn">Inicio Sesion</a>
        </div>
        <div class="ufps-navbar-left">
            <div class="ufps-navbar-corporate">
                <img src="img/logo_ufps_inverted.png" alt="Logo UFPS">
            </div>
        </div>

    </div>
</div>
    <div class="ufps-container ">
        <h2 class="ufps-text-center"><b>Recupero de Contraseña</b></h2>
        
        <div class="ufps-card margin-footer-card-50 ufps-width-div-50 ufps-margin-auto ">
            <div class="ufps-card-caption ufps-padding-10">
                <form>
                    <div class="label">
                        <label class="ufps-title-input">Nueva Contraseña</label>
                    </div>
                    <input type="text"  name="newclave" class="ufps-input-line" required>
                    <div class="label">
                        <label class="ufps-title-input">Repita la contraseña</label>
                    </div>
                    <input type="text"  name="repenewclave" class="ufps-input-line" required>
                    <input type="submit"  class="ufps-btn ufps-width-100 ufps-margin-top-10" value="Enviar"></input>
                </form>
            </div>
        </div>
        
    </div>



<div class="ufps-footer">
    <h3>Universidad Francisco de Paula Santander</h3>
    <p>Programa Ingeniería de Sistemas</p>
    <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
</div>     

    <script src="js/ufps.min.js"></script>
</body>
</html>
