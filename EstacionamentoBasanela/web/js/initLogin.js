//Acoes a serem executadas apos carregar pagina
$(document).ready(function() {
    $("#input_operador").focus();
    $("#input_operador").keypress(function(e) {
        var tecla = (ee.keyCode() ? e.keyCode() : e.which());
        if (tecla === 13) {
            efetuarLogin();
        }
    });
    $("#input_senha").keypress(function(e) {
        var tecla = (ee.keyCode() ? e.keyCode() : e.which());
        if (tecla === 13) {
            efetuarLogin();
        }
    });
});

//Efetuar login
function efetuarLogin() {
    if ($("#input_operador").val().trim() == "") {
        alertify.alert("O campo OPERADOR nao foi preenchido!", function() {
            $("#input_operador").focus();
        });
        return;
    }
    if ($("#input_senha").val().trim() == "") {
        alertify.alert("O campo SENHA nao foi preenchido!", function() {
            $("#input_senha").focus();
        });
        return;
    }
    $.ajax({
        url: "Controller?name=EfetuarLogin",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            login: $("#input_operador").val(),
            senha: $("#input_senha").val()
        },
        dataType: "json",
        async: false,
        success: function(json) {
            if (json.status == true) {
                enviar("FormHome");
            }
            else {
                alertify.alert("Operador e/ou senha incorretos!", function() {
                    $("#input_operador").val("");
                    $("#input_senha").val("");
                    $("#input_operador").focus();
                    return;
                });
            }
        }
    });
}

//Efetuar logoff
function efetuarLogoff() {
    alertify.confirm("Deseja realmente sair do sistema?", function(r) {
        if (r) {
            $.ajax({
                url: "Controller?name=EfetuarLogoff",
                type: "POST",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "html",
                async: false,
                success: function(html) {
                    $("#pagina").html("");
                    $("#pagina").html(html);
                }
            });
        }
    });
}
