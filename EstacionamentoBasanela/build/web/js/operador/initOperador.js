function cadastrarOperador() {
    if ($("#input_nome").val().trim() == "") {
        alertify.alert("O campo NOME não foi preenchido!", function() {
            $("#input_nome").focus();
        });
        return;
    }
    if ($("#select_administrador option:selected").val() == "nada") {
        alertify.alert("O campo ADMINISTRADOR não foi selecionado!");
        return;
    }
    if ($("#input_login").val().trim() == "") {
        alertify.alert("O campo LOGIN não foi preenchido!", function() {
            $("#input_login").focus();
        });
        return;
    }
    if ($("#input_senha").val().trim() == "") {
        alertify.alert("O campo SENHA não foi preenchido!", function() {
            $("#input_senha").focus();
        });
        return;
    }
    $.ajax({
        url: "Controller?name=CadastrarOperador",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            nome: $("#input_nome").val(),
            administrador: $("#select_administrador option:selected").val(),
            login: $("#input_login").val(),
            senha: $("#input_senha").val()
        },
        dataType: "html",
        async: false,
        success: function(html) {
            $("#pagina").html("");
            $("#pagina").html(html);
            alertify.log("Cadastro realizado com sucesso!", "success", 5000);
        }
    });
}

function buscarLoginDuplicado() {
    if ($("#input_login").val() != "") {
        $.ajax({
            url: "Controller?name=BuscarLoginDuplicado",
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            data: {
                login: $("#input_login").val()
            },
            dataType: "json",
            async: false,
            success: function(json) {
                if (json.existeLogin == true) {
                    alertify.alert("Este login já está sendo utilizado por outro operador!", function() {
                        $("#input_login").val("");
                        $("#input_login").focus();
                    });
                }
            }
        });
    }
}

function habilitarSenha() {
    if ($("#chk_senha").is(":checked")) {
        $("#input_senha").val("");
        $("#input_senha").removeAttr("disabled");
        $("#input_senha").focus();
    } else {
        $("#input_senha").val("");
        $("#input_senha").attr("disabled", "disabled");
    }
}

function ativarOperador(codOperador) {
    alertify.confirm("Deseja realmente ativar este operador?", function(r) {
        if (r) {
            $.ajax({
                url: "Controller?name=AtivarOperador",
                type: "POST",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                data: {
                    codOperador: codOperador
                },
                dataType: "html",
                async: false,
                success: function(html) {
                    $("#pagina").html("");
                    $("#pagina").html(html);
                    alertify.log("Operacao concluida com sucesso!", "success", 5000);
                }
            });
        }
    });
}

function inativarOperador(codOperador) {
    alertify.confirm("Deseja realmente inativar este operador?", function(r) {
        if (r) {
            $.ajax({
                url: "Controller?name=InativarOperador",
                type: "POST",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                data: {
                    codOperador: codOperador
                },
                dataType: "html",
                async: false,
                success: function(html) {
                    $("#pagina").html("");
                    $("#pagina").html(html);
                    alertify.log("Operacao concluida com sucesso!", "success", 5000);
                }
            });
        }
    });
}

function alterarOperador() {
    if ($("#input_nome").val().trim() == "") {
        alertify.alert("O campo NOME nao foi preenchido!", function() {
            $("#input_nome").focus();
        });
        return;
    }
    if ($("#select_administrador option:selected").val() == "nada") {
        alertify.alert("O campo ADMINISTRADOR não foi selecionado!");
        return;
    }
    if ($("#input_login").val().trim() == "") {
        alertify.alert("O campo LOGIN não foi preenchido!", function() {
            $("#input_login").focus();
        });
        return;
    }
    if ($("#chk_senha").is(":checked") == true) {
        if ($("#input_senha").val().trim() == "") {
            alertify.alert("O campo SENHA não foi preenchido!", function() {
                $("#input_senha").focus();
            });
            return;
        }
    }
    $.ajax({
        url: "Controller?name=AlterarOperador",
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            codOperador: $("#input_codOperador").val(),
            nome: $("#input_nome").val(),
            administrador: $("#select_administrador option:selected").val(),
            login: $("#input_login").val(),
            senha: $("#input_senha").val()
        },
        dataType: "html",
        async: false,
        success: function(html) {
            $("#pagina").html("");
            $("#pagina").html(html);
            alertify.log("Alteracao realizada com sucesso!", "success", 5000);
        }
    });
}