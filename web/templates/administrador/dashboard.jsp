<%@page import="ufps.edu.co.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Software | Dashboard</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <link href="<%=request.getContextPath()%>/css/ufps.min.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

    </head>
    <body>

        <!--HEADER-->
        <jsp:include page="/templates/includes/header.jsp" />
        <!--FIN HEADER-->

        <div class="ufps-container ">  
            <div class="ufps-rafael-titulo-adming ">
                <h1>DASHBOARD ADMINISTRATIVA</h1>
            </div>
            <div class="ufps-row" >
                <div class="ufps-col-mobile-12 ufps-col-netbook-4" >
                    <a href="<%=request.getContextPath()%>/ControlUsuario?q=list">
                        <div class="ufps-option-adm">
                            <div class="icono"><i class="fa fa-user fa-4x"></i></div>
                            <div class="titulo">Registrar docente y/o conferencista</div>
                        </div>
                    </a>

                </div >
                <div class="ufps-col-mobile-12 ufps-col-netbook-4" >

                    <a href="<%=request.getContextPath()%>/ControlActividad?q=show">
                        <div class="ufps-option-adm">
                            <div class="icono"><i class="fa fa-list fa-4x"></i></div>
                            <div class="titulo">Ver actividades registradas</div>
                        </div>
                    </a>
                </div >
                <div class="ufps-col-mobile-12 ufps-col-netbook-4" > 
                    <a href="<%=request.getContextPath()%>/ControlConvenio?q=list">
                        <div class="ufps-option-adm">
                            <div class="icono"><i class="fa fa-handshake fa-4x"></i></div>
                            <div class="titulo">Registrar Convenio</div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!--FOOTER-->
    <jsp:include page="/templates/includes/footer.jsp" />
    <!--FIN FOOTER-->

    <script src="<%=request.getContextPath()%>/js/ufps.min.js"></script>
</body>
</html>