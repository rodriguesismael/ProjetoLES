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
function efetuarLoginRIGHT() {
    if ($("#input_operador").val().trim() == "") {
        alert('Preencha o campo operador!', 'Atenção');
        $("#input_operador").focus();
    } else if ($("#input_senha").val().trim() == "") {
        alert('Preencha o campo senha!', 'Atenção!');
        $("#input_senha").focus();
    } else {
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
                if (json.status === true) {
                    $.ajax({
                        url: "Controller?name=FormHome",
                        type: "POST",
                        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                        dataType: "html",
                        async: false,
                        success: function(html) {
                            $("html").html("");
                            $("html").html(html);
                        }
                    });
                }
                else {
                    alert('Operador e/ou Senha incorretos!', 'Aviso');
                    $("#input_operador").attr("value", "");
                    $("#input_senha").attr("value", "");
                    $("#input_operador").focus();
                }
            }
        });
    }
}

function efetuarLogin() {
    enviar("FormHome");
}

//Efetuar logoff
function efetuarLogoff() {
    confirm('Deseja realmente sair do sistema?', 'Confirmação de Logoff', function(r) {
        if (r) {
            $.ajax({
                url: "Controller?name=EfetuarLogoff",
                type: "POST",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "html",
                async: false,
                success: function(html) {
                    $("html").html("");
                    $("html").html(html);
                }
            });
        }
    });
}
