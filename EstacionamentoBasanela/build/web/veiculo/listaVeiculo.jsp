<%-- 
    Document   : formCadVeiculo
    Created on : May 01, 2014, 10:07:39 PM
    Author     : Ismael Rodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estacionamento Basanella</title>
        <!-- JS -->
        <script type="text/javascript">
            $(document).ready(function() {
                $("#table_listaVeiculo").dataTable({
                    "sDom": "<'row'<'span8'l><'span8'f>r>t<'row'<'span8'i><'span8'p>>"
                });
            })
        </script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] eq true}">
                <div id="header">
                    <h1><strong>Estacionamento Basanella</strong></h1>
                </div>
                <h1>Lista de Veiculos</h1>
                <hr/>
                <div id="boxLista">
                    <table id="table_listaVeiculo">
                        <thead>
                            <tr>
                                <th>Placa</th>
                                <th>Tipo</th>
                                <th>Marca</th>
                                <th>Modelo</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="veiculo" items="${listaVeiculo}">
                                <tr>    
                                    <td>${veiculo.placa}</td>
                                    <c:choose>
                                        <c:when test="${veiculo.tipo eq 1}">
                                            <td>Carro</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>Moto</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${veiculo.marca.descricao}</td>
                                    <td>${veiculo.modelo.descricao}</td>
                                    <td><a>Alterar</a><a>Inativar</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button type="button" class="btn btn-default" onclick="enviar('FormHome')">Voltar</button>
                </div>
            </c:when>
            <c:otherwise>
                <h1>You are not loggedIn!</h1>
            </c:otherwise>
        </c:choose>\
    </body>
</html>
