<%-- 
    Document   : formCadVeiculo
    Created on : May 01, 2014, 10:07:39 PM
    Author     : Ismael Rodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estacionamento Basanella</title>
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="jquery/dataTables_1.9.4/css/dataTables.css"/>
        <style type="text/css">
            div.dataTables_length label {
                width: 460px;
                float: left;
                text-align: left;
            }

            div.dataTables_length select {
                width: 75px;
            }

            div.dataTables_filter label {
                float: right;
                width: 460px;
            }

            div.dataTables_info {
                padding-top: 8px;
            }

            div.dataTables_paginate {
                float: right;
                margin: 0;
            }

            table {
                margin: 1em 0;
                clear: both;
            }

            table.dataTable th:active {
                outline: none;
            }
        </style>
        <!-- JS -->
        <script type="text/javascript" src="jquery/dataTables_1.9.4/js/dataTables.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#table_listaVeiculo").dataTable({
                    "sDom": "<'row'<'span8'l><'span8'f>r>t<'row'<'span8'i><'span8'p>>"
                });
            })
        </script>
    </head>
    <body>
        <div id="header">
            <h1><strong>Estacionamento Basanella</strong></h1>
        </div>        
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
                    <tr>
                        <td>DXX-3590</td>
                        <td>Carro</td>
                        <td>Chevrolet</td>
                        <td>Corsa Sedan</td>
                        <td><a>Alterar</a> <a>Excluir</a></td>
                    </tr>
                    <tr>
                        <td>TPM-0666</td>
                        <td>MOTO</td>
                        <td>HONDA</td>
                        <td>Twister</td>
                        <td><a>Alterar</a> <a>Excluir</a></td>
                    </tr>                    
                </tbody>
            </table>
        </div>
    </body>
</html>
