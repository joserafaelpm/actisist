<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="css/jquery.transfer.css">
        <link rel="stylesheet" href="icon_font/css/icon_font.css">
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
                    <a href="misActividades.jsp" class="ufps-navbar-btn">Inicio</a>
                    <a href="index.jsp" class="ufps-navbar-btn">Mis Actividades</a>
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
        <div class="ufps-container">

            <div class="ufps-card">
                <form>
                    <div class="ufps-card-caption ufps-padding-5 ">

                        <div class="ufps-row">
                            <div class="ufps-col-netbook-6 ufps-col-mobile-12 ">
                              <div class="ufps-div-title ufps-title-color">
                                    Informacion de la Imagen
                                </div>
                                <div class="ufps-row ufps-margin-top-10" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-8" >
                                        <input type="file" id="fileImagen" class="ufps-input-line">
                                    </div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-4" >
                                        <img id="previsualizaImagen" src="img/logo_ufps.png" class="ufps-img-responsive" />
                                    </div >
                                </div >
                            </div>
                            <div class="ufps-col-netbook-6 ufps-col-mobile-12 ufps-margin-top-10">
                                <div class="ufps-row ufps-margin-top-10" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-4" ><label>Nombre de la actividad:</label> </div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-8" > <input type="text"  name="nombre" class="ufps-input-line" required></div>
                                </div>
                                <div class="ufps-row ufps-margin-top-20" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-10" > 
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-3" > Fecha Inicio:</div >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-9" > <input type="date"  name="nombre" class="ufps-input-line" required></div>
                                        </div>
                                    </div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-10" > 
                                        <div class="ufps-row" >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-3" > Fecha Fin:</div >
                                            <div class="ufps-col-mobile-12 ufps-col-netbook-9" > <input type="date"  name="nombre" class="ufps-input-line" required></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="ufps-row ufps-margin-top-20" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-2" >Tipo:</div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-10" >
                                        <select class="ufps-input-line" name="select">
                                            <option value="value1">Value 1</option>
                                            <option value="value2" selected>Value 2</option>
                                            <option value="value3">Value 3</option>
                                        </select>
                                    </div >
                                </div >
                                <div class="ufps-row ufps-margin-top-20" >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-2" >Semestre:</div >
                                    <div class="ufps-col-mobile-12 ufps-col-netbook-10" >
                                        <select class="ufps-input-line" name="select">
                                            <option value="value1">Value 1</option>
                                            <option value="value2" selected>Value 2</option>
                                            <option value="value3">Value 3</option>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="ufps-pl-pr-15 ufps-margin-top-20">
                                    Tematica:
                                    <textarea class="ufps-input" rows="4" cols="50"></textarea>
                                    Descripción:
                                    <textarea class="ufps-input" rows="4" cols="50"></textarea>
                                    Lugar:
                                    <input type="text" class="ufps-input" rows="4" cols="50"></input>
                                    Conferencistas:
                                    <!--Informacion: 
                                    https://www.jqueryscript.net/form/Groupable-Searchable-Dual-Listbox-Transfer.jsp
                                    -->
                                    <div class="ufps-conferencistas-div">
                                        <div id="transfer1" class="transfer-demo"></div>
                                    </div
                                </div>
                                <div class="ufps-pl-pr-15 ufps-margin-top-20">
                                    Movilidad
                                    <select class="ufps-input-line" id="movilidadSelect" name="select">
                                        <option value="entrante">Entrante</option>
                                        <option value="saliente">Saliente</option>
                                    </select>
                                </div>
                                <div id="movilidad" hidden class="ufps-pl-pr-15 ufps-margin-top-20">
                                        <div class="ufps-card">
                                            <div class="ufps-card-caption">
                                                <p>Institucion de destino</p>
                                                <div class="ufps-row" >
                                                    <div class="ufps-col-mobile-12 ufps-col-netbook-6" >

                                                        <div class="ufps-row ufps-margin-top-20" >
                                                            <div class="ufps-col-mobile-12 ufps-col-netbook-4" >Pais Destino</div >
                                                            <div class="ufps-col-mobile-12 ufps-col-netbook-8" >
                                                                <select class="ufps-input-line" name="select">
                                                                    <option value="value1">Colombia</option>
                                                                    <option value="value2" selected>Japon</option>
                                                                    <option value="value3">Value 3</option>
                                                                </select>
                                                            </div >
                                                        </div >

                                                    </div >
                                                    <div class="ufps-col-mobile-12 ufps-col-netbook-6" >

                                                        <div class="ufps-row ufps-margin-top-20" >
                                                            <div class="ufps-col-mobile-12 ufps-col-netbook-4" >Ciudad</div >
                                                            <div class="ufps-col-mobile-12 ufps-col-netbook-8" >
                                                                <select class="ufps-input-line" name="select">
                                                                    <option value="value1">Colombia</option>
                                                                    <option value="value2" selected>Japon</option>
                                                                    <option value="value3">Value 3</option>
                                                                </select>
                                                            </div >
                                                        </div >

                                                    </div >
                                                </div >
                                                <div class="ufps-margin-top-20">
                                                    Tiempo de Estadia:
                                                    <input type="number" id="fileImagen" class="ufps-input-line">
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                <div class="ufps-margin-top-30">
                                    <label class="ufps-pl-pr-15 ">Convenios:</label>
                                </div>

                                <!--Informacion: 
                                https://www.jqueryscript.net/form/Groupable-Searchable-Dual-Listbox-Transfer.jsp
                                -->
                                <div class=" ufps-conferencistas-div">
                                    <div id="transfer2" class="transfer-demo"></div>
                                </div>
                                
                            </div>
                        </div>
                        <input type="submit"  class="ufps-btn ufps-width-100 ufps-margin-top-20" value="Registrar Actividad"></input>
                </form>
            </div>
        </div>

    </div>



    <div class="ufps-footer">
        <h3>Universidad Francisco de Paula Santander</h3>
        <p>Programa Ingeniería de Sistemas</p>
        <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script src="js/jquery.bootstrap-duallistbox.js"></script>
    <script src="js/dataTables.min.js"></script>
    <script src="js/jquery.transfer.js"></script>
    <script src="js/ufps.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
