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