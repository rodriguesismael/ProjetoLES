function registrarEntrada(placa) {
    $.ajax({
        url: "Controller?name=RegistrarEntrada",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            placa: placa
        },
        dataType: "html",
        async: false,
        success: function(html) {
            $("#pagina").html("");
            $("#pagina").html(html);
            alertify.log("Entrada registrada com sucesso!", "success", 5000);
        }
    });
}