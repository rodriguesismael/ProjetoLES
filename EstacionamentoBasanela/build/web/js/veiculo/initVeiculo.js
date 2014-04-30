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

function buscarVeiculo() {
    if ($("#input_placa").val().trim() == "") {
        alertify.alert("A PLACA nao foi digitada!", function() {
            $("#input_placa").focus();
        });
        return;
    }
    if ($("#input_placa").val().size() < 8) {
        alertify.alert("Placa invalida!", function() {
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
            var html = "";
            html += "<div class=\"modal-dialog\">";
            html += "<div class=\"modal-content\">";
            html += "<div class=\"modal-header\">";
            html += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>";
            if (json.existeVeiculo) {
                html += "<h4 class=\"modal-title\">Veiculo Cadastrado</h4>";
                html += "</div>";
                html += "<div class=\"modal-body\">";
                html += "conteudo";
                html += "</div>";
                html += "<div class=\"modal-footer\">";
                html += "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>";
                if (json.emMovimento) {
                    html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"registrarSaida()\">Registrar Saida</button>";
                } else {
                    html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"registrarEntrada()\">Registrar Entrada</button>";
                }
                html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"datalharVeiculo()\">Detalhes</button>";
                html += "</div>";
            } else {
                html += "<h4 class=\"modal-title\">Veiculo Nao Cadastrado</h4>";
            }
            $("#modal_veiculo").html("");
            $("#modal_veiculo").html(html);
            $("#modal_veiculo").modal();
        }
    });
}