//Função responsável por chamar uma regra de negócio sem parametro
function enviar(regraNegocio) {
    block();
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

function enviarParametro(regraNegocio, parametro) {
    block();
    $.ajax({
        url: "Controller?name=" + regraNegocio,
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "html",
        data: {
            id: parametro
        },
        async: true,
        success: function(html) {
            $("body").html("");
            $("body").html(html);
        }
    });
}

function block() {
    $(document).ajaxStart(function() {
        $.blockUI({
            message: $("#barra-progresso"),
            css: {
                border: "none",
                backgroundColor: "transparent"
            }
        });
    }).ajaxStop(function() {
        $.unblockUI();
    });
}