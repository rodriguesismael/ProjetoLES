function modalModelo(tipoOperacao) {
    if ($("#select_marca option:selected").val() == "nada" && tipoOperacao == "cad") {
        alertify.alert("Selecione uma marca para cadastrar um modelo!");
        return;
    }
    if ($("#select_modelo option:selected").val() == "nada" && tipoOperacao == "alt") {
        alertify.alert("Selecione um modelo para que possa alterar suas informações!");
        return;
    }
    var html = "";
    html += "<div class=\"modal-dialog\">";
    html += "<div class=\"modal-content\">";
    html += "<div class=\"modal-header\">";
    html += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>";
    if (tipoOperacao == "cad") {
        html += "<h4 class=\"modal-title\">Cadastrar Modelo</h4>";
        html += "</div>";
    } else {
        html += "<h4 class=\"modal-title\">Alterar Modelo</h4>";
        html += "</div>";
    }
    html += "<div class=\"modal-body\">";
    html += "<div class=\"form-group\">";
    html += "<label for=\"input_modelo\">Descrição</label>";
    if (tipoOperacao == "cad") {
        html += "<input type=\"text\" class=\"form-control\" id=\"input_modelo\"/>";
    } else {
        html += "<input type=\"text\" class=\"form-control\" id=\"input_modelo\" value=\"" + $("#select_modelo option:selected").text() + "\"/>";
    }
    html += "</div>";
    html += "</div>";
    html += "<div class=\"modal-footer\">";
    if (tipoOperacao == "cad") {
        html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"cadastrarModelo()\">Salvar</button>";
    } else {
        html += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"alterarModelo(" + $("#select_modelo option:selected").val() + ")\">Alterar</button>";
    }
    html += "</div>";
    $("#modal_modelo").html("");
    $("#modal_modelo").html(html);
    $("#modal_modelo").modal();
}

function cadastrarModelo() {
    if ($("#input_modelo").val().trim() == "") {
        alertify.alert("O campo não foi preenchido!", function() {
            $("#input_modelo").focus();
        });
        return;
    }
    $.ajax({
        url: "Controller?name=CadastrarModelo",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            codMarca: $("#select_marca option:selected").val(),
            descricao: $("#input_modelo").val()
        },
        dataType: "json",
        async: false,
        success: function(json) {
            var option = "<option value=" + json.codModelo + ">" + json.descricao + "</option>";
            $("#select_modelo").append(option);
            $("#select_modelo option:last").attr("selected", "selected");
            $("#modal_modelo").modal("hide");
        }
    });
}

function alterarModelo(codModelo) {
    if ($("#input_modelo").val().trim() == "") {
        alertify.alert("O campo não foi preenchido!", function() {
            $("#input_modelo").focus();
        });
        return;
    }
    var descricao = $("#input_modelo").val();
    $.ajax({
        url: "Controller?name=AlterarModelo",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            codModelo: codModelo,
            descricao: descricao
        },
        async: false,
        success: function() {
            $("#select_modelo option").each(function() {
                if ($(this).val() == codModelo) {
                    $(this).text(descricao);
                }
            });
            $("#modal_modelo").modal("hide");
        }
    });
}

function buscarModelo() {
    if ($("#select_marca option:selected").val() != "nada") {
        $.ajax({
            url: "Controller?name=BuscarModelo",
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            data: {
                codMarca: $("#select_marca option:selected").val()
            },
            dataType: "json",
            async: true,
            success: function(json) {
                $("#select_modelo option").remove();
                var html = "";
                html += "<option value=\"nada\"><-- selecione --></option>";
                for (var i = 0; i < json.listaModelo.length; i++) {
                    if (json.listaModelo.length == 1) {
                        html += "<option value=\"" + json.listaModelo[i].codModelo + "\" selected>" + json.listaModelo[i].descricao + "</option>";
                    } else {
                        html += "<option value=\"" + json.listaModelo[i].codModelo + "\">" + json.listaModelo[i].descricao + "</option>";
                    }
                }
                $("#select_modelo").html(html);
                $("#select_modelo").attr("disabled", false);
            }
        });
    } else {
        var html = "";
        html += "<option value=\"nada\"><-- selecione --></option>";
        $("#select_modelo").html(html);
        $("#select_modelo").attr("disabled", true);
    }
}