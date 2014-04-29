<%-- 
    Document   : home
    Created on : Apr 28, 2014, 10:22:31 PM
    Author     : Alvaro Augusto Roberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estacionamento Basanela</title>
        <!-- CSS -->

        <!-- JS -->
        <script type="text/javascript">
            function showModal(){
                var html = "";
                html += '<div class="modal-dialog">';
                html += '<div class="modal-content">';
                html += '<div class="modal-header">';
                html += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>';
                html += '<h4 class="modal-title" id="myModalLabel">Modal title</h4>';
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
        <div id="myModal" class="modal fade">
        </div>

        <div id="header">
            <h1><strong>Estacionamento Basanela</strong></h1>
        </div>
        <div id="conteudo">
            <input type="button" onclick="showModal()" value="Try it!"/>
        </div>
    </body>
</html>
