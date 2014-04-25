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
				<label for="input_operador"><strong>Operador:</strong></label> <input type="text" class="form-control input-sm" id="input_operador" name="input_operador"/><br>
				<label for="input_senha"><strong>Senha:</strong></label> <input type="password" class="form-control input" id="input_senha" name="input_senha"/> <br>
				<input type="submit" name="entrar" class="btn btn-primary" id="entrar" value="Entrar"/>
				</div>
			</form>
		</div>
	</body>
</html>