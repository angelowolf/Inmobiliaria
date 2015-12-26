<%-- 
    Document   : Recuperar
    Created on : 21/12/2015, 00:05:35
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
        <!--        <style type="text/css">
                    body{
                        background-color: #eee;
                    }          
                    #contenedor{
                        padding-top: 150px;
                    }
        
                </style>-->
    </head>
    <body>
        <div class="wrapper">
            <div class="form-header">
                <h1 style="color: white;font-family: inherit;">Recuperar Contraseña</h1>
            </div>
            <s:form id="form" style="padding-top: 25px;" action="recuperarCuenta" method="POST" validate="true" theme="bootstrap" cssClass="form-horizontal">
                <s:actionerror theme="bootstrap"/>
                <s:actionmessage theme="bootstrap"/>
                <s:fielderror theme="bootstrap"/>
                <div class="form-group has-feedback">
                    <div class="input-group-addon">
                        <div class="glyphicon glyphicon-user"></div>
                    </div>
                    <input class="form-control" id="username" name="email" placeholder="Ingrerse el email de la cuenta a recuperar"  type="text">
                </div>
                <div class="form-group submit">
                    <input class="btn btn-lg" type="submit" value="ENVIAR">
                </div>
            </s:form>
        </div>
    </body>
</html>
