<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Login</title>
</head>
<body>
<div class="container"> 
	<form action="login-controller" method="post">
	
		<div class="form-row">
			<div class="col-2">
			<label for="usuarioInput">Usuário</label> <input type="text"
				class="form-control" value="esdras" name="usuario"
				aria-describedby="emailHelp" placeholder="Usuário"> 
				</div>
				<div class="col2">
			  <label for="exampleInputPassword1">Password</label> <input
				type="password" class="form-control" name="senha" value="1234"
				placeholder="Password">
			</div>
			
		</div>
		<div class="col">
			<input type="hidden" name="parametro" value="login" >
			<button type="submit" value="Logar" class="btn btn-primary">Submit</button>
			</div>
		
	</form>
  </div>
</body>
</html>