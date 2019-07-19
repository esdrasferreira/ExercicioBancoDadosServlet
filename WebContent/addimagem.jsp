
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

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
	integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
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
				<span class="navbar-brand mb-0 h1">Adicionar Imagem</span>
			</nav>
		</div>
		<div class="row">
			<br />
			<h6 style="color: blue;">
				Usuário logado:
				<%=user.getUsuario()%>
			</h6>
		</div>
		<div class="row">
			<h6 style="color: blue;">
				ID do produto:
				<%=produto.getId()%>
			</h6>
		</div>

	</div>
	<div class="container">
		<form class="form-inline" action="upload" method="post"
			enctype="multipart/form-data">
			<div class="input-group mb-3">
				<div class="custom-file">

					<input type="file" name="upload" class="custom-file-input"
						id="inputGroupFile02"> <label class="custom-file-label"
						for="inputGroupFile02">Choose file</label>

				</div>
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="submit">Enviar
						Imagem</button>
					<input type="hidden" value="<%=produto.getId()%>" name="id">
				</div>
			</div>
		</form>
	</div>

	

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
	<script>
		$('#inputGroupFile02').on('change', function() {
			//get the file name
			var fileName = $(this).val().replace('C:\\fakepath\\', " ");
			//replace the "Choose a file" label
			$(this).next('.custom-file-label').html(fileName);
		})
	</script>



</body>
</html>