/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var maximo = 10485760;
$(document).ready(function ()
{
    bindear();

    $("#btnAgregar").click(function (event) {
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
        bindear();
    });
});

function bindear() {
    var inputs = $('#imagenes').find('input');
    var alertaSize = $("#alerta-size");
    $.each(inputs, function (index, input) {
        
        $(input).bind('change', function () {
            var actual = 0;
            $.each(inputs, function (index, input) {
                actual += this.files[0] ? this.files[0].size : 0;
            });
            alertaSize.show();
            if (actual == 0) {alertaSize.hide();}
            else if (actual > maximo) {
                alertaSize.removeClass("alert-info").addClass("alert-danger").html("Ha superado el tamaño máximo de carga de imágenes (10 MB) por " + ((actual-maximo)/1024) + " KB.");
                $("#submit").prop('disabled', true);
            } else {
                if (alertaSize.hasClass("alert-danger")) {
                    alertaSize.removeClass("alert-danger").addClass("alert-info").html("Tamaño actual de las imagenes "+Math.round(actual/1024) + " KB");
                    $("#submit").prop('disabled', false);
                }
                else { alertaSize.html("Tamaño actual de las imagenes "+Math.round(actual/1024) + " KB"); }
            }
        });
        
    });
}