<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8" import="javax.servlet.*" import="java.sql.*"
	import="java.util.*" import="br.com.esdrasferreira.model.entity.*"
	import="java.util.Iterator"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Complete Bootstrap 4 Website Layout</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
	<link href="style.css" rel="stylesheet">
</head>
					
<body>
<%
			Produto produto = (Produto) request.getAttribute("produto");
						String caminho = produto.getImagem();
						
					%>
				
	<div class="container">
		<h1 class="my-4 text-center text-lg-left">Imagem</h1>
		<div class="row text-center text-lg-left">
			

			<div class="col-lg-3 col-md-4 col-xs-6">

				<img class="img-fluid img-thumbnail" src="<%=caminho%>">
				<address><%=caminho%></address>
			</div>

		
		
		</div>

	</div>







</body>

</html>