$(function() {
    
    $('button[data-target="#modal-delete"]').click(function(){
        var id = $(this).data('id');
        if (id >= 0) {
            $('#modal-delete #deleteURL_id').val(id);
            $('#modal-delete .modal-body').html('¿Está seguro de que desea eliminar?');
        } else {
            $('#modal-delete .modal-body').html(id);
        }
    });
});