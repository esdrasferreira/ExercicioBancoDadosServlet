
<%@page import="br.com.esdrasferreira.model.dao.ProdutoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="javax.servlet.*" import="java.sql.*"
	import="br.com.esdrasferreira.model.entity.Produto"%>

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
		Integer id = (Integer) sessao.getAttribute("id");
		Integer produtoId = Integer.parseInt(request.getParameter("Id"));
		String produtoSearch = request.getParameter("produtoSearch");

		out.write(produtoId);
	%>



	<div class="container">
		<div class="row">
			<nav class="navbar navbar-sticky-top navbar-light bg-light">
				<span class="navbar-brand mb-0 h1">Atualizar Produtos</span>
			</nav>
		</div>
		<div class="row">
			<br />
			<h6 style="color: blue;">
				Usuário logado:
				<%=sessao.getAttribute("user")%></h6>
		</div>

	</div>
	<%
		ProdutoDao dao = new ProdutoDao();
		Produto produto = new Produto();
		produto = dao.pesquisaPorID(produtoId);
		String nome = produto.getProduto();
		int idpro = produto.getId();
	%>

	<nav class="navbar navbar-light bg-light">
		<div class="row">
			<div class="col" style="column-width: 10cm;"></div>

			<form class="form-inline" action="atualiza-produto-servlet">
				<input type="hidden" name="id" value="<%=idpro%>"> <input
					class="form-control mr-sm-2" type="text" name="txtRq"
					value="<%=nome%>" style="background-color: white;"
					aria-label="Salvar">
				<button class="btn btn-outline-success my-2 my-sm-0"
					style="background-color: aqua;" type="submit">Salvar</button>
			</form>
		</div>

		</div>
	</nav>



	<div class="container">

		<div class="row">
			<br />
		</div>
		<div class="row" style="font-style: italic;">
			<a class="btn btn-primary" href="finaliza-sessao" role="button">Logout</a>
		</div>
	</div>




</body>
</html>