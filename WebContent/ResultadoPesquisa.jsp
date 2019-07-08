
<%@page import="br.com.esdrasferreira.model.dao.ProdutoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="javax.servlet.*" import="java.sql.*"
	import="java.util.*" import="br.com.esdrasferreira.model.entity.*"
	import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Produtos</title>
</head>
<body>

	<%
		HttpSession sessao = request.getSession(true);
		Integer idsession = (Integer) session.getAttribute("id");
		String produtoSearch = request.getParameter("produtoSearch");
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
				<%=sessao.getAttribute("user")%></h6>
		</div>

	</div>

	<form action="ResultadoPesquisa.jsp" method="get">
		<div class="container">

			<div class="form-inline">
				<div class="row">
					<label for="inputPassword2" class="sr-only">procurar</label> <input
						type="text" class="form-control" name="produtoSearch"
						id="<%=idsession%>" placeholder="texto">
					<button type="submit" class="btn btn-primary mb-2">Realizar
						Procura</button>
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
					Produto prod = new Produto();

					ProdutoDao dao = new ProdutoDao();
					List<Produto> produtos = dao.pesquisaPorNomeProduto(produtoSearch, idsession);
					Iterator<Produto> iter = produtos.iterator();
					while (iter.hasNext()) {
						prod = iter.next();
				%>


				<tr>
						<td><%=prod.getId()%></td>
						<td><%=prod.getProduto()%></td>
						<td><a href=<%="\"AtualizaLista.jsp?Id=" + prod.getId() + "\""%>>Atualizar</a></td>
						<td><a href=<%="\"excluir-servlet?Id=" + prod.getId() + "\""%>>Excluir</a></td>
					</tr>

				<%
					}
				%>



			</tbody>
		</table>
	</div>
	<div class="container">
	
		<div class="row" style="margin: 5 mm;">
			<a class="btn btn-primary" href="AddProduto.jsp" role="button">Clique
				aqui para adicionar um produto...</a>
		</div>
		<div class="row">
			<br />
		</div>
		<div class="row" style="font-style: italic;">
			<a class="btn btn-primary" href="finaliza-sessao" role="button">Logout</a>
		</div>
	</div>




</body>
</html>