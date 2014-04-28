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
				<label for="input_operador"><strong>Placa:</strong></label> <input type="text" class="form-control input-sm" id="input_placa" name="input_placa"/><br>
				<label for="input_senha"><strong>Marca:</strong></label> <input type="text" class="form-control input" id="input_marca" name="input_marca"/> <br>
				<label for="input_senha"><strong>Modelo:</strong></label> <input type="text" class="form-control input" id="input_modelo" name="input_modelo"/> <br>
				<label for="input_senha"><strong>Tipo:</strong></label> 
				<input type="radio" class="form-control input" id="tipo_carro" name="input_tipo"/>Carro 
				<input type="radio" class="form-control input" id="tipo_moto" name="input_tipo"/>Moto
				<br>
				<input type="submit" name="cadastrar" class="btn btn-primary" id="cadastrar" value="Cadastrar"/>
				</div>
			</form>
		</div>
	</body>
</html>