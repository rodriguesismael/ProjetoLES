<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap.min.css">

		<!-- Optional theme -->
		<link rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap-theme.min.css">

		<!-- Latest compiled and minified JavaScript -->
		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
		<style type="text/css">
			.form-group{
				margin-top:25%;
			}
			.form-control {
				width:20%;
			}
		</style>
	</head>
	<body>
		<div id="conteudo">
			<form action="javascript:;">
				<div id="form" class="form-group" align="center">
				<label for="input_operador"><strong>Nome:</strong></label> <input type="text" class="form-control input-sm" id="input_placa" name="input_placa"/><br>
				<label for="input_senha"><strong>CPF:</strong></label> <input type="text" class="form-control input" id="input_marca" name="input_marca"/> <br>
				<label for="input_senha"><strong>Estado:</strong></label>
				<select class="form-control input" id="select_estado" name="input_modelo">
					<option>Selecione...</option>
				</select><br>
				<label for="input_senha"><strong>Cidade:</strong></label>
				<select class="form-control input" id="select_cidade" name="input_modelo">
					<option>Selecione...</option>
				</select>
				<br>
				<label for="input_senha"><strong>Endereço:</strong></label> <input type="text" class="form-control input" id="input_endereco" name="input_modelo"/> <br>
				<label for="input_senha"><strong>Telefone:</strong></label> <input type="text" class="form-control input" id="input_telefone" name="input_modelo"/> <br>
				<label for="input_senha"><strong>Celular:</strong></label> <input type="text" class="form-control input" id="input_celular" name="input_modelo"/> <br>
				<br>
				<input type="submit" name="cadastrar" class="btn btn-primary" id="cadastrar" value="Cadastrar"/>
				</div>
			</form>
		</div>
	</body>
</html>