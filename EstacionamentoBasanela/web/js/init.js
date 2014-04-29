//Função responsável por chamar uma regra de negócio sem parametro
function enviar(regraNegocio) {
    $.ajax({
        url: "Controller?name=" + regraNegocio,
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "html",
        async: true,
        success: function(html) {
            $("body").html("");
            $("body").html(html);
        }
    });
}