<%-- 
    Document   : formAltCliente
    Created on : May 20, 2014, 10:45:13 AM
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
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] eq true}">
                <div id="header">
                    <h1><strong>Estacionamento Basanela</strong></h1>
                    <a href="javascript:;" onclick="efetuarLogoff()"><img src="img/logoff2.png" title="Logoff"/></a>
                </div>
                <h1>Alteracao de Cliente</h1>
                <hr/>
                <form action="javascript:;">
                    <div style="margin: 0 auto; width: 900px;">
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_cpf">CPF</label>
                                <input type="text" class="form-control" id="input_cpf" name="input_cpf" value="${cliente.cpf}" readonly/>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="select_estado">Estado</label>
                                <select class="form-control" id="select_estado" name="select_estado" onchange="buscarCidade()">
                                    <option value="nada"><-- selecione --></option>
                                    <c:forEach var="estado" items="${listaEstado}">
                                        <c:choose>
                                            <c:when test="${estado.codEstado eq cliente.estado.codEstado}">
                                                <option value="${estado.codEstado}" selected>${estado.descricao}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${estado.codEstado}">${estado.descricao}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>                                                    
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_nome">Nome</label>
                                <input type="text" class="form-control" id="input_nome" name="input_nome" value="${cliente.nome}"/>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="select_cidade">Cidade</label>
                                <select class="form-control" id="select_cidade" name="select_cidade">
                                    <option value="nada"><-- selecione --></option>
                                    <c:forEach var="cidade" items="${listaCidade}">
                                        <c:choose>
                                            <c:when test="${cidade.codCidade eq cliente.cidade.codCidade}">
                                                <option value="${cidade.codCidade}" selected>${cidade.descricao}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${cidade.codCidade}">${cidade.descricao}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_telefone">Telefone</label>
                                <input type="text" class="form-control" id="input_telefone" name="input_telefone" value="${cliente.telefone}"/>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="input_endereco">Endereço</label>
                                <input type="text" class="form-control" id="input_endereco" name="input_endereco" value="${cliente.endereco}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_celular">Celular</label>
                                <input type="text" class="form-control" id="input_celular" name="input_celular" value="${cliente.celular}"/>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="select_periodo">Período</label>
                                <select class="form-control" id="select_periodo" name="select_periodo">
                                    <option value="nada"><-- selecione --></option>
                                    <c:if test="${cliente.periodo eq 1}">
                                        <option value="1" selected>Manhã</option>
                                        <option value="2">Tarde</option>
                                        <option value="3">Noite</option>
                                    </c:if>
                                    <c:if test="${cliente.periodo eq 2}">
                                        <option value="1">Manhã</option>
                                        <option value="2" selected>Tarde</option>
                                        <option value="3">Noite</option>
                                    </c:if>
                                    <c:if test="${cliente.periodo eq 3}">
                                        <option value="1">Manhã</option>
                                        <option value="2">Tarde</option>
                                        <option value="3" selected>Noite</option>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="alterarCliente()">Alterar</button>
                        <button type="button" class="btn btn-default" onclick="enviar('ListarCliente')">Voltar</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <h1>You are not loggedIn!</h1>
            </c:otherwise>
        </c:choose>
    </body>    
</html>
