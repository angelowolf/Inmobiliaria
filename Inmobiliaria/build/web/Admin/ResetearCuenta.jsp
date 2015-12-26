<%-- 
    Document   : ResetearCuenta
    Created on : 21/12/2015, 00:06:53
    Author     : Angelo
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--        <link href="/../css/bootstrap.min.css" rel="stylesheet">
                <link href="/../css/stylesLogin.css" media="all" rel="stylesheet">
                <link href="/../css/font-google.css" rel="stylesheet" type="text/css">-->

        <link href="/Inmobiliaria/css/bootstrap.min.css" rel="stylesheet">
        <link href="/Inmobiliaria/css/stylesLogin.css" media="all" rel="stylesheet">
        <link href="/Inmobiliaria/css/font-google.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <div class="wrapper">
            <div class="form-header">
                <h1 style="color: white;font-family: inherit;">Recuperar Contraseña</h1>
            </div>
            <s:form id="form" style="padding-top: 25px;" action="resetearCuenta" method="POST" validate="true" cssClass="form-horizontal">
                <div class="text-center">
                    <s:actionerror theme="bootstrap"/>
                    <s:actionmessage theme="bootstrap"/>
                    <s:fielderror theme="bootstrap"/>
                </div> 
                <div class="form-group has-feedback">
                    <div class="input-group-addon">
                        <div class="glyphicon glyphicon-user"></div>
                    </div>
                    <input class="form-control" id="username" name="email" placeholder="Ingrerse el email"  type="text">
                </div>
                <div class="form-group has-feedback">
                    <div class="input-group-addon">
                        <div class="glyphicon glyphicon-exclamation-sign"></div>
                    </div>
                    <input class="form-control" id="username" name="codigo" placeholder="Ingrerse el codigo"  type="text">
                </div>
                <div class="form-group has-feedback">
                    <div class="input-group-addon">
                        <div class="glyphicon glyphicon-lock"></div>
                    </div>
                    <input class="form-control" id="username" name="clave1" placeholder="Ingrese una nueva contraseña"  type="text">
                </div>
                <div class="form-group has-feedback">
                    <div class="input-group-addon">
                        <div class="glyphicon glyphicon-lock"></div>
                    </div>
                    <input class="form-control" id="username" name="clave2" placeholder="Repita la contraseña"  type="text">
                </div>           
                <div class="form-group submit">
                    <input class="btn btn-lg" type="submit" value="ENVIAR">
                </div>  
            </s:form>

        </div>
    </body>
</html>
