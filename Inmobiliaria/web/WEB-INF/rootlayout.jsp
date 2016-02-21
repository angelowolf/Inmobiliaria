
<%@page import="Persistencia.Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-bootstrap-tags" prefix="sb" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:importAttribute name="stylesheet" />
<tiles:importAttribute name="javascript" />
<!DOCTYPE html>
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
        <s:iterator value="#attr.stylesheet" var="cadaCSS">
            <link href="<s:url value="%{cadaCSS}"/>" rel="stylesheet" type="text/css" >
        </s:iterator>
    </head>

    <body>
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<s:url action="index" namespace="/"/>"><i class="fa fa-home fa-4"></i> Inmobiliaria</a> 
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">

                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="<s:url action="loadEdit" namespace="/Admin"/>"><i class="fa fa-gear fa-fw"></i> Configuración</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="<s:url action="logout" namespace="/Admin"/>"><i class="fa fa-sign-out fa-fw"></i> Cerrar sesión</a>
                            </li>
                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-tint fa-fw"></i> Servicios<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="<s:url action="listar" namespace="/Servicio"/>"><i class="glyphicon glyphicon-list fa-fw "></i> Todos</a></li>
                                    <li><a href="<s:url action="nuevo" namespace="/Servicio"/>"><i class="glyphicon glyphicon-plus fa-fw "></i> Nuevo</a></li>
                                </ul>                              
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-check fa-fw"></i> Ambientes<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="<s:url action="listar" namespace="/Ambiente"/>"><i class="glyphicon glyphicon-list fa-fw "></i> Todos</a></li>
                                    <li><a href="<s:url action="nuevo" namespace="/Ambiente"/>"><i class="glyphicon glyphicon-plus fa-fw "></i> Nuevo</a></li>
                                </ul>                              
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-star fa-fw"></i> Tipo Propiedades<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="<s:url action="listar" namespace="/TipoPropiedad"/>"><i class="glyphicon glyphicon-list fa-fw "></i> Todos</a></li>
                                    <li><a href="<s:url action="nuevo" namespace="/TipoPropiedad"/>"><i class="glyphicon glyphicon-plus fa-fw "></i> Nuevo</a></li>
                                </ul>                              
                            </li>
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-usd fa-fw"></i> Tipo monedas<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="<s:url action="listar" namespace="/TipoMoneda"/>"><i class="glyphicon glyphicon-list fa-fw "></i> Todos</a></li>
                                    <li><a href="<s:url action="nuevo" namespace="/TipoMoneda"/>"><i class="glyphicon glyphicon-plus fa-fw "></i> Nuevo</a></li>
                                </ul>                              
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-home fa-fw"></i> Propiedades<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="<s:url action="listar" namespace="/Propiedad"/>"><i class="glyphicon glyphicon-list fa-fw "></i> Todos</a></li>
                                    <li><a href="<s:url action="nuevo" namespace="/Propiedad"/>"><i class="glyphicon glyphicon-plus fa-fw "></i> Nuevo</a></li>
                                </ul>                              
                            </li>
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-thumbs-up fa-fw"></i> Propiedades Destacadas<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="<s:url action="listar" namespace="/Destacado"/>"><i class="glyphicon glyphicon-list fa-fw "></i> Todos</a></li>
                                    <li><a href="<s:url action="nuevo" namespace="/Destacado"/>"><i class="glyphicon glyphicon-plus fa-fw "></i> Nuevo</a></li>
                                </ul>                              
                            </li>
                            <li>
                                <a href="<s:url action="loadContacto" namespace="/Contacto"/>"><i class="glyphicon glyphicon-user fa-fw "></i> Contacto</a>                    
                            </li>
                            <li>
                                <a href="<s:url action="imagenes" namespace="/Propiedad"/>"><i class="glyphicon glyphicon-hdd fa-fw "></i> Espacio Disponible</a>                    
                            </li>
                        </ul> <g
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">                            
                            <tiles:insertAttribute name="modal-delete" />
                            <tiles:insertAttribute name="body" />
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->

        <s:iterator value="#attr.javascript" var="cadaJS">
            <script src="<s:url value="%{cadaJS}"/>" ></script>
        </s:iterator>
    </body>
</html>
