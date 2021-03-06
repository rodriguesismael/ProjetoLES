<%-- 
    Document   : home
    Created on : Apr 28, 2014, 10:22:31 PM
    Author     : Alvaro Augusto Roberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estacionamento Basanella</title>
        <!-- CSS -->

        <!-- JS -->
        <script type="text/javascript" src="js/initHome.js"></script>
        <script type="text/javascript" src="js/movimento/initMovimento.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#input_veiculo").mask("aaa-9999");
                $("#input_cliente").mask("999.999.999-99");
            });

        </script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] eq true}">
                <!-- Modal Sobre -->
                <div id="modal_sobre" class="modal fade">
                </div>                
                <!-- Modal Veiculo -->
                <div id="modal_veiculo" class="modal fade" role="dialog" aria-hidden="true">
                </div>
                <!-- Modal Cliente -->
                <div id="modal_cliente" class="modal fade" role="dialog" aria-hidden="true">
                </div>
                <div id="header">
                    <h1><strong>Estacionamento Basanella</strong></h1>
                    <a href="javascript:;" onclick="efetuarLogoff()"><img src="img/logoff2.png" title="Logoff"/></a>
                    <a href="javascript:;" onclick="sobre()"><img src="img/about.png" title="Sobre"/></a>
                    <a href="javascript:;"><img src="img/operador.png"/> <strong>${sessionScope['nome']}</strong></a>
                </div>
                <div id="conteudo">
                    <!-- Topo -->
                    <div id="top">
                        <div id="topLeft">
                            <div class="input-group">
                                <input type="text" class="form-control" id="input_veiculo" name="input_veiculo" placeholder="XXX-0123" onblur="$(this).val($(this).val().toUpperCase())"/>
                                <span class="input-group-btn">
                                    <button type="button" onclick="buscarVeiculo()" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                                </span>
                            </div>
                            <h3>Consulta de Veículo</h3>
                        </div>
                        <div id="topRight">
                            <div class="input-group">
                                <input type="text" class="form-control" id="input_cliente" name="input_cliente" placeholder="000.000.000-00"/>
                                <span class="input-group-btn">
                                    <button type="button" onclick="buscarCliente();" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                                </span>
                            </div>
                            <h3>Consulta de Cliente</h3>
                        </div>
                    </div>
                    <!-- Base -->
                    <div id="bottom">
                        <div id="bottomLeft1">
                            <!--<button type="button" class="btn btn-primary" onclick="enviar('ListarVeiculo')">Listar Veículos</button>-->
                            <a href="javascript:;" onclick="enviar('ListarVeiculo')">
                                <div>
                                    <img src="img/carro.png">
                                        <div><strong>Veículos</strong></div>
                                 </div>
                            </a>
                        </div>
                        <div id="bottomLeft2">
                            <!--<button type="button" class="btn btn-primary" onclick="enviar('ListarCliente')">Listar Clientes</button>-->
                            <a href="javascript:;" onclick="enviar('ListarCliente')">
                                <div>
                                    <img src="img/clientes.png">
                                        <div><strong>Clientes</strong></div>
                                 </div>
                            </a>
                        </div>
                        <div id="bottomRight1">
                            <c:if test="${sessionScope['administrador'] eq true}">
                                <!--<button type="button" class="btn btn-primary" onclick="enviar('ListarOperador')">Gerenciar Operadores</button>-->
                                <a href="javascript:;" onclick="enviar('ListarOperador')">
                                    <div>
                                        <img src="img/operadores.png">
                                            <div><strong>Operadores</strong></div>
                                     </div>
                                </a>                                
                            </c:if>
                        </div>
                        <div id="bottomRight1">
                            <c:if test="${sessionScope['administrador'] eq true}">
                                <form style="width:210px; margin: 0 auto;margin-top: 0px" id="formGerarRelatorio" name="formGerarRelatorio" action="GerarXLSRelatorioMensal" method="POST">
                                    <!--<button type="button" class="btn btn-primary" onclick="gerarRelatorioMensal()">Gerar Relatório Mensal</button>-->
                                    <a href="javascript:;" onclick="gerarRelatorioMensal()">
                                        <div>
                                            <img src="img/relatorio.png">
                                                <div><strong>Relatório Mensal</strong></div>
                                         </div>
                                    </a>                                      
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <h1>You are not loggedIn!</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>
