function desvincularVeiculo(placa) {
    var numRows = 0;
    $("#listagemVeiculo tr").not("#cabecalho").each(function() {
        numRows++;
    });
    if (numRows == 1) {
        alertify.alert("Para desvincular um veículo é necessário possuir mais de um veículo vinculado ao cliente!");
        return;
    } else {
        alertify.confirm("Realmente deseja desvincular este veículo?", function(r) {
            if (r) {
                $.ajax({
                    url: "Controller?name=DesvincularVeiculo",
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    data: {
                        placa: placa
                    },
                    async: false,
                    success: function() {
                        $("tbody tr#" + placa).remove();
                        alertify.log("O veículo foi desvinculado com sucesso!", "success", 3000);
                    }
                });
            }
        });
    }
}

function vincularVeiculo() {
    if ($("#select_veiculo option:selected").val() == "nada") {
        alertify.alert("O VEÍCULO não foi selecionado!");
        return;
    }
    $.ajax({
        url: "Controller?name=VincularVeiculo",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            placa: $("#select_veiculo option:selected").val(),
            cpf: $("#input_cpf").val()
        },
        async: false,
        dataType: "json",
        success: function(json) {
            var veiculo = "";
            veiculo += "<tr id=\"" + json.placa + "\">";
            veiculo += "<td>" + json.placa + "</td>";
            if (json.tipo == 0) {
                veiculo += "<td>Carro</td>";
            } else {
                veiculo += "<td>Moto</td>";
            }
            veiculo += "<td>" + json.marca + "</td>";
            veiculo += "<td>" + json.modelo + "</td>";
            veiculo += "<td style=\"text-align: center;\"><a href=\"javascript:;\" onclick=\"desvincularVeiculo('" + json.placa + "')\"><span class=\"glyphicon glyphicon-trash\"></span></a></td>";
            veiculo += "</tr>";
            $("#select_veiculo option:selected").remove();
            $("#select_veiculo option:first-child").attr("selected", "selected");
            $("#listagemVeiculo tbody").append(veiculo);
            alertify.log("O veículo foi vinculado com sucesso!", "success", 3000);
        }
    });
}