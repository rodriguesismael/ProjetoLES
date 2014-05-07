<%-- 
    Document   : formCadVeiculoMensal
    Created on : May 6, 2014, 10:21:56 AM
    Author     : Alvaro Augusto Roberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estacionamento Basanela</title>
        <!-- JS -->
        <script type="text/javascript" src="js/veiculo/initVeiculo.js"></script>
        <script type="text/javascript" src="js/marcaVeiculo/initMarcaVeiculo.js"></script>
        <script type="text/javascript" src="js/cliente/initCliente.js"></script>
        <script type="text/javascript" src="js/modeloVeiculo/initModeloVeiculo.js"></script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] eq true}">
                <!-- Modal Marca de Veiculo -->
                <div id="modal_marcaVeiculo" class="modal fade">
                </div>
                <!-- Modal Modelo de Veiculo -->
                <div id="modal_modeloVeiculo" class="modal fade">
                </div>
                <!-- Modal Cliente -->
                <div id="modal_cliente" class="modal fade">
                </div>
                <div id="header">
                    <h1><strong>Estacionamento Basanela</strong></h1>
                </div>
                <h1>Cadastro de Veiculo Mensal</h1>
                <hr/>
                <form action="javascript:;">
                    <div id="formCadVeiculoEsquerda">
                        <div class="form-group">
                            <label for="input_placa">Placa</label>
                            <input type="text" class="form-control" id="input_placa" name="input_placa" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="select_tipo">Tipo</label>
                            <select class="form-control" id="select_tipo" name="select_tipo">
                                <option id="nada"><-- selecione --></option>
                                <option id="1">Carro</option>
                                <option id="2">Moto</option>
                            </select>
                        </div>
                    </div>
                    <div id="formCadVeiculoDireita">
                        <div class="form-group">
                            <label for="select_marca">Marca</label>
                            <div class="input-group">
                                <select class="form-control" id="select_marca" name="select_marca" onchange="buscarModelo()">
                                    <option id="nada"><-- selecione --></option>
                                    <c:forEach var="marca" items="${listaMarca}">
                                        <option id="${marca.codMarca}">${marca.descricao}</option>
                                    </c:forEach>
                                </select>
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-plus"></span></button>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="modalCadMarca()">Adicionar</a></li>
                                        <li><a href="modalAltMarca()">Alterar</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="select_modelo">Modelo</label>
                            <div class="input-group">
                                <select class="form-control" id="select_modelo" name="select_modelo" disabled>
                                    <option id="nada"><-- selecione --></option>
                                </select>
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-plus"></span></button>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="modalCadModelo()">Inserir</a></li>
                                        <li><a href="modalAltModelo()">Alterar</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="select_cliente">Proprietario</label>
                        <div class="input-group">
                            <select class="form-control" id="select_cliente" name="select_cliente">
                                <option id="nada"><-- selecione --></option>
                                <c:forEach var="cliente" items="${listaCliente}">
                                    <option id="${cliente.codCliente}">${cliente.descricao}</option>
                                </c:forEach>
                            </select>
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-plus"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="modalCadModelo()">Inserir</a></li>
                                    <li><a href="modalAltModelo()">Alterar</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-primary" onclick="cadastrarVeiculoMensal()">Cadastrar</button>
                    <button type="button" class="btn btn-default" onclick="enviar('FormHome')">Cancelar</button>
                </form>
            </c:when>
            <c:otherwise>
                <h1>You are not loggedIn!</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>
