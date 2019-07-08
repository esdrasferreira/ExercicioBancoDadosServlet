
<%@page import="br.com.esdrasferreira.model.dao.ProdutoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
 import="javax.servlet.*" 
 import="java.sql.*"  
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
 Integer idsession = (Integer)session.getAttribute("id");
 String produtoSearch = request.getParameter("produtoSearch");
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

  <form action="ResultadoPesquisa.jsp" method="get">
  <div class="container">
  
  	<div class="form-inline" >
  		<div class="row">
    		<label for="inputPassword2" class="sr-only">procurar</label>
    		<input type="text" class="form-control" name="produtoSearch" id="<%= idsession %>" placeholder="texto">
    		<button type="submit" class="btn btn-primary mb-2">Realizar Procura</button>
    	</div>
    </div>
    
      </div>
    	
  </form>
 


	<div class="container">
	  <table class="table table-bordered table-dark" >
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
  try{
	  Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
	  String url = "jdbc:mysql://192.175.108.234/servlet?useTimezone=true&serverTimezone=UTC";
	  Connection connection = DriverManager.getConnection(url , "esdras01", "j4cA0~uh!x-f");
	  Statement statement = connection.createStatement() ;
	  PreparedStatement ps = null;
	  ps = connection.prepareStatement("select servlet.produtos.id_produto, servlet.produtos.produto\n" + 
				"from servlet.produtos\n" + 
				"where servlet.produtos.usuariofk = ?\n" + 
				"and servlet.produtos.produto like ?");
	  ps.setInt(1, idsession);
	  ps.setString(2, '%'+ produtoSearch+ '%');
	  ResultSet resultset = ps.executeQuery() ;
	  while(resultset.next()){ %>
	  
	   <tr> 
    	  <td><%= resultset.getInt(1)%></td>
    	  <td><%= resultset.getString(2)%></td>
    	  <td><a href=AtualizaLista.jsp id= <%=resultset.getInt(1)%>>Atualizar</a></td>
    	  <td><a href=excluir-servlet id= <%=resultset.getInt(1)%>>Excluir</a></td>
       </tr>
	  
	  <% } 
	  ProdutoDao produtoDao = new ProdutoDao();
	  produtoDao.fecharConexao();
	  %> 
<%
	  
  }catch(Exception ex){
	  out.println("Exception: "+ex.getMessage());
	  ex.printStackTrace();
  }
 %>
   
    
  </tbody>
</table>
	</div>
		<div class="container">
		<div class="row" style="margin: 5 mm;" >
 			<a class="btn btn-primary" href="form-inserir-produto" role="button">Clique aqui para adicionar um produto...</a>
 		</div>
 		<div class="row">
 		<br/>
 		</div>
 		<div class="row" style="font-style: italic; " >
 			<a class="btn btn-primary" href="finaliza-sessao" role="button">Logout</a>
 		</div>
	</div>




</body>
</html>