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

    if ($("#select_veiculo option:selected").val() == "nada") {
        alertify.alert("Selecione um periodo!", function() {
            $("#select_periodo").focus();
        });
        return;
    }

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
            placaVeiculo: $("#select_veiculo option:selected").val()
        },
        dataType: "json",
        async: false,
        success: function(json) {
            alertify.log("Cliente cadastrado com sucesso", "sucess", 5000);
        }

    });
    enviar("FormHome");
}

function carregarMarcas() {
    $.ajax({
        url: "Controller?name=CarregarMarcas",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            codEstado: $("#select_estado").val()
        },
        async: false,
        success: function(json) {
            $("#select_marca option").remove();
            var html = "";
            for (var i = 0; i < json.listaMarca.length; i++) {
                if (json.listaMarca.length == 1) {
                    html += "<option value=\"" + json.listaMarca[i].codMarca + "\" selected>" + json.listaMarca[i].descricao + "</option>";
                } else {
                    html += "<option value=\"" + json.listaMarca[i].codMarca + "\">" + json.listaMarca[i].descricao + "</option>";
                }
            }
            $("#select_marca").html(html);
        }
    });
}

function modalVeiculo(tipoOperacao) {
    var html = "";
    html += "<div class=\"modal-dialog\">";
    html += "<div class=\"modal-content\">";
    html += "<div class=\"modal-header\">";
    html += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>";
    if (tipoOperacao == 'cad') {
        html += "<h4 class=\"modal-title\">Novo Veículo</h4>";
    } else {
        html += "<h4 class=\"modal-title\">Alterar Veículo</h4>";
    }
    html += "</div>";
    html += "<div class=\"modal-body\">";
    html += "<form action=\"javascript:;\">";
    html += "<div style=\"width: 450px;\">";
    html += "<div class=\"row\">";
    html += "<div class=\"col-xs-6 form-group\">";
    html += "<label for=\"input_placa\">Placa</label>";
    if (tipoOperacao == 'cad') {
        html += "<input type=\"text\" class=\"form-control\" name=\"input_placa\" id=\"input_placa\"/>";
    }
    else {
        html += "<input type=\"text\" class=\"form-control\" name=\"input_placa\" id=\"input_placa\" value=\"" + $("#select_veiculo option:selected").text().split("/")[0] + "\"/>";
    }
    html += "</div>";
    html += "</div>";
    html += "<div class=\"row\">";
    html += "<div class=\"col-xs-8 form-group\">";
    html += "<label for=\"select_marca\">Marca</label>";
    html += "<select class=\"form-control\" id=\"select_marca\" name=\"select_marca\" onchange=\"buscarModelo()\">";
    html += "<option value=\"nada\"> <- selecione -> </option>";
    html += "</select>";
    html += "</div>";
    html += "</div>";
    html += "<div class=\"row\">";
    html += "<div class=\"col-xs-8 form-group\">";
    html += "<label for=\"select_modelo\">Modelo</label>";
    html += "<select class=\"form-control\" id=\"select_modelo\" name=\"select_modelo\" disabled>";
    html += "<option value=\"nada\"><-- selecione -></option>";
    html += "</select>";
    html += "</div>";
    html += "</div>";
    html += "<div class=\"row\">";
    html += "<div class=\"col-xs-6 form-group\">";
    html += "<label for=\"select_tipo\">Tipo</label>";
    html += "<select class=\"form-control\" id=\"select_tipo\" name=\"select_tipo\">";
    html += "<option value=\"nada\"><- selecione -></option>";
    html += "<option value=\"0\">Carro</option>";
    html += "<option value=\"1\">Moto</option>";
    html += "</select>";
    html += "</div>";
    html += "</div>";
    html += "<div class=\"row\" align=\"center\">";
    html += "<div class=\"col-xs-1\">";
    html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"cadastrarVeiculo()\">Salvar</button>";
    html += "</div>";
    html += "</div>";
    html += "</div>";
    html += "</form>";
    html += "</div>";
    html += "</div>";
    html += "</div>";
    html += "</div>";
    $("#modal_veiculo").html("");
    $("#modal_veiculo").html(html);
    $("#input_placa").mask("aaa-9999");
    carregarMarcas();
    buscarModelo();
    $("#modal_veiculo").modal();
}

function cadastrarVeiculo() {
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
            alertify.log("Veiculo cadastrado com sucesso!", "success", 5000);
            var html = "<option value=\"" + json.plavaVeiculo + "\" selected>" + json.placa + "/" + json.modelo + "</option>";
            $("#select_veiculo").append(html);
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
                            alertify.log("Entrada registrada com sucesso!", "success", 5000);
                        }
                    });
                }
            });
            $("#modal_veiculo").modal('hide');
        }
    });
}

function alterarCliente() {
    if ($("#input_nome").val().trim() == "") {
        alertify.alert("O campo NOME nao foi preenchido!", function() {
            $("#input_nome").focus();
        });
        return;
    }
    if ($("#input_telefone").val().trim() == "") {
        alertify.alert("O campo TELEFONE nao foi preenchido", function() {
            $("#input_telefone").focus();
        });
        return;
    }
    if ($("#select_estado option:selected").val() == "nada") {
        alertify.alert("O campo ESTADO nao foi selecionado!");
        return;
    }
    if ($("#input_endereco").val().trim() == "") {
        alertify.alert("O campo ENDERECO nao foi preenchido!", function() {
            $("#input_endereco").focus();
        });
        return;
    }
    if ($("#select_periodo option:selected").val() == "nada") {
        alertify.alert("O campo PERIODO nao foi selecionado!");
        return;
    }
    $.ajax({
        url: "Controller?name=AlterarCliente",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            cpf: $("#input_cpf").val(),
            nome: $("#input_nome").val(),
            telefone: $("#input_telefone").val(),
            celular: $("#input_celular").val(),
            codEstado: $("#select_estado option:selected").val(),
            codCidade: $("#select_cidade option:selected").val(),
            endereco: $("#input_endereco").val(),
            codPeriodo: $("#select_periodo option:selected").val()
        },
        dataType: "html",
        async: false,
        success: function(html) {
            $("#pagina").html("");
            $("#pagina").html(html);
            alertify.log("Alteracao realizada com sucesso!", "success", 5000);
        }
    });
}

$(document).ready(function() {
    //$.mask.definitions['~'] = '([0-9] )?';
    $("#input_telefone").mask("(99) 99999999");
    $("#input_celular").mask("(99) 999999999");
})