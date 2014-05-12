function cadastrarCliente() {
    /*inicio validação campos do cliente*/
    if ($("#input_nome").val().trim(" ") == "") {
        alertify.alert("O nome não foi preenchido", function() {
            $("#input_nome").focus();
        });
        return;
    }

    if ($("#select_estado option:selected").val() == "nada") {
        alertify.alert("Selecione um estado!", function() {
            $("#select_estado").focus();
        });
        return;
    }

    if ($("#select_cidade option:selected").val() == "nada") {
        alertify.alert("Selecione uma cidade!", function() {
            $("#select_cidade").focus();
        });
        return;
    }

    if ($("#input_endereco").val().trim(" ") == "") {
        alertify.alert("Informe o endereço!", function() {
            $("#input_endereco").focus();
        });
        return;
    }

    if ($("#select_periodo option:selected").val() == "nada") {
        alertify.alert("Selecione um periodo!", function() {
            $("#select_periodo").focus();
        });
        return;
    }

    if ($("#input_telefone").val().trim(" ") == "") {
        alertify.alert("Informe o telefone de contato!", function() {
            $("#input_telefone").focus();
        });
        return;
    }
    /*fim validação campos do cliente*/

    /*validação campos do veiculo do cliente*/
    if ($("#input_placa").val().trim(" ") == "") {
        alertify.alert("Informe a placa do veículo!", function() {
            $("#input_placa").focus();
        });
        return;
    }

    if ($("#select_tipo option:selected").val() == "nada") {
        alertify.alert("O TIPO DE VEICULO nao foi selecionado!");
        return;
    }
    if ($("#select_marca option:selected").val() == "nada") {
        alertify.alert("A MARCA do veiculo nao foi selecionada!");
        return;
    }
    if ($("#select_modelo option:selected").val() == "nada") {
        alertify.alert("O MODELO do veiculo nao foi selecionado!");
        return;
    }
    /*fim validação campos do veiculo do cliente*/

    $.ajax({
        url: "Controller?name=CadastrarCliente",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            cpf: $("#input_cpf").val(),
            nome: $("#input_nome").val(),
            estado: $("#select_estado option:selected").val(),
            cidade: $("#select_cidade option:selected").val(),
            endereco: $("#input_endereco").val(),
            telefone: $("#input_telefone").val(),
            celular: $("#input_celular").val(),
            periodo: $("#select_periodo option:selected").val(),
            placa: $("#input_placa").val(),
            marcaVeiculo: $("#select_marca option:selected").val(),
            modeloVeiculo: $("#select_modelo option:selected").val(),
            tipoVeiculo: $("#select_tipo option:selected").val()
        },
        dataType: "json",
        async: false,
        success: function(json) {
            alertify.log("Cliente cadastrado com sucesso", "sucess", 5000);
        }

    });
}