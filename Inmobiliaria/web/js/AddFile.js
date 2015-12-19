/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function ()
{
    $("#btnAgregar").click(function (event)
    {
        event.preventDefault();
        var num = $("input[type=file]").length;
        var numeroSiguiente = num + 1;
        var elementoNuevo = $("#imagen" + num).clone().attr('id', 'imagen' + numeroSiguiente).attr("name", "imagen");
        var etiquetaNueva = $("label[for=imagen" + num + "]").clone().attr("for", "imagen" + numeroSiguiente).text("Imagen " + numeroSiguiente + ": ");
        $("#imagen" + num).after(elementoNuevo);
        var imagenes = $("#imagenes");
        imagenes.append("<div class='form-group'></div>");
        var ultimoForm = imagenes.find('.form-group:last');
        ultimoForm.append(etiquetaNueva);
        ultimoForm.append('<div class="col-sm-9 controls"></div>');
        ultimoForm.find('.controls').append(elementoNuevo);
    });
});