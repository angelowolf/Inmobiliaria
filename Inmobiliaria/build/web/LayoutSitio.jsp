<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
                <link href="/../css/jquery-ui.min.css" rel="stylesheet" />
                <link href="/../css/bootstrap.min.css" rel="stylesheet">
                <link href="/../font-awesome/css/font-awesome.min.css" rel="stylesheet"> 
                <link href="/../css/estilos.css" rel="stylesheet"/>

<!--        <link href="/Inmobiliaria/css/jquery-ui.min.css" rel="stylesheet" />
        <link href="/Inmobiliaria/css/bootstrap.min.css" rel="stylesheet">
        <link href="/Inmobiliaria/font-awesome/css/font-awesome.min.css" rel="stylesheet"> 
        <link href="/Inmobiliaria/css/estilos.css" rel="stylesheet"/>-->
        <style type="text/css">
            #body{
                padding-top:  20px;
            }
        </style>
    </head>
    <body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<s:url action="loadIndex" namespace="/"/>">Inmobiliaria</a>          
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right">
                <ul class="nav navbar-nav">
                    <li>
                        <a  href="<s:url action="loadFind" namespace="/"/>">Buscar Propiedades</a>
                    </li>
                    <li>
                        <a  href="<s:url action="loadContact" namespace="/"/>">Contacto</a>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>


    <div id="body">
        <div class="container">
            <div class="row"  >
                <tiles:insertAttribute name="body" />
            </div>
        </div>
    </div>
    <div class="pie">
        <div class="container"><!-- Footer -->
            <div  id="row">
                <div class="col-xs-12 col-sm-4 col-md-5 col-lg-7">
                    <p>LOGO INMBO</p>
                </div>
                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-3">
                    <hr class="visible-xs"/>
                    <h5 class="h4">Contactanos</h5>
                    <p><s:property value="#application.contacto.telefono"  /><br />
                        <s:property value="#application.contacto.email"  /></p>
                </div>
                <div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
                    <h5 class="h4">Visitanos</h5>
                    <p><s:property value="#application.contacto.direccion"  /></p>
                </div>
            </div>
        </div>
        <div class="by" >
            <div class="container">
                <div class="col-sm-12">
                    <p class="text-right">Wolf Angelo - angelowolf21@gmail.com</p>
                </div>
            </div>
        </div>
    </div>
        <script src="/../js/jquery-2.1.1.min.js"></script>
        <script src="/../js/jquery-ui.min.js"></script>
        <script src="/../js/bootstrap.min.js"></script>
        <script src="/../js/moment.js"></script>
        <script src="/../js/bootstrap-datetimepicker.min.js"></script>
        <script src="/../js/sidebar_menu.js"></script>
        <script src="/../js/asd.js"></script>   
        <script src="/../js/es.js"></script>   
        <script src="/../js/AddFile.js"></script>
        <script src="http://maps.googleapis.com/maps/api/js"></script>
        <script src="/../js/mapa-vista.js"></script>   
        <script src="/../js/mapa-contacto.js"></script>   

<!--    <script src="/Inmobiliaria/js/jquery-2.1.1.min.js"></script>
    <script src="/Inmobiliaria/js/jquery-ui.min.js"></script>
    <script src="/Inmobiliaria/js/bootstrap.min.js"></script>
    <script src="/Inmobiliaria/js/moment.js"></script>
    <script src="/Inmobiliaria/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/Inmobiliaria/js/sidebar_menu.js"></script>
    <script src="/Inmobiliaria/js/asd.js"></script>   
    <script src="/Inmobiliaria/js/es.js"></script>   
    <script src="/Inmobiliaria/js/AddFile.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js"></script>
    <script src="/Inmobiliaria/js/mapa-vista.js"></script>   
    <script src="/Inmobiliaria/js/mapa-contacto.js"></script>-->

</body>
</html>
