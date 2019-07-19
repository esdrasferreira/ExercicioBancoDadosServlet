
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

	<form action="produto-controller" method="get">
		<div class="container">

			<div class="form-inline">
				<div class="row">
					<label for="inputPassword2" class="sr-only">procurar</label> <input
						type="text" class="form-control" name="produtoSearch"
						placeholder="texto" value="">
					<button type="submit" class="btn btn-primary mb-2">Realizar
						Procura</button>
					<input type="hidden" name="comando" value="procura">
				</div>
			</div>

		</div>

	</form>
	<form action="produto-controller?comando=atualizar" method="post">
		<div class="container">
			<table class="table table-bordered table-dark">
				<thead class="thead-dark">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Produto</th>
						<th scope="col">Imagem</th>
						<th scope="col">Alterar nome produto</th>
						<th scope="col">Excluir produto</th>
						<th scope="col">Adicionar</th>
					</tr>
				</thead>

				<tbody>
					<%
						List<Produto> produtos = (List<Produto>) request.getAttribute("produtoList");
						for (Iterator<Produto> list = produtos.iterator(); list.hasNext();) {

							Produto prod = (Produto) list.next();
					%>
					<tr>
						<td><%=prod.getId()%></td>
						<td><%=prod.getProduto()%></td>
						<td><%=prod.getImagem()%></td>
						<td><a
							href="produto-controller?comando=atualizar&idProduto=<%=prod.getId()%>">Atualizar</a>
						</td>
						<td><a
							href="produto-controller?comando=excluir&idProduto=<%=prod.getId()%>">Excluir</a>
						</td>
						<td>
						<a
							href="produto-controller?comando=imagem&idProduto=<%=prod.getId()%>">Add imagem</a>
						</td>
					</tr>

					<%
						}
					%>
				</tbody>
			</table>

		</div>
	</form>
	
		<div class="container">

			<div class="row" style="margin: 5 mm;">
				<a class="btn btn-primary" href="produto-controller?comando=add"
					role="button" style="margin: 5mm; color: white;">Clique aqui para adicionar um produto...</a>

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