package br.com.esdrasferreira.view.sessao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.esdrasferreira.model.dao.ProdutoDao;
import br.com.esdrasferreira.model.entity.Produto;

@WebServlet({ "/RecuperaSessao", "/area-restrita" })
public class RecuperaSessao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		Integer id = (Integer) sessao.getAttribute("id");
		
		
		

		// inicia a saída HTML
		response.setContentType("text/html");
		String html = "<html>";
		

		if (id == null) {
			html += "<head><title>Área Restrita</title></head>" + "<body>" + "Você não tem permissão. "
					+ "<br /><a href=\"finaliza-sessao\"> Clique aqui para logar</a>" + "</body></html>";

		} else {

			html += "<head><title>Área Restrita</title></head>" + "<body>"
					
					+ "<h1>Produtos Encotrados </h1>" +
					"<br/> Usuario logado: "+ sessao.getAttribute("user")+
					"<br /><form action=\"resultado-pesquisa\">\r\n"
					+ "Pesquisa: <input type=\"text\" name=\"produtoSearch\" value=\"\" id="+id+" />\r\n"
					+ "<input type=\"submit\" value=\"Pesquisar\"/>\r\n" + "\r\n" + 
					 "<br/><a href=\"form-inserir-produto\">Clique aqui para adicionar um produto</a>"+
					"</form>" +
					
					"<table border=\"1\">" + "<tr><th>ID</th><th>Produto</th><th>Atualizar</th><th>Excuir</th></tr>";

			try {
				ProdutoDao produtoDao = new ProdutoDao();

				List<Produto> produtos = produtoDao.todos(id);

				for (Produto produto : produtos) {

					System.out.println(produto.getProduto());

					html += "<tr><td>" + produto.getId() + "</td>";
					html += "<td>" + produto.getProduto() + "</td>";
					html += "<td><a href=\"form-atualiza-servlet?id=" + produto.getId() + "\">Atualizar</a></td>";
					html += "<td><a href=\"excluir-servlet?id=" + produto.getId() + "\">Excluir</a></td>";
					html += "</tr>";

				}

				html += "</table>";
				produtoDao.fecharConexao();

			} catch (Exception e) {
				e.printStackTrace();

				html += "<h3>Erro: " + e + "</h3>";
			}

			html += "<br /><a href=\"finaliza-sessao\"> Logout do sistema</a>" + "</body></html>";
		}

		response.getWriter().append(html);
		// termina a saída html
	}

}
