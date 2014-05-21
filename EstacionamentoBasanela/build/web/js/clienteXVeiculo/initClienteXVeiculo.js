function desvincularVeiculo(placa) {
    var numRows = 0;
    $("#listagemVeiculo tr").not("#cabecalho").each(function() {
        numRows++;
    });
    if (numRows == 1) {
        alertify.alert("Para desvincular um veículo é necessário possuir mais de um veículo vinculado ao cliente!");
        return;
    } else {
        alertify.confirm("Realmente deseja desvincular este veiculo?", function(r) {
            if (r) {
                $("tbody tr#" + placa).remove();// FUNCIONA!!!
            }
        });
    }
}