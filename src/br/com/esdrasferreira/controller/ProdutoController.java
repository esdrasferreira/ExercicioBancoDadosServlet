package br.com.esdrasferreira.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.esdrasferreira.model.dao.ProdutoDao;
import br.com.esdrasferreira.model.dao.UsuarioDao;
import br.com.esdrasferreira.model.dao.UsuarioProdutoDao;
import br.com.esdrasferreira.model.entity.Produto;
import br.com.esdrasferreira.model.entity.Usuario;
import br.com.esdrasferreira.model.entity.UsuarioProduto;


@WebServlet({ "/ProdutoController", "/produto-controller" })
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		RequestDispatcher requestDispatcher = null;

		String comando = request.getParameter("comando");
		System.out.println(comando);
		ProdutoDao produtoDAO = null;
		Usuario user = null;

		if (comando == null)
			comando = "produtos";

		try {
			produtoDAO = new ProdutoDao();
			user = new Usuario();

			if (comando.equals("produtos")) {
				UsuarioDao userDao = new UsuarioDao();
				int userID = (Integer) sessao.getAttribute("idUsuario");
				user = userDao.getUser(userID);

				List<Produto> produtos = produtoDAO.todos(user.getId());
				request.setAttribute("produtoList", produtos);
				request.setAttribute("usuario", user);
				requestDispatcher = request.getRequestDispatcher("arearestrita.jsp");

			} else if (comando.equals("excluir")) {

				String idProduto = request.getParameter("idProduto");
				int id = Integer.parseInt(idProduto);

				produtoDAO.excluir(id);

				requestDispatcher = request.getRequestDispatcher("/produto-controller?comando=produtos");

			} else if (comando.equals("update")) {

				String idProduto = request.getParameter("idProduto");
				String novoProduto = request.getParameter("novoProduto");
				int id = Integer.parseInt(idProduto);

				Produto produto = new Produto();
				produto.setProduto(novoProduto);
				produto.setId(id);
				produtoDAO.atualizar(produto);

				requestDispatcher = request.getRequestDispatcher("/produto-controller?comando=produtos");

			} else if (comando.equals("atualizar")) {
				String idProduto = request.getParameter("idProduto");
				int userID = (Integer) sessao.getAttribute("idUsuario");

				int id = Integer.parseInt(idProduto);

				UsuarioProduto userProd = new UsuarioProduto();
				UsuarioProdutoDao userDao = new UsuarioProdutoDao();
				userProd = userDao.getDados(userID, id);
				request.setAttribute("usuario", userProd);
				requestDispatcher = request.getRequestDispatcher("atualizalista.jsp");

			} else if (comando.equals("procura")) {
				String valorProcura = request.getParameter("produtoSearch");
				int userID = (Integer) sessao.getAttribute("idUsuario");

				List<Produto> produtos = produtoDAO.pesquisaPorNomeProduto(valorProcura, userID);

				UsuarioDao userDao = new UsuarioDao();

				user = userDao.getUser(userID);

				request.setAttribute("produtoList", produtos);
				request.setAttribute("usuario", user);

				requestDispatcher = request.getRequestDispatcher("resultadopesquisa.jsp");

			} else if (comando.equals("add")) {

				int userID = (Integer) sessao.getAttribute("idUsuario");

				UsuarioDao userDao = new UsuarioDao();
				user = userDao.getUser(userID);
				System.out.println(user.getUsuario());
				request.setAttribute("usuario", user);

				requestDispatcher = request.getRequestDispatcher("addproduto.jsp");

			} else if (comando.equals("salvar")) {

				int userID = (Integer) sessao.getAttribute("idUsuario");
				String novoProduto = request.getParameter("txtRq");

				produtoDAO.addProduto(novoProduto, userID);

				requestDispatcher = request.getRequestDispatcher("/produto-controller?comando=produtos");

			}

			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
