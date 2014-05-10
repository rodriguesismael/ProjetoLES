function buscarCidade(){
    if($("#select_estado option:selected").val() != "nada"){
        $.ajax({
            url:"Controller?name=BuscarCidade",
            type:"POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            data:{
                codEstado:$("#select_estado").val()
            },
            async: false,
            success: function(json) {
                $("#select_cidade option").remove();
                var html="";
                for (var i = 0; i < json.listaCidade.length; i++) {
                    if (json.listaCidade.length == 1) {
                        html += "<option value=\"" + json.listaCidade[i].codCidade + "\" selected>" + json.listaCidade[i].descricao + "</option>";
                    } else {
                        html += "<option value=\"" + json.listaCidade[i].codCidade + "\">" + json.listaCidade[i].descricao + "</option>";
                    }
                }
                $("#select_cidade").html(html);
                $("#select_cidade").attr("disabled", false);                
            }
        })
    }
}