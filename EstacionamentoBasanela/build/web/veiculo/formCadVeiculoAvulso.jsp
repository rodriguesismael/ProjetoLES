<%-- 
    Document   : formCadVeiculo
    Created on : Apr 29, 2014, 12:07:39 PM
    Author     : Alvaro Augusto Roberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estacionamento Basanella</title>
        <!-- JS -->
        <script type="text/javascript" src="js/veiculo/initVeiculo.js"></script>
        <script type="text/javascript" src="js/marca/initMarca.js"></script>
        <script type="text/javascript" src="js/modelo/initModelo.js"></script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] eq true}">
                <!-- Modal Sobre -->
                <div id="modal_sobre" class="modal fade">
                </div>                
                <!-- Modal Marca de Veiculo -->
                <div id="modal_marca" class="modal fade">
                </div>
                <!-- Modal Modelo de Veiculo -->
                <div id="modal_modelo" class="modal fade">
                </div>
                <div id="header">
                    <h1><strong>Estacionamento Basanella</strong></h1>
                    <a href="javascript:;" onclick="efetuarLogoff()"><img src="img/logoff2.png" title="Logoff"/></a>
                    <a href="javascript:;" onclick="sobre()"><img src="img/about.png" title="Sobre"/></a>
                    <a href="javascript:;"><img src="img/operador.png"/> <strong>${sessionScope['nome']}</strong></a>                    
                </div>
                <h1>Cadastro de Veículo Avulso</h1>
                <hr/>
                <form action="javascript:;">
                    <div style="margin: 0 auto; width: 900px;">
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_placa">Placa</label>
                                <input type="text" class="form-control" id="input_placa" name="input_placa" value="${placa}" readonly/>
                            </div>
                            <div class="col-xs-6 form-group">
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
                                            <li><a href="javascript:;" onclick="modalMarca('cad')">Adicionar</a></li>
                                            <li><a href="javascript:;" onclick="modalMarca('alt')">Alterar</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="select_tipo">Tipo</label>
                                <select class="form-control" id="select_tipo" name="select_tipo">
                                    <option value="nada"><-- selecione --></option>
                                    <option value="0">Carro</option>
                                    <option value="1">Moto</option>
                                </select>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="select_modelo">Modelo</label>
                                <div class="input-group">
                                    <select class="form-control" id="select_modelo" name="select_modelo" disabled>
                                        <option value="nada"><-- selecione --></option>
                                    </select>
                                    <div class="input-group-btn">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-plus"></span></button>
                                        <ul class="dropdown-menu pull-right">
                                            <li><a href="javascript:;" onclick="modalModelo('cad')">Inserir</a></li>
                                            <li><a href="javascript:;" onclick="modalModelo('alt')">Alterar</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="cadastrarVeiculoAvulso()">Cadastrar</button>
                        <button type="button" class="btn btn-default" onclick="enviar('FormHome')">Cancelar</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <h1>You are not loggedIn!</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>
