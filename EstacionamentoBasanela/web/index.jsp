<%-- 
    Document   : index
    Created on : 25/04/2014, 17:27:14
    Author     : Alvaro Augusto Roberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transi/EN"
    "http://www.w3html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Estacionamento Basanela</title>
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="jquery/alertify_0.3.11/css/alertify.core.css"/>
        <link rel="stylesheet" type="text/css" href="jquery/alertify_0.3.11/css/alertify.bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/default.css"/>
        <!-- JS -->
        <script type="text/javascript" src="jquery/jquery_1.11.0/jquery_1.11.0.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
        <script type="text/javascript" src="jquery/alertify_0.3.11/js/alertify.js"></script>
        <script type="text/javascript" src="js/init.js"></script>
        <script type="text/javascript" src="js/initLogin.js"></script>
    </head>
    <body>
        <div id="header">
            <h1><strong>Estacionamento Basanela</strong></h1>
        </div>
        <div id="boxLogin">
            <form id="formLogin" action="javascript:;">
                <div class="form-group">
                    <label for="input_operador">Operador</label>
                    <input type="text" class="form-control" id="input_operador" name="input_operador"/>
                </div>
                <div class="form-group">
                    <label for="input_senha">Senha</label>
                    <input type="password" class="form-control input-sm" id="input_senha" name="input_senha"/>
                </div>
                <input type="submit" class="btn btn-primary" value="Entrar" onclick="efetuarLogin()"/>
            </form>
        </div>
    </body>
</html>