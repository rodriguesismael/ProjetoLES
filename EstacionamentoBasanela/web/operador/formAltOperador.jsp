<%-- 
    Document   : formAltOperador
    Created on : 21/05/2014, 18:15:19
    Author     : Alvaro Augusto Roberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estacionamento Basanella</title>
        <!-- JS -->
        <script type="text/javascript" src="js/operador/initOperador.js"></script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] eq true}">
                <div id="header">
                    <h1><strong>Estacionamento Basanella</strong></h1>
                    <a href="javascript:;" onclick="efetuarLogoff()"><img src="img/logoff2.png" title="Logoff"/></a>
                    <a href="javascript:;" onclick="sobre()"><img src="img/about.png" title="Sobre"/></a>
                    <a href="javascript:;"><img src="img/operador.png"/> <strong>${sessionScope['nome']}</strong></a>                    
                </div>
                <h1>Alteração de Operador</h1>
                <hr/>
                <form action="javascript:;">
                    <input type="hidden" id="input_codOperador" name="input_codOperador" value="${operador.codOperador}"/>
                    <div style="margin: 0 auto; width: 900px;">
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="input_nome">Nome</label>
                                <input type="text" class="form-control" id="input_nome" name="input_nome" value="${operador.nome}"/>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="input_login">Login</label>
                                <input type="text" class="form-control" id="input_login" name="input_login" onblur="buscarLoginDuplicado()" value="${operador.login}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 form-group">
                                <label for="select_administrador">Administrador</label>
                                <select class="form-control" id="select_administrador" name="select_administrador">
                                    <option value="nada"><-- selecione --></option>
                                    <c:choose>
                                        <c:when test="${operador.administrador eq true}">
                                            <option value="1" selected>SIM</option>
                                            <option value="0">NÃO</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="1">SIM</option>
                                            <option value="0" selected>NÃO</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <div class="col-xs-6 form-group">
                                <label for="input_senha">Trocar senha?</label>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="chk_senha" onchange="habilitarSenha()"/>
                                    </span>
                                    <input type="password" class="form-control" id="input_senha" name="input_senha" maxlength="10" disabled>
                                </div>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="alterarOperador()">Alterar</button>
                        <button type="button" class="btn btn-default" onclick="enviar('ListarOperador')">Cancelar</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <h1>You are not loggedIn!</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>
