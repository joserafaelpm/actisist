<%-- 
    Document   : nueva_clave
    Created on : 05-jun-2021, 21:00:36
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
            <form action="nueva_clave.jsp" class="formulario-login">
                <div class="title-form mb-25">
                    <h1>Recupero Contraseña</h1>
                </div> 
                <label>Nueva contraseña</label>
                <input type="password" />
                <label>Confirmar contraseña</label>
                <input type="password" class="mb-25" />
                <button type="submit" >Enviar</button>
            </form>
        </section>

    </main>
    <footer>
        &copy; 2021 | Analisis y Diseño de Sistemas de Información
    </footer>
</body>
</html>
