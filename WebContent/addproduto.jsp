
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
		Produto produto = (Produto) request.getAttribute("produto");
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
				<%=user.getUsuario()%> Id do produto: <%=produto.getId()%></h6>
		</div>

	</div>


	<nav class="navbar navbar-light bg-light">
		<div class="row">
			<div class="col" style="column-width: 10cm;"></div>

			<form class="form-inline" action="upload"
				method="post" enctype="multipart/form-data">

				
					<input type="file" name="upload">
				   <input type="submit" value="Enviar arquivo">
				   <input type="hidden" value="<%=produto.getId()%>" name="id">
				   
				   
			</form>
			<form class="form-inline" action="upload"
				method="post" enctype="multipart/form-data">
						   
				   <input class="form-control mr-sm-2" type="text" name="txtRq" placeholder="Nome do Produto"
					value="" style="background-color: white;">
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