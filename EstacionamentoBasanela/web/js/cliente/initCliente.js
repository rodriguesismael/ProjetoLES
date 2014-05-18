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

function modalNovoVeiculo(){
    var html="";
    html += "<div class=\"modal-dialog\">";
    html += "<div class=\"modal-content\">";
    html += "<div class=\"modal-header\">";
    html += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>";
    html += "<h4 class=\"modal-title\">Novo Veículo</h4>";
    html += "</div>";
    html += "<div class=\"modal-body\">";
    html += "<form action=\"javascript:;\">";
    html += "<div style=\"width: 450px;\">";
    html += "<div class=\"row\">";
    html += "<div clas=\"col-xs-6 form-group\">";
    html += "<label for=\"input_placa\">Placa</label>";
    html += "<input type=\"text\" class=\"form-control\" name=\"input_placa\" id=\"input_placa\"/>";
    html += "</div>";
    html += "<div clas=\"col-xs-6 form-group\">";
    html += "<label for=\"input_placa\">Placa</label>";
    html += "<input type=\"text\" class=\"form-control\" name=\"input_placa\" id=\"input_placa\"/>";
    html += "</div>";
    html += "<div clas=\"col-xs-6 form-group\">";
    html += "<label for=\"select_marca\">Marca</label>";
    html += "<select class=\"form-control\" id=\"select_marca\" name=\"select_marca\" onchange=\"buscarModelo()\">";
    html += "<option value=\"nada\"> <- selecione -> </option>";
    html += "<c:forEach var=\"marca\" items=\"${listaMarca}\">";
    html += "<option value=\"${marca.codMarca}\">${marca.descricao}</option>";
    html += "</c:forEach>";
    html += "</select>";
    html += "<div class=\"input-group-btn\">";
    html += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"glyphicon glyphicon-plus\"></span></button>";
    html += "<ul class=\"dropdown-menu pull-right\">";
    html += "<li><a href=\"modalCadMarca()\">Adicionar</a></li>";
    html += "<li><a href=\"modalAltMarca()\">Alterar</a></li>";
    html += "</ul>";
    html += "</div>";
    html += "</div>";
    html += "<div clas=\"col-xs-6 form-group\">";
    html += "<label for=\"select_modelo\">Modelo</label>";
    html += "<select class=\"form-control\" id=\"select_modelo\" name=\"select_modelo\" disabled>";
    html += "<option value=\"nada\"><-- selecione -></option>";
    html += "</select>";
    html += "<div class=\"input-group-btn\">";
    html += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"glyphicon glyphicon-plus\"></span></button>";
    html += "<ul class=\"dropdown-menu pull-right\">";
    html += "<li><a href=\"modalCadModelo()\">Inserir</a></li>";
    html += "<li><a href=\"modalAltModelo()\">Alterar</a></li>";
    html += "</ul>";
    html += "</div>";
    html += "</div>";
    html += "<div clas=\"col-xs-6 form-group\">";
    html += "<label for=\"select_tipo\">Tipo</label>";
    html += "<select class=\"form-control\" id=\"select_tipo\" name=\"select_tipo\">";
    html += "<option value=\"nada\"><- selecione -></option>";
    html += "<option value=\"0\">Carro</option>";
    html += "<option value=\"1\">Moto</option>";
    html += "</select>";
    html += "<div class=\"row\" align=\"center\">";
    html += "<div class=\"col-xs-12\">";
    html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"javascript:;\">Salvar</button>";
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
    $("#modal_veiculo").modal();
}