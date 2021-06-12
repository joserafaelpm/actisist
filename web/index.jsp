<%-- 
    Document   : index
    Created on : 05-jun-2021, 21:00:08
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
       <section class="contenido-principal">
            <div class="title-section">
                Actividades
            </div>
            <div class="container-activity">
                <div class="col-1 w-s1">
                    <img class="w-80" src="img/actividad1.jpg"/>
                </div>
                <div class="col-2 flex-col w-s1 mb-80">
                   <label>Nombre Actividad</label>
                   <input type="text"/>
                   <label>Fecha</label>
                   <input type="text"/>
                   <label>Hora</label>
                   <input type="text"/>
                   <label>Lugar</label>
                   <input type="text"/>
                   <label>Organizador</label>
                   <input type="text"/>
                </div>
            </div>
       </section>
    </main>
    <footer>
        &copy; 2021 | Analisis y Diseño de Sistemas de Información
    </footer>
</body>
</html>