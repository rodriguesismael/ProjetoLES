/*
 * Funcao responsavel por cadastrar veiculos avulsos e exibir a opcao de registrar entrada
 */
function cadastrarVeiculoAvulso() {
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
    if ($("#select_tipoVeiculo option:selected").val() == "nada") {
        alertify.alert("O TIPO DE VEICULO nao foi selecionado!");
        return;
    }
    if ($("#select_marcaVeiculo option:selected").val() == "nada") {
        alertify.alert("A MARCA do veiculo nao foi selecionada!");
        return;
    }
    if ($("#select_modeloVeiculo option:selected").val() == "nada") {
        alertify.alert("O MODELO do veiculo nao foi selecionado!");
        return;
    }
    block();
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
        dataType: "json",
        async: false,
        success: function(json) {
            alertufy.log("Veiculo cadastrado com sucesso!", success, 3000);
            alertify.confirm("Registrar entrada?", function(r) {
                if (r) {//Caso a resposta seja sim
                    block();
                    $.ajax({
                        url: "Controller?name=RegistrarEntrada",
                        type: "POST",
                        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                        data: {
                            placa: json.placa
                        },
                        dataType: "html",
                        async: false,
                        success: function(html) {
                            $(body).html("");
                            $(body).html(html);
                            alerify.log("Entrada cadastrada com sucesso!", success, 300);
                        }
                    });
                } else {//Caso a resposta seja nao
                    enviar("FormHome");
                }
            });
        }
    });
}

/*
 * Funcao responsavel por cadastrar veiculos mensais e exibir a opcao de registrar entrada
 */
function cadastrarVeiculoMensal() {
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
    if ($("#select_tipoVeiculo option:selected").val() == "nada") {
        alertify.alert("O TIPO DE VEICULO nao foi selecionado!");
        return;
    }
    if ($("#select_marcaVeiculo option:selected").val() == "nada") {
        alertify.alert("A MARCA do veiculo nao foi selecionada!");
        return;
    }
    if ($("#select_modeloVeiculo option:selected").val() == "nada") {
        alertify.alert("O MODELO do veiculo nao foi selecionado!");
        return;
    }
    if ($("#select_cliente option:selected").val() == "nada") {
        alertify.alert("O PROPRIETÃ�RIO do veiculo nao foi selecionado!");
        return;
    }
    block();
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
        dataType: "json",
        async: false,
        success: function(json) {
            block();
            $.ajax({
                url: "Controller?name=CadastrarClienteXVeiculo",
                type: "POST",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                data: {
                    placa: json.placa,
                    cpf: $("#select_cliente option:selected").val()
                },
                dataType: "json",
                async: false,
                success: function(json) {
                    alertufy.log("Veiculo cadastrado com sucesso!", success, 3000);
                    alertify.confirm("Registrar entrada?", function(r) {
                        if (r) {//Caso a resposta seja sim
                            block();
                            $.ajax({
                                url: "Controller?name=RegistrarEntrada",
                                type: "POST",
                                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                                data: {
                                    placa: json.placa
                                },
                                dataType: "html",
                                async: false,
                                success: function(html) {
                                    $(body).html("");
                                    $(body).html(html);
                                    alerify.log("Entrada cadastrada com sucesso!", success, 300);
                                }
                            });
                        } else {//Caso a resposta seja nao
                            enviar("FormHome");
                        }
                    });
                }
            });
        }
    });
}