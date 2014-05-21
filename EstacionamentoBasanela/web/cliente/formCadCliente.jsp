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
        <script type="text/javascript" src="js/cidade/initCidade.js"></script>
        <script type="text/javascript" src="js/cliente/initCliente.js"></script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] eq true}">
                <!-- Modal de Veiculo do cliente-->
                <div id="modal_veiculo" class="modal fade">
                </div>
                <div id="header">
                    <h1><strong>Estacionamento Basanela</strong></h1>
                    <a href="javascript:;" onclick="efetuarLogoff()"><img src="img/logoff2.png" title="Logoff"/></a>
                </div>
                <h1>Cadastro de Cliente</h1>
                <hr/>
                <form action="javascript:;">
                    <div style="margin: 0 auto; width: 900px;">
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_cpf">CPF</label>
                                <input type="text" class="form-control" id="input_cpf" name="input_cpf" value="${cpf}" readonly/>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="select_estado">Estado</label>
                                <select class="form-control" id="select_estado" name="select_estado" onchange="buscarCidade()">
                                    <option value="nada"><-- selecione --></option>
                                    <c:forEach var="estado" items="${listaEstado}">
                                        <option value="${estado.codEstado}">${estado.descricao}</option>
                                    </c:forEach>                                                    
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_nome">Nome</label>
                                <input type="text" class="form-control" id="input_nome" name="input_nome"/>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="select_cidade">Cidade</label>
                                <select class="form-control" id="select_cidade" name="select_cidade" disabled>
                                    <option value="nada"><-- selecione --></option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_telefone">Telefone</label>
                                <input type="text" class="form-control" id="input_telefone" name="input_telefone"/>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="input_endereco">Endereço</label>
                                <input type="text" class="form-control" id="input_endereco" name="input_endereco"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_celular">Celular</label>
                                <input type="text" class="form-control" id="input_celular" name="input_celular"/>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="select_periodo">Período</label>
                                <select class="form-control" id="select_periodo" name="select_periodo">
                                    <option value="nada"><-- selecione --></option>
                                    <option value="1">Manhã</option>
                                    <option value="2">Tarde</option>
                                    <option value="3">Noite</option>
                                </select>
                            </div>
<<<<<<< HEAD
                        </div>
                        <div class="row">
                            <div class="col-xs-12 form-group">
                                <label for="select_veiculo">Veiculo</label>
                                <div class="input-group">
                                    <select class="form-control" id="select_veiculo" name="select_veiculo">
                                        <option value="nada"><-- selecione --></option>
                                        <c:forEach var="veiculo" items="${listaVeiculo}">
                                            <option value="${veiculo.placa}">${veiculo.placa} / ${veiculo.modelo.descricao}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="input-group-btn">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-plus"></span></button>
                                        <ul class="dropdown-menu pull-right">
                                            <li><a href="javascript:;" onclick="modalNovoVeiculo()">Cadastrar</a></li>
                                            <li><a href="javascript:;" onclick="">Alterar</a></li>
                                        </ul>
=======
                            <div class="row">
                                    <div class="col-xs-12 form-group">
                                            <label for="select_veiculo">Veiculo</label>
                                            <div class="input-group">
                                                    <select class="form-control" id="select_veiculo" name="select_veiculo">
                                                            <option value="nada"><-- selecione --></option>
                                                            <c:forEach var="veiculo" items="${listaVeiculo}">
                                                                <option value="${veiculo.placa}">${veiculo.placa}/${veiculo.modelo.getDescricao()}</option>
                                                            </c:forEach>
                                                    </select>
                                                    <div class="input-group-btn">
                                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-plus"></span></button>
                                                            <ul class="dropdown-menu pull-right">
                                                                    <li><a href="javascript:;" onclick="modalVeiculo('cad')">Cadastrar</a></li>
                                                                    <!--<li><a href="javascript:;" onclick="modalVeiculo('alt')">Alterar</a></li>-->
                                                            </ul>
                                                    </div>
                                            </div>
>>>>>>> 55b79c656f8aaae289be264e242279c8ad916fda
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="cadastrarCliente()">Salvar</button>
                        <button type="button" class="btn btn-default" onclick="enviar('FormHome')">Voltar</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <h1>You are not loggedIn!</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>