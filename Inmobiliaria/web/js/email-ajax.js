$(document).ready(function () {
    $('#form-contact').on('submit', function (e) {
        e.preventDefault();
        enviar_mail();
    });
});

function enviar_mail() {
    var atributos = $('form').serialize();
    
    $("#contenedor-contacto").fadeOut(function () {
        $('.loading').fadeIn(function () {
            $.ajax({
                url: "mandarEmail",
                data: atributos,
                success: function (data) {
                    $("#contenedor-contacto").fadeOut(function () {
                        $('.loading').hide();
                        if (data.codigo) {
                            $("#alerta-mail").html(data.mensaje);

                            if (data.codigo === 200) {
                                $("#alerta-mail").addClass('alert-info');
                            } else {
                                $("#alerta-mail").addClass('alert-danger');
                            }
                        }
                        $("#alerta-mail").fadeIn();
                    });
                },
                error: function () {
                    $('.loading').hide();
                    alert("Ocurrió un problema al mandar el email.");
                }});

        });
    });


}