<%-- 
    Document   : test
    Created on : 14/12/2015, 21:57:01
    Author     : Angelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <s:form action="" theme="simple">
            <s:label for="imagen1" value="Imagen 1: " />
            <s:file id="imagen1" name="imagen" /> <br /> 
            <s:submit value="Enviar" />    
        </s:form>

        <button id="btnAgregar">Agregar Elemento</button>
    </body>
</html>

<script src="/Inmobiliaria/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">

    $(document).ready(function ()
    {
        $("#btnAgregar").click(function ()
        {
            var num = $("input[type=file]").length;
            var numeroSiguiente = num + 1;
            var elementoNuevo = $("#imagen" + num).clone().attr('id', 'imagen' + numeroSiguiente).attr("name", "imagen");
            var etiquetaNueva = $("label[for=imagen" + num + "]").clone().attr("for", "imagen" + numeroSiguiente).text("Imagen " + numeroSiguiente + ": ");

            $("#imagen" + num).after(elementoNuevo);
            elementoNuevo.before(etiquetaNueva);
            etiquetaNueva.before("<br />");
        });
    });
</script>
