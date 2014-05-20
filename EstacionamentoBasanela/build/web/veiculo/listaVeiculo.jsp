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
                    "oLanguage": {
                        "sLengthMenu": "Mostrar _MENU_ registros por página.",
                        "sZeroRecords": "Nenhum registro encontrado!",
                        "sInfo": "Mostrando _START_ - _END_ de _TOTAL_",
                        "sInfoEmpty": "Mostrando 0 - 0 de 0",
                        "sInfoFiltered": "(filtrados de um total de _MAX_ registros)",
                        "sSearch": "Buscar",
                        "sProcessing": "Carregando...",
                        "oPaginate": {
                            "sFirst": "Primeiro",
                            "sLast": "Último",
                            "sNext": "Próximo",
                            "sPrevious": "Anterior"
                        }
                    },
                    "aoColumnDefs": [
                        {"bSortable": false, "aTargets": [4]}
                    ],
                    "bProcessing": true,
                    "bStateSave": true,
                    "bAutoWidth": false,
                    "aaSorting": [],
                    "sSortAsc": "header headerSortDown",
                    "sSortDesc": "header headerSortUp",
                    "sSortable": "header"
                });
            });
        </script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] eq true}">
                <div id="header">
                    <h1><strong>Estacionamento Basanella</strong></h1>
                    <a href="javascript:;" onclick="efetuarLogoff()"><img src="img/logoff2.png" title="Logoff"/></a>
                </div>
                <h1>Lista de Veiculos</h1>
                <hr/>
                <div id="listagem">
                    <table id="table_listaVeiculo" class="table table-bordered table-striped table-hover">
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
                                        <c:when test="${veiculo.tipo eq 0}">
                                            <td>Carro</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>Moto</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${veiculo.marca.descricao}</td>
                                    <td>${veiculo.modelo.descricao}</td>
                                    <td style="text-align: center;"><a href="javascript:;" onclick="enviarParametro('FormAltVeiculo', '${veiculo.placa}')"><span class="glyphicon glyphicon-pencil"></span></a></td>
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
        </c:choose>
    </body>
</html>
