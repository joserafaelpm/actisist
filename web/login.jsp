<%-- 
    Document   : login
    Created on : 05-jun-2021, 21:00:18
    Author     : Familia Pena Mena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Software | Login</title>
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
                    <a href="index.html" class="ufps-navbar-btn">Inicio</a>
                    <a href="login.html" class="ufps-navbar-btn">Inicio Sesion</a>
                </div>
                <div class="ufps-navbar-left">
                    <div class="ufps-navbar-corporate">
                        <img src="img/logo_ufps_inverted.png" alt="Logo UFPS">
                    </div>
                </div>
            </div>
        </div>

        <div class="ufps-container ">  
            <h2 class="ufps-text-center"><b>Inicio de sesion</b></h2>
            <div class="ufps-card margin-footer-card-50 ufps-width-div-50 ufps-margin-auto ">
                <div class="ufps-card-caption ufps-padding-10">        
                    <form  action="ControlUsuario?q=reg" method="POST">
                        <label for="doc" class="ufps-title-input">Documento</label>
                        <input type="number" name="doc" id="doc" class="ufps-input-line" required/>
                        <label for="cod" class="ufps-title-input">Codigo</label>
                        <input type="number" name="cod" id="cod" class="ufps-input-line"required/>
                        <label for="pw" class="ufps-title-input">Contraseña</label>
                        <input type="password" name="pw" id="pw" class="ufps-input-line"required/>
                        <div class="ufps-title-input">
                            <a class="ufps-vista-aher" href="recuperar_clave.jsp">¿Has olvidado tu contraseña?</a>
                        </div>
                        <button type="submit" class="ufps-btn ufps-width-100 ufps-margin-top-10" value="Iniciar Sesion" >Iniciar Sesion</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="ufps-footer">
            <h3>Universidad Francisco de Paula Santander</h3>
            <p>Programa Ingeniería de Sistemas</p>
            <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
        </div>
    </body>
</html>