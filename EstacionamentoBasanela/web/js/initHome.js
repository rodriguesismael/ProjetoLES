/*
 * Funcao responsavel por buscar o veiculo mediante a sua placa e montar
 * o modal_veiculo conforme os resultados da funcao.
 */
function buscarVeiculo() {
    if ($("#input_veiculo").val().trim() == "") {
        alertify.alert("A PLACA não foi informada!", function() {
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
                if (json.veiculo[0].tipo == 0) {
                    html += "<p><b>Tipo de Veículo: </b>Carro</p>";
                } else {
                    html += "<p><b>Tipo de Veículo: </b>Moto</p>";
                }
                html += "<p><b>Marca: </b>" + json.veiculo[0].marca + "</p>";
                html += "<p><b>Modelo: </b>" + json.veiculo[0].modelo + "</p>";
                if (json.faturasAbertas == true) {
                    html += "<p id=\"alerta\"><b>Existem faturas à serem pagas!</b></p>";
                }
                html += "</div>";
                html += "<div class=\"modal-footer\">";
                if (json.emMovimento) { //Se o veiculo estiver em um movimento nao encerrado, exibir botao registrar saida
                    html += "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"registrarSaida('" + json.veiculo[0].placa + "')\">Registrar Saida</button>";
                } else { //Se o veiculo nao estiver em um movimento, exibir botao registrar entrada
                    html += "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"registrarEntrada('" + json.veiculo[0].placa + "')\">Registrar Entrada</button>";
                }
                html += "</div>";
                html += "</div>";
                html += "</div>";
            } else { //Criacao do modal_veiculo caso o veiculo nao exista
                html += "<h4 class=\"modal-title\">Veiculo Não Cadastrado</h4>";
                html += "</div>";
                html += "<div class=\"modal-body\">";
                html += "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"enviarParametro('FormCadVeiculoAvulso', '" + $("#input_veiculo").val() + "')\">Cadastrar Veiculo Avulso</button>";
                html += "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"enviarParametro('FormCadVeiculoMensal', '" + $("#input_veiculo").val() + "')\">Cadastrar Veiculo Mensal</button>";
                html += "</div>";
                html += "</div>";
            }
            $("#modal_veiculo").html("");
            $("#modal_veiculo").html(html);
            $("#modal_veiculo").modal();
        }
    });
}

function cpfValido() {
    var Soma;
    var Resto;
    Soma = 0;
    var strCPF = $("#input_cliente").val();

    strCPF = strCPF.replace(/[^0-9]/g, "");//pega apenas os numeros
    if (strCPF == "00000000000")
        return false;
    if (strCPF == "11111111111")
        return false;
    if (strCPF == "22222222222")
        return false;    
    if (strCPF == "33333333333")
        return false;
    if (strCPF == "44444444444")
        return false;
    if (strCPF == "55555555555")
        return false;
    if (strCPF == "66666666666")
        return false;
    if (strCPF == "77777777777")
        return false;
    if (strCPF == "88888888888")
        return false;
    if (strCPF == "99999999999")
        return false;    
    for (i = 1; i <= 9; i++)
        Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;
    if ((Resto == 10) || (Resto == 11))
        Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10)))
        return false;
    Soma = 0;
    for (i = 1; i <= 10; i++)
        Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;
    if ((Resto == 10) || (Resto == 11))
        Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11)))
        return false;
    return true;


}

/*
 * Funcao responsavel por buscar o cliente mediante a seu numero de CPF e
 * montar o modal_cliente conforme o resultado da funcao.
 */
function buscarCliente() {
    if ($("#input_cliente").val().trim() == "") {
        alertify.alert("O CPF do cliente não foi preenchido", function() {
            $("#input_cliente").focus();
        });
        return;
    }
    if (!cpfValido()) {
        alertify.alert("O CPF do cliente não é válido", function() {
            $("#input_cliente").focus();
        });
        return;
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
            var html = "";
            html += "<div class=\"modal-dialog\">";
            html += "<div class=\"modal-content\">";
            html += "<div class=\"modal-header\">";
            html += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>";
            //Criacao do modal_cliente caso o veiculo exista

            if (json.existeCliente) {
                html += "<h4 class=\"modal-title\">Cliente Cadastrado</h4>";
                html += "</div>";
                html += "<div class=\"modal-body\">";
                html += "<p><b>CPF: </b>" + json.cliente[0].cpf + "</p>";
                html += "<p><b>Nome: </b>" + json.cliente[0].nome + "</p>";
                if (json.cliente[0].periodo == 1) {
                    html += "<p><b>Periodo: </b>Manhã</p>";
                } else
                if (json.cliente[0].periodo == 2) {
                    html += "<p><b>Periodo: </b>Tarde</p>";
                } else {
                    html += "<p><b>Periodo: </b>Noite</p>";

                }
                html += "</div>";
                html += "<div class=\"modal-footer\">";
                if (json.emMovimento) { //Se o veiculo estiver em um movimento nao encerrado, exibir botao registrar saida
                    html += "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\">Detalhar</button>";
                } else { //Se o veiculo nao estiver em um movimento, exibir botao registrar entrada
                    html += "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"enviarParametro('FormAltCliente', '" + json.cliente[0].cpf + "')\">Alterar</button>";
                }
                html += "</div>";
                html += "</div>";
                html += "</div>";
            } else { //Criacao do modal_veiculo caso o veiculo nao exista
                html += "<h4 class=\"modal-title\">Cliente Não Cadastrado</h4>";
                html += "</div>";
                html += "<div class=\"modal-body\">";
                html += "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"enviarParametro('FormCadCliente', '" + $("#input_cliente").val() + "')\">Cadastrar Cliente</button>";
                html += "</div>";
                html += "</div>";
            }
            $("#modal_cliente").html("");
            $("#modal_cliente").html(html);
            $("#modal_cliente").modal();
        }
    });
}

function sobre() {
    var html = "";
    html += "<div class=\"modal-dialog\">";
    html += "<div class=\"modal-content\">";
    html += "<div class=\"modal-header\">";
    html += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>";
    html += "<h4 class=\"modal-title\">Sobre...</h4>";
    html += "</div>";
    html += "<div class=\"modal-body\">";
    html += "<p>Projeto desenvolvido para a disciplina de Laboratório de Engenharia de Software do curso de ADS da FATEC - AM.</p>";
    html += "<p>Equipe:</p>";
    html += "<p><img src=\"img/operador_mini.png\">Álvaro Augusto Roberto</p>";
    html += "<p><img src=\"img/operador_mini.png\">Guilherme Adão</p>";
    html += "<p><img src=\"img/operador_mini.png\">Ismael Rodrigues Martins</p>";
    html += "<p><img src=\"img/operador_mini.png\">Márcia Leite Silva</p>";
    html += "<p><img src=\"img/operador_mini.png\">Rafael Juzo Oda</p>";
    html += "<p><img src=\"img/operador_mini.png\">Wang Yu Tzu</p>";
    html += "</div>";
    html += "</div>";
    html += "</div>";
    html += "</div>";
    $("#modal_sobre").html("");
    $("#modal_sobre").html(html);
    $("#modal_sobre").modal();
}