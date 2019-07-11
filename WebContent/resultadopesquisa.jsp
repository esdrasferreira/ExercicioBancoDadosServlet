 
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
<title>Produtos Encontrados</title>
</head>
<body>

	<%
		HttpSession sessao = request.getSession(true);
	    Usuario user = (Usuario) request.getAttribute("usuario");
	    
	%>



	<div class="container">
		<div class="row">
			<nav class="navbar navbar-sticky-top navbar-light bg-light">
				<span class="navbar-brand mb-0 h1">Produtos Encontrados</span>
			</nav>
		</div>
		<div class="row">
			<br />
			<h6 style="color: blue;">
				Usuário logado:	<%=user.getUsuario()%>
			</h6>
		</div>

	</div>

	<form action="produto-controller" method="get">
		<div class="container">

			<div class="form-inline">
				<div class="row">
					<label for="inputPassword2" class="sr-only">procurar</label> <input
						type="text" class="form-control" name="produtoSearch"
						id="<%=user.getId()%>" placeholder="texto" value="">
					<button type="submit" class="btn btn-primary mb-2">Realizar
						Procura</button>
						<input type="hidden"  name="comando"   value="procura">
						<input type="hidden"  name="idUsuario"   value="<%=user.getId()%>">
				</div>
			</div>

		</div>

	</form>



	<div class="container">
		<table class="table table-bordered table-dark">
			<thead class="thead-dark">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Produto</th>
					<th scope="col">Atualizar</th>
					<th scope="col">Excluir</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Produto> produtos = (List<Produto>) request.getAttribute("produtoList");
							for(Iterator<Produto> list = produtos.iterator(); list.hasNext();){
								
								Produto prod = (Produto) list.next();
				
				%>


				<tr>
						<td><%=prod.getId()%></td>
						<td><%=prod.getProduto()%></td>
						<td><a href="produto-controller?comando=atualizar&idProduto=<%=prod.getId()%>&idUsuario=<%=user.getId()%>">Atualizar</a> </td>
						<td><a href="produto-controller?comando=excluir&idProduto=<%=prod.getId()%>&idUsuario=<%=user.getId()%>">Excluir</a> </td>
					</tr>

				<%
					}
				%>



			</tbody>
		</table>
	</div>
	<form action="produto-controller?comando=add" method="post">
		<div class="container">

			<div class="row" style="margin: 5 mm;">
				<a class="btn btn-primary" href="produto-controller?comando=add&idUsuario=<%=user.getId() %>"
					role="button">Clique aqui para adicionar um produto...</a> 
				<input type="hidden" name="idUsuario" value="<%=user.getId()%>">
			</div>
			<div class="row">
				<br />
			</div>
			<div class="row" style="font-style: italic;">
				<a class="btn btn-primary" href="login-controller?parametro=logout"
					role="button">Logout</a>
			</div>
		</div>
	</form>



</body>
</html>