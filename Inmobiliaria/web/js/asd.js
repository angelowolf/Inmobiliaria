$(function () {
    $('#acCliente').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "clientesJSON",
                type: "POST",
                data: {
                    term: request.term
                },
                dataType: "json",
                success: function (data) {
                    response(data);
                }
            });
        },
        select: function (event, ui) {
            $('#idCliente').val(ui.item.id);
        }
    });

    $('#acBateria').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "bateriasJSON",
                type: "POST",
                data: {
                    term: request.term
                },
                dataType: "json",
                success: function (data) {
                    response(data);
                }
            });
        },
        select: function (event, ui) {
            $('#idBateria').val(ui.item.id);
        }
    });

    $('#acMarca').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "marcasJSON",
                type: "POST",
                data: {
                    term: request.term
                },
                dataType: "json",
                success: function (data) {
                    response(data);
                }
            });
        },
        select: function (event, ui) {
            $('#idMarca').val(ui.item.id);
        }
    });

    $('#acModelo').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "modelosJSON",
                type: "POST",
                data: {
                    term: request.term
                },
                dataType: "json",
                success: function (data) {
                    response(data);
                }
            });
        },
        select: function (event, ui) {
            $('#idModelo').val(ui.item.id);
        }
    });

    var inputFecha = $('input[id=fecha]');
    inputFecha.after('<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>');
    inputFecha.parent().css("display", "inline-table");
    $('#fecha').datetimepicker({
        locale: 'es',
        format: 'DD/MM/YYYY'
    });
});