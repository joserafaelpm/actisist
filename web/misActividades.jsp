<%-- 
    Document   : registrarActividad
    Created on : 10/06/2021, 05:32:27 PM
    Author     : dunke
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
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
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
            <a href="misActividades.jsp" class="ufps-navbar-btn">Mis Actividades</a>
            <a onclick="openDropdown('dropdown4')"  class="ufps-navbar-btn ufps-dropdown-btn">Hecttor Parra <img class="ufps-perfil-redonde" src="img/user.jpg"/></a>
        </div>
        <div class="ufps-dropdown" id="dropdown4">
            <div class="ufps-dropdown-content">
                <a href="#">Opción 1</a>
                <a href="#">Opción 2</a>
                <a href="#">Opción 3</a>
            </div>
        </div>
        <div class="ufps-navbar-left">
            <div class="ufps-navbar-corporate">
                <img  src="img/logo_ufps_inverted.png" alt="Logo UFPS">
            </div>
        </div>

    </div>
</div>
    <div class="ufps-container-fluid ">
        
        <div class="ufps-row ufps-margin-top-10" >
            <div class="ufps-col-mobile-12 ufps-col-netbook-3" > 
                <div class="ufps-card">
                   <div class="ufps-card-caption ufps-padding-30">
                          <div class="ufps-div-title">
                              <label>Buscar</label>
                          </div>
                        <div class="label"><label class="ufps-title-input">Nombre</label></div>
                        <input type="text"  name="nombre" id="nombreData" class="ufps-input-line" required>
                        <div class="label"><label class="ufps-title-input">Fecha</label></div>
                        <input type="date"  name="fecha" id="fechaData"  value="2017-06-01" class="ufps-input-line" required>
                        <div class="label"><label class="ufps-title-input">Hora</label></div>
                        <input type="text"  name="hora" id="horaData" class="ufps-input-line" required>
                        <div class="label"><label class="ufps-title-input">Lugar</label></div>
                        <input type="text"  name="lugar" id="lugarData" class="ufps-input-line" required>
                        <a href="registrarActividad.jsp"  class="ufps-tx-center ufps-btn ufps-width-100 ufps-margin-top-10">Registrar Actividad</a>
                        <a href="#" class="ufps-btn ufps-width-100 ufps-margin-top-10 ufps-tx-center">Generar Informe</a>
                     
                    </div>
                </div>   
            </div >
            <div class="ufps-col-mobile-12 ufps-col-netbook-9" >
                <div class="ufps-card">
                    <div class="ufps-card-caption ufps-padding-5">
                        <div class="ufps-table-responsive">
                           <table id="example" class="display" style="width:100%">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Nombre</th>
                                    <th>Fecha</th>
                                    <th>Hora</th>
                                    <th>Lugar</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="text-align: center;"><input type="checkbox"></td>
                                    <td style="text-align: center;">Activida 1</td>
                                    <td style="text-align: center;">2021-06-02</td>
                                    <td style="text-align: center;">09:00 a.m.</td>
                                    <td style="text-align: center;">Cúcuta</td>
                                    <td style="text-align: center;"><a href="#"><i class="fa fa-edit"></i></a><a href="#"><i class="fa fa-times"></i></a></td>

                                </tr>    
                            </tbody>
                            </table>
                            </div>  
                    </div>
                </div>
            </div >
        </div >
    </div>



<div class="ufps-footer">
    <h3>Universidad Francisco de Paula Santander</h3>
    <p>Programa Ingeniería de Sistemas</p>
    <p>Desarrollado por: Rafael Peña</p>
</div>   
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

<script src="js/dataTables.min.js"></script>
    <script src="js/ufps.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
