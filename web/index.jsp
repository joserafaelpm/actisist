<%-- 
    Document   : index
    Created on : 05-jun-2021, 21:00:08
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
            <a href="" class="ufps-navbar-btn">Inicio</a>
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
      <h1 class="ufps-text-center">ACTIVIDADES</h1>
      <div class="ufps-row" >
        <div class="ufps-col-mobile-12 ufps-col-netbook-6 " >
          <img class="ufps-img-responsive" src="img/actividad1.jpg"/>
        </div >
        <div class="ufps-col-mobile-12 ufps-col-netbook-6 ufps-actividades" >
          <form>
            <label>Actividad</label>
            <input type="text" placeholder="Texto" class="ufps-input-line" disabled>
            <label>Fecha</label>
            <input type="text" placeholder="Texto" class="ufps-input-line" disabled>
            <label>Hora</label>
            <input type="text" placeholder="Texto" class="ufps-input-line" disabled>
            <label>Lugar</label>
            <input type="text" placeholder="Texto" class="ufps-input-line" disabled>
            <label>Organizador</label>
            <input type="text" placeholder="Texto" class="ufps-input-line" disabled>
        </form>  
        </div >
      </div >
    </div>



<div class="ufps-footer-2 ">
    <h3>Universidad Francisco de Paula Santander</h3>
    <p>Programa Ingeniería de Sistemas</p>
    <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
</div>   

    <script src="js/ufps.min.js"></script>
</body>
</html>