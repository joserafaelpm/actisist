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
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <header>
        <a href="index.jsp"><img class="brandlogo" src="img/logo_ufps.png"/></a>
        <nav>
            <li><a href="index.jsp">Inicio</a></li>
            <li><a href="login.jsp">Iniciar Sesion</a></li>
            <li><img src="img/logo_ingsistemas.png"/></li>
        </nav>

    </header>
    <main> 
        <section class="login-usuarios">  
            <form class="formulario-login" action="ControlUsuario?q=reg" method="POST">
                <div class="title-form">
                    <h1>Inicio de Sesion</h1>
                </div> 
                <label for="doc">Documento</label>
                <input type="text" name="doc" id="doc"/>
                <label for="cod">Codigo</label>
                <input type="text" name="cod" id="cod"/>
                <label for="pw">Contraseña</label>
                <input type="password" name="pw" id="pw"/>
                <a href="recuperar_clave.jsp" >¿Has olvidado tu contraseña?</a>
                <button type="submit" >Iniciar Sesion</button>
            </form>
        </section>

    </main>
    <footer>
        &copy; 2021 | Analisis y Diseño de Sistemas de Información
    </footer>
</body>
</html>