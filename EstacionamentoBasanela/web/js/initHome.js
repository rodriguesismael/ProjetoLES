/*
 * Funcao responsavel por buscar o veiculo mediante a sua placa e montar
 * o modal_veiculo conforme os resultados da funcao.
 */
function buscarVeiculo() {
    if ($("#input_veiculo").val().trim() == "") {
        alertify.alert("O campo nao foi preenchido!", function() {
            $("#input_veiculo").focus();
        });
        return;
    }
    $.ajax({
        url: "Controller?name=BuscarVeiculo",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            placa: $("#input_veiculo").val()
        },
        dataType: "json",
        async: false,
        success: function(json) {
            var html = "";
            html += "<div class=\"modal-dialog\">";
            html += "<div class=\"modal-content\">";
            html += "<div class=\"modal-header\">";
            html += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>";
            //Criacao do modal_veiculo caso o veiculo exista
            if (json.existeVeiculo) {
                html += "<h4 class=\"modal-title\">Veiculo Cadastrado</h4>";
                html += "</div>";
                html += "<div class=\"modal-body\">";
                html += "<p><b>Placa: </b>" + json.veiculo[0].placa + "</p>";
                if (json.veiculo[0].tipo == 1) {
                    html += "<p><b>Tipo de Veiculo: </b>Carro</p>";
                } else {
                    html += "<p><b>Tipo de Veiculo: </b>Moto</p>";
                }
                html += "<p><b>Marca: </b>" + json.veiculo[0].marca + "</p>";
                html += "<p><b>Modelo: </b>" + json.veiculo[0].modelo + "</p>";
                html += "</div>";
                html += "<div class=\"modal-footer\">";
                if (json.emMovimento) { //Se o veiculo estiver em um movimento nao encerrado, exibir botao registrar saida
                    html += "<button type=\"button\" class=\"btn btn-primary\">Registrar Saida</button>";
                } else { //Se o veiculo nao estiver em um movimento, exibir botao registrar entrada
                    html += "<button type=\"button\" class=\"btn btn-primary\">Registrar Entrada</button>";
                }
                html += "</div>";
                html += "</div>";
                html += "</div>";
            } else { //Criacao do modal_veiculo caso o veiculo nao exista
                html += "<h4 class=\"modal-title\">Veiculo Nao Cadastrado</h4>";
                html += "</div>";
                html += "<div class=\"modal-body\">";
                html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"enviarParametro('FormCadVeiculoAvulso', '" + $("#input_veiculo").val() + "')\">Cadastrar Veiculo Avulso</button>";
                html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"enviarParametro('FormCadVeiculoMensal', '" + $("#input_veiculo").val() + "')\">Cadastrar Veiculo Mensal</button>";
                html += "</div>";
                html += "</div>";
            }
            $("#modal_veiculo").html("");
            $("#modal_veiculo").html(html);
            $("#modal_veiculo").modal();
        }
    });
}

/*
 * Funcao responsavel por buscar o cliente mediante a seu numero de CPF e
 * montar o modal_cliente conforme o resultado da funcao.
 */
function buscarCliente() {
    if ($("#input_cliente").val().trim("") == "") {
        alertify.alert("O CPF do cliente não foi preenchido", function() {
            $("#input_cliente").focus();
        });
    }
    $.ajax({
        url: "Controller?name=BuscarCliente",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            cpf: $("#input_cliente").val()
        },
        async: false,
        success: function(json) {

        }
    });
}