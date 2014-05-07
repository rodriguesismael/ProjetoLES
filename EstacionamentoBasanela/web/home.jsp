<%-- 
    Document   : home
    Created on : Apr 28, 2014, 10:22:31 PM
    Author     : Alvaro Augusto Roberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estacionamento Basanela</title>
        <!-- CSS -->

        <!-- JS -->
        <script type="text/javascript" src="js/cliente/initCliente.js"></script>
        <script type="text/javascript" src="js/veiculo/initVeiculo.js"></script>
    </head>
    <body>
        <!-- Modal Veiculo -->
        <div id="modal_veiculo" class="modal fade">
        </div>
        <!-- Modal Cliente -->
        <div id="modal_cliente" class="modal fade">
        </div>

        <div id="header">
            <h1><strong>Estacionamento Basanela</strong></h1>
        </div>
        <div id="conteudo">
            <!-- Topo -->
            <div id="top">
                <div id="topLeft">
                    <button type="button" class="btn btn-primary" onclick="enviar('FormCadVeiculoAvulso')">Cadastrar Veiculo Avulso</button>
                    <button type="button" class="btn btn-primary" onclick="enviar('FormCadVeiculoMensal')">Cadastrar Veiculo Mensal</button>
                    <button type="button" class="btn btn-primary" onclick="enviar('ListarVeiculo')">Listar Veiculos</button>
                </div>
                <div id="topCenter">
                </div>
                <div id="topRight">
                </div>
            </div>
            <!-- Base -->
            <div id="bottom">
                <div id="bottomLeft">
                </div>
                <div id="bottomCenter">
                </div>
                <div id="bottomRight">
                </div>
            </div>
        </div>
    </body>
</html>
