<%-- 
    Document   : formCadCliente
    Created on : May 9, 2014, 10:46:00 AM
    Author     : ismael
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
        <script type="text/javascript" src="js/marca/initMarca.js"></script>
        <script type="text/javascript" src="js/modelo/initModelo.js"></script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] eq true}">
                <!-- Modal de Veiculo do cliente-->
                <div id="modal_veiculo" class="modal fade">
                </div>
                <div id="header">
                    <h1><strong>Estacionamento Basanela</strong></h1>
                </div>
                <h1>Cadastro de Cliente</h1>
                <hr/>
                <form action="javascript:;">
                    <div id="formCadVeiculoEsquerda">
                        <div class="form-group">
                            <label for="input_placa">Placa</label>
                            <input type="text" class="form-control" id="input_placa" name="input_placa" value="${placa}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="select_tipo">Tipo</label>
                            <select class="form-control" id="select_tipo" name="select_tipo">
                                <option value="nada"><-- selecione --></option>
                                <option value="1">Carro</option>
                                <option value="2">Moto</option>
                            </select>
                        </div>
                    </div>
                    <div id="formCadVeiculoDireita" class="form-group">
                        <div class="form-group">
                            <label for="select_marca">Marca</label>
                            <div class="input-group">
                                <select class="form-control" id="select_marca" name="select_marca" onchange="buscarModelo()">
                                    <option value="nada"><-- selecione --></option>
                                    <c:forEach var="marca" items="${listaMarca}">
                                        <option value="${marca.codMarca}">${marca.descricao}</option>
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
                                    <option value="nada"><-- selecione --></option>
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
                    <button type="button" class="btn btn-primary" onclick="cadastrarVeiculoAvulso()">Cadastrar</button>
                    <button type="button" class="btn btn-default" onclick="enviar('FormHome')">Cancelar</button>
                </form>
            </c:when>
            <c:otherwise>
                <h1>You are not loggedIn!</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>