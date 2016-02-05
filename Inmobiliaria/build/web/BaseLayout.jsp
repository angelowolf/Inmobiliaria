<%@page import="Persistencia.Modelo.Usuario"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    HttpSession sesion = request.getSession();
    Usuario userLogin = (Usuario) sesion.getAttribute("user");

    if (userLogin == null) {
        response.sendRedirect("/Admin/Login.jsp");
    }
%>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <link  href="/../css/jquery-ui.min.css" rel="stylesheet"/>
        <link href="/../css/bootstrap.min.css" rel="stylesheet">
        <link href="/../font-awesome/css/font-awesome.min.css" rel="stylesheet"> 
        <link href="/../css/simple-sidebar.css" rel="stylesheet">
        <link  href="/../css/estilos.css" rel="stylesheet"/>

        <!--        <link  href="/Inmobiliaria/css/jquery-ui.min.css" rel="stylesheet"/>
                <link href="/Inmobiliaria/css/bootstrap.min.css" rel="stylesheet">
                <link href="/Inmobiliaria/font-awesome/css/font-awesome.min.css" rel="stylesheet"> 
                <link href="/Inmobiliaria/css/simple-sidebar.css" rel="stylesheet">
                <link href="/Inmobiliaria/css/estilos.css" rel="stylesheet" />-->
    </head>
    <style type="text/css">           
        .navbar-nav li {
            line-height: 50px;
        }
    </style>
    <body>
    <nav class="navbar navbar-default no-margin" id="main-navbar" >
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header fixed-brand">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"  id="menu-toggle">
                <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
            </button>
            <a class="navbar-brand" href="<s:url action="index" namespace="/"/>"><i class="fa fa-home fa-4"></i> Inmobiliaria</a>        
        </div><!-- navbar-header-->

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        </li>
                        <li><a href="<s:url action="loadEdit" namespace="/Admin"/>"><i class="fa fa-gear fa-fw"></i> Configuración</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="<s:url action="logout" namespace="/Admin"/>"><i class="fa fa-sign-out fa-fw"></i> Cerrar sesión</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
            </ul>
        </div><!-- bs-example-navbar-collapse-1 -->



        <!--
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active" ><button class="navbar-toggle collapse in" data-toggle="collapse" id="menu-toggle-2"> <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span></button></li>
                    </ul>
                    <ul class="nav navbar-nav  navbar-right">
                        <li><a href="<s:url action="loadEdit" namespace="/Admin"/>"><s:property value="#session.user.apellido"/> <s:property value="#session.user.nombre"/></a></li>
                    </ul>
                </div> bs-example-navbar-collapse-1 -->
    </nav>
    <div id="wrapper">
        <!-- Sidebar -->
        <div id="sidebar-wrapper" >
            <ul class="sidebar-nav nav-pills nav-stacked" id="menu">

                <li>
                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-tint  fa-stack-1x "></i></span> Servicios</a>
                    <ul class="nav-pills nav-stacked" style="list-style-type:none;">
                        <li><a href="<s:url action="listar" namespace="/Servicio"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-list fa-stack-1x "></i></span>Todos</a></li>
                        <li><a href="<s:url action="nuevo" namespace="/Servicio"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-plus fa-stack-1x "></i></span>Nuevo</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="fa fa-check  fa-stack-1x "></i></span> Ambientes</a>
                    <ul class="nav-pills nav-stacked" style="list-style-type:none;">
                        <li><a href="<s:url action="listar" namespace="/Ambiente"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-list fa-stack-1x "></i></span>Todos</a></li>
                        <li><a href="<s:url action="nuevo" namespace="/Ambiente"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-plus fa-stack-1x "></i></span>Nuevo</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="fa fa-star fa-stack-1x "></i></span> Tipo Propiedades</a>
                    <ul class="nav-pills nav-stacked" style="list-style-type:none;">
                        <li><a href="<s:url action="listar" namespace="/TipoPropiedad"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-list fa-stack-1x "></i></span>Todos</a></li>
                        <li><a href="<s:url action="nuevo" namespace="/TipoPropiedad"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-plus fa-stack-1x "></i></span>Nuevo</a></li>       
                    </ul>
                </li>
                <li>
                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-usd fa-stack-1x "></i></span> Tipo monedas</a>
                    <ul class="nav-pills nav-stacked" style="list-style-type:none;">
                        <li><a href="<s:url action="listar" namespace="/TipoMoneda"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-list fa-stack-1x "></i></span>Todos</a></li>
                        <li><a href="<s:url action="nuevo" namespace="/TipoMoneda"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-plus fa-stack-1x "></i></span>Nuevo</a></li>       
                    </ul>
                </li>
                <li>
                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="fa fa-home fa-stack-1x "></i></span> Propiedades</a>
                    <ul class="nav-pills nav-stacked" style="list-style-type:none;">
                        <li><a href="<s:url action="listar" namespace="/Propiedad"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-list fa-stack-1x "></i></span>Todos</a></li>
                        <li><a href="<s:url action="nuevo" namespace="/Propiedad"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-plus fa-stack-1x "></i></span>Nuevo</a></li>       
                    </ul>
                </li>
                <li>
                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-thumbs-up fa-stack-1x "></i></span> Propiedades Destacadas</a>
                    <ul class="nav-pills nav-stacked" style="list-style-type:none;">
                        <li><a href="<s:url action="listar" namespace="/Destacado"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-list fa-stack-1x "></i></span>Todos</a></li>
                        <li><a href="<s:url action="nuevo" namespace="/Destacado"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-plus fa-stack-1x "></i></span>Nuevo</a></li>       
                    </ul>
                </li>
                <li>
                    <a href="<s:url action="loadContacto" namespace="/Contacto"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-user fa-stack-1x "></i></span> Contacto</a>                    
                </li>
                <li>
                    <a href="<s:url action="imagenes" namespace="/Propiedad"/>"><span class="fa-stack fa-lg pull-left"><i class="glyphicon glyphicon-hdd fa-stack-1x "></i></span> Espacio Disponible</a>                    
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid xyz">
                <div class="row">
                    <tiles:insertAttribute name="body" />
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->

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
    <script src="/../js/mapa-alta.js"></script>   
    <script src="/../js/mapa-edit.js"></script>   
    <script src="/../js/mapa-edit-contacto.js"></script>   

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
        <script src="/Inmobiliaria/js/mapa-alta.js"></script>   
        <script src="/Inmobiliaria/js/mapa-edit.js"></script>   
        <script src="/Inmobiliaria/js/mapa-edit-contacto.js"></script>   -->
</body>
</html>