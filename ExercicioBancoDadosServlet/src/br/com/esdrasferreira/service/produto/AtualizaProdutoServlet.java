package br.com.esdrasferreira.service.produto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.esdrasferreira.model.dao.ProdutoDao;
import br.com.esdrasferreira.model.entity.Produto;

@WebServlet({ "/AtualizaProdutoServlet", "/atualiza-produto-servlet" })
public class AtualizaProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		Integer id = (Integer) sessao.getAttribute("id");
		Integer produtoId = Integer.parseInt(request.getParameter("id")); // <input type=hidden name=id/>
		String nome = request.getParameter("nome");// <input type=hidden name=nome />

		if (id == null) {
			// inicia a saída HTML
			response.setContentType("text/html");
			String html = "<html>";

			html += "<head><title>Área Restrita</title></head>" + "<body>" + "Você não tem permissão. "
					+ "<br /><a href=\"area-login\"> Clique aqui para logar</a>" + "</body></html>";
			response.getWriter().append(html);

		} else {

			

			try {
				ProdutoDao produtoDao = new ProdutoDao();

				Produto produto = new Produto(produtoId, nome);

				produtoDao.atualizar(produto);

				System.out.println(produto.getProduto());

				produtoDao.fecharConexao();
				response.sendRedirect("area-restrita");

			} catch (Exception e) {
				e.printStackTrace();
				response.setContentType("text/html");
				String html = "<html>";

				html += "<h3>Erro: " + e
						+ "</h3><br /> <br /><a href=\"finaliza-sessao\"> Logout do sistema</a></body></html>";
				response.getWriter().append(html);
			}

		}

		// termina a saída html
	}
}
