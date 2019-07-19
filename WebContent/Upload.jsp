
<%@page import="br.com.esdrasferreira.model.dao.ProdutoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8" import="javax.servlet.*" import="java.sql.*"
	import="java.util.*" import="br.com.esdrasferreira.model.entity.*"
	import="java.util.Iterator"%>

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
		Usuario user = (Usuario) request.getAttribute("usuario");
	%>



	<div class="container">
		<div class="row">
			<nav class="navbar navbar-sticky-top navbar-light bg-light">
				<span class="navbar-brand mb-0 h1">Produtos</span>
			</nav>
		</div>
		<div class="row">
			<br />
			<h6 style="color: blue;">
				Usuário logado:
				<%=user.getUsuario()%>
			</h6>
		</div>

	</div>

	
	<form action="produto-controller?comando=atualizar" method="post">
		<div class="container">
			<table class="table table-bordered table-dark">
				<thead class="thead-dark">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Arquivo</th>
						<th scope="col">Atualizar</th>
						<th scope="col">Excluir</th>
					</tr>
				</thead>

				<tbody>
					<%
						
					%>
					<tr>
						<td></td>
						<td></td>
						<td><a
							href="produto-controller?comando=atualizar&idProduto=">Atualizar</a>
						</td>
						<td><a
							href="produto-controller?comando=excluir&idProduto=">Excluir</a>
						</td>
					</tr>

					
				</tbody>
			</table>

		</div>
	</form>
	
		<div class="container">
			
			<div class="row" style="margin: 5 mm;">
			<form action="upload" method="post" enctype="multipart/form-data">
				<input type="file" name="upload">
				<input type="submit" value="Enviar arquivo">
				
			</form>
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