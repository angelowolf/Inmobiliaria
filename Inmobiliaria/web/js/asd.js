$(function () {
    var padre =$("#precio").parent();
    padre.append("<div class='input-group' id='inp-padre'></div>");
    var precio = $( "#precio" ).detach();
    precio.appendTo(padre.find('.input-group'));
    var padreNuevo =$("#precio").parent();
    padreNuevo.prepend(' <div class="input-group-addon">$</div>');
    
});