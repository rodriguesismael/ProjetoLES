//Função responsável por chamar uma regra de negócio sem parametro
function enviar(regraNegocio) {
    $.ajax({
        url: "Controller?name=" + regraNegocio,
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "html",
        async: true,
        beforeSend: function() {
            $("body").removeClass("modal-open");
            $(".modal-backdrop").remove();
        },
        success: function(html) {
            $("#pagina").html("");
            $("#pagina").html(html);
        }
    });
}

function enviarParametro(regraNegocio, parametro) {
    $.ajax({
        url: "Controller?name=" + regraNegocio,
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "html",
        beforeSend: function() {
            $("body").removeClass("modal-open");
            $(".modal-backdrop").remove();
        },
        data: {
            id: parametro
        },
        async: true,
        success: function(html) {
            $("#pagina").html("");
            $("#pagina").html(html);
        }
    });
}