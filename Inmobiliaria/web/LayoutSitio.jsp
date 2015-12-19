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
        <link rel="stylesheet" href="/Inmobiliaria/css/jquery-ui.min.css" />
        <link href="/Inmobiliaria/css/bootstrap.min.css" rel="stylesheet">
        <link href="/Inmobiliaria/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet"> 
        <link rel="stylesheet" href="/Inmobiliaria/css/bootstrap-datetimepicker.min.css" />
        <link href="/Inmobiliaria/css/simple-sidebar.css" rel="stylesheet">
        <link rel="stylesheet" href="/Inmobiliaria/css/estilos.css" />
    </head>
    <body>
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row">
                    <tiles:insertAttribute name="body" />
                </div>
            </div>
        </div>
        <script src="/Inmobiliaria/js/jquery-2.1.1.min.js"></script>
        <script src="/Inmobiliaria/js/jquery-ui.min.js"></script>
        <script src="/Inmobiliaria/js/bootstrap.min.js"></script>
        <script src="/Inmobiliaria/js/moment.js"></script>
        <script src="/Inmobiliaria/js/bootstrap-datetimepicker.min.js"></script>
        <script src="/Inmobiliaria/js/sidebar_menu.js"></script>
        <script src="/Inmobiliaria/js/asd.js"></script>   
        <script src="/Inmobiliaria/js/es.js"></script>   
        <script src="/Inmobiliaria/js/AddFile.js"></script>   
    </body>
</html>
