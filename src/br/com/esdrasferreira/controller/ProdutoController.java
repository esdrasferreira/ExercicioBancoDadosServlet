package br.com.esdrasferreira.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.esdrasferreira.model.dao.ProdutoDao;
import br.com.esdrasferreira.model.dao.UsuarioDao;
import br.com.esdrasferreira.model.dao.UsuarioProdutoDao;
import br.com.esdrasferreira.model.entity.Produto;
import br.com.esdrasferreira.model.entity.Usuario;
import br.com.esdrasferreira.model.entity.UsuarioProduto;

/**
 * Servlet implementation class ProdutoController
 */
@WebServlet({ "/ProdutoController", "/produto-controller" })
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		HttpSession sessao = request.getSession(true);
		RequestDispatcher requestDispatcher = null;

		String comando = request.getParameter("comando");
		ProdutoDao produtoDAO = null;
		Usuario user = null;

		if (comando == null)
			comando = "produtos";

		try {
			produtoDAO = new ProdutoDao();
			user = new Usuario();

			if (comando.equals("produtos")) {
				 user = (Usuario) request.getAttribute("usuario");

				List<Produto> produtos = produtoDAO.todos(user.getId());
				request.setAttribute("produtoList", produtos);
				request.setAttribute("usuario", user);
				requestDispatcher = request.getRequestDispatcher("arearestrita.jsp");
				
				
			}else if (comando.equals("excluir")) { 
				
				String idProduto = request.getParameter("idProduto");
				String idUsuario = request.getParameter("idUsuario");
				
				int id = Integer.parseInt(idProduto);
				int idUser = Integer.parseInt(idUsuario);

				produtoDAO.excluir(id);
				UsuarioDao userDao = new UsuarioDao();
				
				user = userDao.getUser(idUser);
				request.setAttribute("usuario", user);

				requestDispatcher = request.getRequestDispatcher("/produto-controller?comando=produtos");
				
			}else if (comando.equals("update")) {
				
				String idProduto = request.getParameter("idProduto");
				String idUsuario = request.getParameter("idUsuario");
				String novoProduto = request.getParameter("novoProduto");
				int id = Integer.parseInt(idProduto);
				int idUser = Integer.parseInt(idUsuario);
				
				Produto produto = new Produto();
				produto.setProduto(novoProduto);
				produto.setId(id);
				produtoDAO.atualizar(produto);
				
				UsuarioDao userDao = new UsuarioDao();
				user = userDao.getUser(idUser);
				request.setAttribute("usuario", user);
				
				requestDispatcher = request.getRequestDispatcher("/produto-controller?comando=produtos");
				
				
				
				
			}else if (comando.equals("atualizar")) {
				String idProduto = request.getParameter("idProduto");
				String idUsuario = request.getParameter("idUsuario");
				
				int id = Integer.parseInt(idProduto);
				int idUser = Integer.parseInt(idUsuario);
				UsuarioProduto userProd = new UsuarioProduto();
				 UsuarioProdutoDao userDao = new UsuarioProdutoDao();
				 userProd =userDao.getDados(idUser, id);
				 request.setAttribute("usuario", userProd);
				 requestDispatcher = request.getRequestDispatcher("atualizalista.jsp");
				
			}else if (comando.equals("procura")) {
				String valorProcura = request.getParameter("produtoSearch");
				String idUsuario = request.getParameter("idUsuario");
				
				
				int idUser = Integer.parseInt(idUsuario);
				List<Produto> produtos=produtoDAO.pesquisaPorNomeProduto(valorProcura, idUser);
				
				UsuarioDao userDao = new UsuarioDao();
				
				user = userDao.getUser(idUser);
				
				request.setAttribute("produtoList", produtos);
				request.setAttribute("usuario", user);
				
				requestDispatcher = request.getRequestDispatcher("resultadopesquisa.jsp");
				
			}else if (comando.equals("add")) {
				
				String idUsuario = request.getParameter("idUsuario");
								
				int idUser = Integer.parseInt(idUsuario);
				
				UsuarioDao userDao = new UsuarioDao();
				user = userDao.getUser(idUser);
				
				
				request.setAttribute("usuario", user);
				
				requestDispatcher = request.getRequestDispatcher("addproduto.jsp");
				
			}else if (comando.equals("salvar")) {
				
				String idUsuario = request.getParameter("idUsuario");
				String novoProduto = request.getParameter("txtRq");
								
				int idUser = Integer.parseInt(idUsuario);
				
				UsuarioDao userDao = new UsuarioDao();
				produtoDAO.addProduto(novoProduto, idUser);
				
				
				user = userDao.getUser(idUser);
				
				
				request.setAttribute("usuario", user);
				
				
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
