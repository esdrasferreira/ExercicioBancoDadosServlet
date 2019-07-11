
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
<title>Produtos</title>
</head>
<body>

	<%
		UsuarioProduto user = (UsuarioProduto) request.getAttribute("usuario");
	%>



	<div class="container">
		<div class="row">
			<nav class="navbar navbar-sticky-top navbar-light bg-light">
				<span class="navbar-brand mb-0 h1">PRODUCT UPDATE</span>
			</nav>
		</div>
		<div class="row">
			<br />
			<h6 style="color: blue; font-weight: bold;">
				Current user:
				<%=user.getNome()%>
			</h6>
		</div>

	</div>


	<nav class="navbar navbar-light bg-light">
		<div class="row">
			<div class="col" style="column-width: 10cm;"></div>

			<form class="form-inline" action="produto-controller" method="post">
				<input type="hidden" name="idProduto"
					value="<%=user.getIdproduto()%>"> <input type="hidden"
					name="comando" value="update"> <input
					class="form-control mr-sm-2" type="text" name="novoProduto"
					value="<%=user.getProduto()%>" style="background-color: white;"
					aria-label="Salvar">
				<button class="btn btn-outline-success my-2 my-sm-0"
					style="background-color: aqua;" type="submit">Salvar</button>
			</form>
		</div>


	</nav>



	<div class="container">

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