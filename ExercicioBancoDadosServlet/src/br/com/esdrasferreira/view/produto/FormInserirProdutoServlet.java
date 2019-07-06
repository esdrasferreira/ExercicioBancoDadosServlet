package br.com.esdrasferreira.view.produto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet({ "/FormInserirProdutoServlet", "/form-inserir-produto" })
public class FormInserirProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		Integer id = (Integer) sessao.getAttribute("id");
		
		
		
		
		

		// inicia a saída HTML
		response.setContentType("text/html");
		String html = "<html>";

		if (id == null) {
			html += "<head><title>Área Restrita</title></head>" + "<body>" + "Você não tem permissão. "
					+ "<br /><a href=\"area-login\"> Clique aqui para logar</a>" + "</body></html>";

		} else {

			html += "<head><title>Add Produto</title></head>" + "<body>" +
			"<h1>Inserir Produtos</h1>"+
			"<table border=\"1\">";
			
			
			try {
									
					html += "<form action=\"inserir-produto-servlet\" method=\"post\">";
					html += "<input type=\"hidden\" name=\"id\" value=\""+id+"\">";
					html += "<tr><td>Nome</td><td><input type=\"text\" name=\"nome\" /></td></tr>";
					html += "<tr><td colspan=\"2\"><input type=\"submit\" value=\"Enviar\" /></td></tr>";
					
					
					
			    	html +="</table>";
				    html += "</form>";
				    
				    
				
				
				
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
