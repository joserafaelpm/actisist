$(document).ready(function () {
    let oTable = $('#example').dataTable();
    $('#nombreData').on('input', function (e) {
        let valInput = $('#nombreData').val();
        oTable.fnFilter(valInput);
    });
    $('#horaData').on('input', function (e) {
        let valInput = $('#horaData').val();
        oTable.fnFilter(valInput);
    });
    $('#fechaData').on('input', function (e) {
        let valInput = $('#fechaData').val();
        oTable.fnFilter(valInput);
    });
    $('#docenteData').on('input', function (e) {
        let valInput = $('#docenteData').val();
        oTable.fnFilter(valInput);
    });
    $('#lugarData').on('input', function (e) {
        let valInput = $('#lugarData').val();
        oTable.fnFilter(valInput);
    });



    $('#movilidadSelect').change(function () {
        let valor = $('#movilidadSelect').val();
        if (valor == 'saliente')
        {
            $('#movilidad').show();
        } else {
            $('#movilidad').hide();
        }
    });

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                // Asignamos el atributo src a la tag de imagen
                $('#previsualizaImagen').removeAttr("src");
                $('#previsualizaImagen').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    
    

    $('#fileImagen').change(function () {
        readURL(this);
    });
});