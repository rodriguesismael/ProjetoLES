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
    block();
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
                html += "<p>One fine body&hellip;</p>";
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

}