
<%@page import="br.com.esdrasferreira.model.dao.ProdutoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8" import="javax.servlet.*" import="java.sql.*"
	import="br.com.esdrasferreira.model.entity.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Adicionar Produtos</title>
</head>
<body>

	<%
		Usuario user = (Usuario) request.getAttribute("usuario");
	%>



	<div class="container">
		<div class="row">
			<nav class="navbar navbar-sticky-top navbar-light bg-light">
				<span class="navbar-brand mb-0 h1">Adicionar Produtos</span>
			</nav>
		</div>
		<div class="row">
			<br />
			<h6 style="color: blue;">
				Usuário logado:
				<%=user.getUsuario()%></h6>
		</div>

	</div>


	<nav class="navbar navbar-light bg-light">
		<div class="row">
			<div class="col" style="column-width: 10cm;"></div>

			<form class="form-inline" action="produto-controller?comando=salvar"
				method="post">

				<input class="form-control mr-sm-2" type="text" name="txtRq"
					value="" style="background-color: white;" aria-label="Adicionar">
				<button class="btn btn-outline-success my-2 my-sm-0"
					style="background-color: aqua;" type="submit">Salvar</button>
			</form>
		</div>


	</nav>
	<div class="container">
	<label style="font-size: large; font-weight: bolder; color: red;">
	<%
			String erro = (String) request.getAttribute("erros");
			if (erro != null) {
				out.println(erro);
				out.println("<br>");
			}
		%>
	
	</label>
		
	</div>

	<div class="container">
	<div class="row">
			<br />
		</div>
		<div class="row" style="margin: 5 mm;">
			<a class="btn btn-primary" href="produto-controller?comando=produtos"
				role="button">Visualizar todos os produtos</a>

		</div>

		<div class="row">
			<br />
		</div>
		<div class="row" style="font-style: italic;">
			<a class="btn btn-primary" href="login-controller?parametro=logout"
				role="button">Logout</a>
		</div>
	</div>




</body>
</html>