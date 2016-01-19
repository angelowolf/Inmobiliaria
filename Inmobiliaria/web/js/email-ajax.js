$(document).ready(function () {
    $('#form-contact').on('submit', function (e) {
        e.preventDefault();
        enviar_mail();
    });
});



function enviar_mail() {

    var atributos = 'propiedad.idPropiedad=' + $("#idpropiedad").val() +
            "&nombre=" + $("#nombre").val() +
            "&email=" + $("#email").val() +
            "&telefono=" + $("#telefono").val() +
            "&consulta=" + $("#consulta").val();
    $.ajax({
        async: false,
        url: "mandarEmail",
        data: atributos,
        success: function () {
            $("#contenedor-contacto").addClass("hidden");
            $("#alerta-mail").removeClass("hidden");
        },
        error: function () {
            alert("Ocurrio un problema al mandar el email.");
        }});

}