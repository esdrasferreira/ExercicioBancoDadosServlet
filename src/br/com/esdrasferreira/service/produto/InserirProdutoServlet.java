package br.com.esdrasferreira.service.produto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.esdrasferreira.model.dao.ProdutoDao;

@WebServlet({ "/InserirProdutoServlet", "/inserir-produto-servlet" })
public class InserirProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		Integer id = (Integer) sessao.getAttribute("id");
		
		String nome = request.getParameter("txtRq");// <input type=hidden name=nome />

		if (id == null) {
			// inicia a sa�da HTML
			response.setContentType("text/html");
			String html = "<html>";

			html += "<head><title>�rea Restrita</title></head>" + "<body>" + "Voc� n�o tem permiss�o. "
					+ "<br /><a href=\"area-login\"> Clique aqui para logar</a>" + "</body></html>";
			response.getWriter().append(html);

		} else {

			

			try {
				ProdutoDao produtoDao = new ProdutoDao();

				produtoDao.addProduto(nome, id);

				produtoDao.fecharConexao();
				response.sendRedirect("AreaRestrita.jsp");

			} catch (Exception e) {
				e.printStackTrace();
				response.setContentType("text/html");
				String html = "<html>";

				html += "<h3>Erro: " + e
						+ "</h3><br /> <br /><a href=\"finaliza-sessao\"> Logout do sistema</a></body></html>";
				response.getWriter().append(html);
			}

		}

		// termina a sa�da html
	}
}