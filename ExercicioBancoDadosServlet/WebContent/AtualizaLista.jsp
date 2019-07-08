
<%@page import="br.com.esdrasferreira.model.dao.ProdutoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
 import="javax.servlet.*" 
 import="java.sql.*"  
 import= "br.com.esdrasferreira.model.entity.Produto"
 %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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
  		<span class="navbar-brand mb-0 h1">Produtos</span>
  		</nav>
   </div>
   <div class="row">
  		<br /> <h6 style="color: blue;">Usuário logado: <%=sessao.getAttribute("user") %></h6>
  	</div>
	
 </div>

  <form action="atualiza-produto-servlet" method="post">

	<div class="container">
	  <table class="table table-bordered" >
  <thead class="thead-dark">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Produto</th>
      <th scope="col">Atualizar</th>
     
    </tr>
  </thead>
  <tbody>
  <%
  
	  ProdutoDao dao = new ProdutoDao();
	  Produto produto = new Produto();
	  produto = dao.pesquisaPorID(produtoId);
	  String nome = produto.getProduto();
	  
	  
	 %>
	  
	   <tr> 
	   	  
    	  <td><%=produto.getId() %></td>
    	  <td><input type="text" class="form-control" name="nome" value="<%=nome%>"/></td>
    	  <td><a href=<%="\"?id="+produto.getId()+"\""%>>Atualizar</a></td>
    	  
       </tr>
    
  </tbody>
</table>
	</div>
	 </form>
		<div class="container">
		
 		<div class="row">
 		<br/>
 		</div>
 		<div class="row" style="font-style: italic; " >
 			<a class="btn btn-primary" href="finaliza-sessao" role="button">Logout</a>
 		</div>
	</div>




</body>
</html>