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
        <title>Estacionamento Basanela</title>
        <!-- CSS -->

        <!-- JS -->
        <script type="text/javascript" src="js/cliente/initCliente.js"></script>
        <script type="text/javascript" src="js/veiculo/initVeiculo.js"></script>
        <script type="text/javascript">
            function showModal() {
                var html = "";
                html += '<div class="modal-dialog">';
                html += '<div class="modal-content">';
                html += '<div class="modal-header">';
                html += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>';
                html += '<h4 class="modal-title">Veiculo Encontrado</h4>';
                html += '</div>';
                html += '<div class="modal-body">';
                html += '...';
                html += '</div>';
                html += '<div class="modal-footer">';
                html += '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>';
                html += '<button type="button" class="btn btn-primary">Save changes</button>';
                html += '</div>';
                html += '</div>';
                html += '</div>';
                $("#myModal").html(html);
                $("#myModal").modal();
            }
        </script>
    </head>
    <body>
        <!-- Modal Veiculo -->
        <div id="modal_veiculo" class="modal fade">
        </div>
        <!-- Modal Cliente -->
        <div id="modal_cliente" class="modal fade">
        </div>

        <div id="header">
            <h1><strong>Estacionamento Basanela</strong></h1>
        </div>
        <div id="conteudo">
            <!-- Topo -->
            <div id="top">
                <div id="topLeft">
                </div>
                <div id="topCenter">
                </div>
                <div id="topRight">
                </div>
            </div>
            <!-- Base -->
            <div id="bottom">
                <div id="bottomLeft">
                </div>
                <div id="bottomCenter">
                </div>
                <div id="bottomRight">
                </div>
            </div>
        </div>
    </body>
</html>
