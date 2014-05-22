<%-- 
    Document   : listaCliente
    Created on : May 20, 2014, 10:34:25 AM
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
        <script type="text/javascript" src="js/cliente/initCliente.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#table_listaCliente").dataTable({
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
                        {"bSortable": false, "aTargets": [2]}
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
                <h1>Lista de Clientes</h1>
                <hr/>
                <div id="listagem">
                    <table id="table_listaCliente" class="table table-bordered table-striped table-hover">
                        <thead>
                            <tr>
                                <th>CPF</th>
                                <th>Nome</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cliente" items="${listaCliente}">
                                <tr>    
                                    <td>${cliente.cpf}</td>
                                    <td>${cliente.nome}</td>
                                    <td style="text-align: center;">
                                        <a href="javascript:;" onclick="enviarParametro('FormAltCliente', '${cliente.cpf}')"><span class="glyphicon glyphicon-pencil"></span></a>
                                            <c:choose>
                                                <c:when test="${cliente.status eq true}">
                                                <a href="javascript:;" onclick="inativarCliente('${cliente.cpf}')"><span class="glyphicon glyphicon-remove"></span></a>
                                                </c:when>
                                                <c:otherwise>
                                                <a href="javascript:;" onclick="ativarCliente('${cliente.cpf}')"><span class="glyphicon glyphicon-ok"></span></a>
                                                </c:otherwise>
                                            </c:choose>
                                    </td>
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
