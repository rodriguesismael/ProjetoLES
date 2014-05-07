function buscarVeiculo() {
    if ($("#input_placa").val().trim() == "") {
        alertify.alert("O campo nÃ£o foi preenchido!", function() {
            $("#input_placa").focus();
        });
        return;
    }
    if ($("#input_placa").val().length() < 8) {
        alertify.alert("A placa digitada Ã© invÃ¡lida!", function() {
            $("#input_placa").focus();
        });
        return;
    }
    $.ajax({
        url: "Controller?name=BuscarVeiculo",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            placa: $("#input_placa").val()
        },
        dataType: "json",
        async: false,
        success: function(json) {

        }
    });
}