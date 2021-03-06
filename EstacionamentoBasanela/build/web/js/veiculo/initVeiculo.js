/*
 * Funcao responsavel por cadastrar veiculos avulsos e exibir a opcao de registrar entrada
 */
function cadastrarVeiculoAvulso() {
    if ($("#select_tipo option:selected").val() == "nada") {
        alertify.alert("O TIPO DE VEÍCULO não foi selecionado!");
        return;
    }
    if ($("#select_marca option:selected").val() == "nada") {
        alertify.alert("A MARCA do veículo nao foi selecionada!");
        return;
    }
    if ($("#select_modelo option:selected").val() == "nada") {
        alertify.alert("O MODELO do veículo não foi selecionado!");
        return;
    }
    $.ajax({
        url: "Controller?name=CadastrarVeiculo",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            placa: $("#input_placa").val(),
            tipoVeiculo: $("#select_tipo option:selected").val(),
            marcaVeiculo: $("#select_marca option:selected").val(),
            modeloVeiculo: $("#select_modelo option:selected").val()
        },
        dataType: "json",
        async: false,
        success: function(json) {
            alertify.log("Veículo cadastrado com sucesso!", "success", 5000);
            alertify.confirm("Registrar entrada?", function(r) {
                if (r) {//Caso a resposta seja sim
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
                            $("#pagina").html("");
                            $("#pagina").html(html);
                            alertify.log("Entrada registrada com sucesso!", "success", 5000);
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
    if ($("#select_tipo option:selected").val() == "nada") {
        alertify.alert("O TIPO DE VEÍCULO não foi selecionado!");
        return;
    }
    if ($("#select_marca option:selected").val() == "nada") {
        alertify.alert("A MARCA do veículo não foi selecionada!");
        return;
    }
    if ($("#select_modelo option:selected").val() == "nada") {
        alertify.alert("O MODELO do veículo não foi selecionado!");
        return;
    }
    if ($("#select_cliente option:selected").val() == "nada") {
        alertify.alert("O PROPRIETARIO do veículo não foi selecionado!");
        return;
    }
    $.ajax({
        url: "Controller?name=CadastrarVeiculo",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            placa: $("#input_placa").val(),
            tipoVeiculo: $("#select_tipo option:selected").val(),
            marcaVeiculo: $("#select_marca option:selected").val(),
            modeloVeiculo: $("#select_modelo option:selected").val()
        },
        dataType: "json",
        async: false,
        success: function(json) {
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
                    alertify.log("Veículo cadastrado com sucesso!", "success", 5000);
                    alertify.confirm("Registrar entrada?", function(r) {
                        if (r) {//Caso a resposta seja sim
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
                                    $("#pagina").html("");
                                    $("#pagina").html(html);
                                    alerify.log("Entrada cadastrada com sucesso!", "success", 5000);
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

function alterarVeiculo() {
    if ($("#select_tipo option:selected").val() == "nada") {
        alertify.alert("O campo TIPO nao foi selecionado!");
        return;
    }
    if ($("#select_marca option:selected").val() == "nada") {
        alertify.alert("O campo MARCA nao foi selecionado!");
        return;
    }
    if ($("#select_modelo option:selected").val() == "nada") {
        alertify.alert("O campo MODELO nao foi selecionado!");
        return;
    }
    $.ajax({
        url: "Controller?name=AlterarVeiculo",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            placa: $("#input_placa").val(),
            tipoVeiculo: $("#select_tipo option:selected").val(),
            marcaVeiculo: $("#select_marca option:selected").val(),
            modeloVeiculo: $("#select_modelo option:selected").val()
        },
        dataType: "html",
        async: false,
        success: function(html) {
            $("#pagina").html("");
            $("#pagina").html(html);
            alertify.log("Veiculo alterado com sucesso!", "success", 5000);
        }
    });
}