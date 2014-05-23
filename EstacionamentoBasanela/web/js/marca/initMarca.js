function modalMarca(tipoOperacao) {
    if ($("#select_marca option:selected").val() == "nada" && tipoOperacao == "alt") {
        alertify.alert("Selecione uma marca para que possa alterar suas informações!");
        return;
    }
    var html = "";
    html += "<div class=\"modal-dialog\">";
    html += "<div class=\"modal-content\">";
    html += "<div class=\"modal-header\">";
    html += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>";
    if (tipoOperacao == "cad") {
        html += "<h4 class=\"modal-title\">Cadastrar Marca</h4>";
        html += "</div>";
    } else {
        html += "<h4 class=\"modal-title\">Alterar Marca</h4>";
        html += "</div>";
    }
    html += "<div class=\"modal-body\">";
    html += "<div class=\"form-group\">";
    html += "<label for=\"input_marca\">Descrição</label>";
    if (tipoOperacao == "cad") {
        html += "<input type=\"text\" class=\"form-control\" id=\"input_marca\"/>";
    } else {
        html += "<input type=\"text\" class=\"form-control\" id=\"input_marca\" value=\"" + $("#select_marca option:selected").text() + "\"/>";
    }
    html += "</div>";
    html += "</div>";
    html += "<div class=\"modal-footer\">";
    if (tipoOperacao == "cad") {
        html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"cadastrarMarca()\">Salvar</button>";
    } else {
        html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"alterarMarca(" + $("#select_marca option:selected").val() + ")\">Alterar</button>";
    }
    html += "</div>";
    $("#modal_marca").html("");
    $("#modal_marca").html(html);
    $("#modal_marca").modal();
}

function cadastrarMarca() {
    if ($("#input_marca").val().trim() == "") {
        alertify.alert("O campo não foi preenchido!", function() {
            $("#input_marca").focus();
        });
        return;
    }
    $.ajax({
        url: "Controller?name=CadastrarMarca",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            descricao: $("#input_marca").val()
        },
        dataType: "json",
        async: false,
        success: function(json) {
            var option = "<option value=" + json.codMarca + ">" + json.descricao + "</option>";
            $("#select_marca").append(option);
            $("#select_marca option:last").attr("selected", "selected");
            $("#modal_marca").modal("hide");
            buscarModelo();
        }
    });
}

function alterarMarca(codMarca) {
    if ($("#input_marca").val().trim() == "") {
        alertify.alert("O campo não foi preenchido!", function() {
            $("#input_marca").focus();
        });
        return;
    }
    var descricao = $("#input_marca").val();
    $.ajax({
        url: "Controller?name=AlterarMarca",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            codMarca: codMarca,
            descricao: descricao
        },
        async: false,
        success: function() {
            $("#select_marca option").each(function() {
                if ($(this).val() == codMarca) {
                    $(this).text(descricao);
                }
            });
            $("#modal_marca").modal("hide");
            buscarModelo();
        }
    });
}