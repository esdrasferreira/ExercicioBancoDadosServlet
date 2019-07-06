package br.com.esdrasferreira.view.sessao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.esdrasferreira.model.dao.ProdutoDao;
import br.com.esdrasferreira.model.entity.Produto;


@WebServlet({ "/FormAtualizaServlet", "/form-atualiza-servlet" })
public class FormAtualizaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		Integer id = (Integer) sessao.getAttribute("id");
		Integer produtoId = Integer.parseInt(request.getParameter("id"));
		
		
		
		

		// inicia a saída HTML
		response.setContentType("text/html");
		String html = "<html>";

		if (id == null) {
			html += "<head><title>Área Restrita</title></head>" + "<body>" + "Você não tem permissão. "
					+ "<br /><a href=\"area-login\"> Clique aqui para logar</a>" + "</body></html>";

		} else {

			html += "<head><title>Área Restrita</title></head>" + "<body>" +
			"<h1>Produtos Encotrados </h1>"+
			"<table border=\"1\">";
			
			
			try {
				ProdutoDao produtoDao = new ProdutoDao();
				
				Produto produto = produtoDao.pesquisaPorID(produtoId);
				
				
					
					System.out.println(produto.getProduto());
					
					html += "<form action=\"atualiza-produto-servlet\" method=\"post\">";
					
					html += "<input type=\"hidden\" name=\"id\" value=\""+ produto.getId()+"\">";
					
					
					html +="<tr><td>ID</td><td>"+ produto.getId()+"</td></tr>";
					html += "<tr><td>Nome</td><td><input type=\"text\" name=\"nome\" value=\"" +  produto.getProduto()+ "\"/></td></tr>";
					html += "<tr><td colspan=\"2\"><input type=\"submit\" value=\"Enviar\" /></td></tr>";
					
					
					
			    	html +="</table>";
				    html += "</form>";
				    
				    
				produtoDao.fecharConexao();
				
				
			}catch (Exception e) {
				e.printStackTrace();
				
				
				html +="<h3>Erro: "+ e + "</h3>";
			}
			
			
			
			
			html += "<br /><a href=\"finaliza-sessao\"> Logout do sistema</a>" + "</body></html>";
		}

		response.getWriter().append(html);
		// termina a saída html
	}
		

}
