<%-- 
    Document   : index
    Created on : 06-jul-2014, 14:53:13
    Author     : Angelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="/Inmobiliaria/css/bootstrap.min.css" rel="stylesheet">
        <link href="/Inmobiliaria/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet"> 
        <link href="/Inmobiliaria/css/animate.min.css" media="all" rel="stylesheet">
        <link href="/Inmobiliaria/css/font-google.css" rel="stylesheet" type="text/css">
        <link href="/Inmobiliaria/css/stylesLogin.css" media="all" rel="stylesheet">
        <script src="/Inmobiliaria/js/modernizr.min.js"></script>
    </head>

    <body>


        <div class="wrapper">
            <div class="form-header">
                <h1 style="color: white;font-family: inherit;">Inmobiliaria Patito Feo - Inicio De Sesion</h1>
            </div>
            <s:form class="form animate-form" id="form" action="login" namespace="/Admin">
                <s:actionerror theme="bootstrap"/>
                <s:actionmessage theme="bootstrap"/>
                <s:fielderror theme="bootstrap"/>
                <div class="form-group has-feedback">
                    <label class="control-label sr-only" for="username">Nombre de Usuario</label>
                    <div class="input-group-addon">
                        <div class="glyphicon glyphicon-user"></div>
                    </div>
                    <input class="form-control" id="username" name="username" placeholder="Usuario" type="text">
                    <span class="glyphicon glyphicon-ok form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <label class="control-label sr-only" for="password">Contraseña</label>
                    <div class="input-group-addon">
                        <div class="glyphicon glyphicon-lock"></div>
                    </div>
                    <input class="form-control" id="password" name="password" placeholder="Contraseña" type="password"><span class="glyphicon glyphicon-ok form-control-feedback"></span>
                </div>
                <div class="form-group submit">
                    <input class="btn btn-lg" type="submit" value="ENVIAR">
                </div>
                <div class="">
                    <a href="/Inmobiliaria/Admin/RecuperarCuenta.jsp">¿Olvidaste la contraseña?</a>
                </div>
            </s:form>
        </div>

        <script src="/Inmobiliaria/js/jquery-2.1.1.min.js"></script>
        <script src="/Inmobiliaria/js/jquery.validation.js"></script>
        <script src="/Inmobiliaria/js/messages_es.js"></script>
        <script src="/Inmobiliaria/js/main.js"></script>
    </body>
</html>
