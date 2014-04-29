function cadastrarVeiculo() {
    if ($("#input_placa").val().trim() == "") {
        alertify.alert("A PLACA do veiculo nao foi digitada!", function() {
            $("#input_placa").focus();
        });
        return;
    }
    if ($("#input_placa").val().size() < 8) {
        alertify.alert("A PLACA do veiculo e invalida!", function() {
            $("#input_placa").attr("value", "");
            $("#input_placa").focus();
        });
        return;
    }
    if ($("#select_tipoVeiculo").val() == "nada") {
        alertify.alert("O TIPO DE VEICULO nao foi selecionado!");
        return;
    }
    if ($("#select_marcaVeiculo").val() == "nada") {
        alertify.alert("A MARCA do veiculo nao foi selecionada!");
        return;
    }
    if ($("#select_modeloVeiculo").val() == "nada") {
        alertify.alert("O MODELO do veiculo nao foi selecionado!");
        return;
    }
    $.ajax({
        url: "Controller?name=CadastrarVeiculo",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            placa: $("#input_placa").val(),
            tipoVeiculo: $("#select_tipoVeiculo option:selected").val(),
            marcaVeiculo: $("#select_marcaVeiculo option:selected").val(),
            modeloVeiculo: $("#select_modeloVeiculo option:selected").val()
        },
        dataType: "html",
        async: false,
        success: function(html) {
            $("html").html("");
            $("html").html(html);
            alertify.log("Cadastro realizado com sucesso!", success, 3000);
        }
    });
}