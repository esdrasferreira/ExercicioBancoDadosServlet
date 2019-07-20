package br.com.esdrasferreira.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5, // 5mb
maxFileSize = 1024 * 1024 * 20, // 20mb
maxRequestSize = 1024 * 1024 * 40 // 40mb

)
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		RequestDispatcher requestDispatcher = null;

		String comando = request.getParameter("comando");
		
		ProdutoDao produtoDAO = null;
		Usuario user = null;
		UsuarioDao userDao = null;
		String idProduto = request.getParameter("idProduto");
		
		
		int userID = (Integer) sessao.getAttribute("idUsuario");
		
		
		
		
		if (comando == null)
			comando = "produtos";

		try {
			produtoDAO = new ProdutoDao();
			user = new Usuario();
			userDao = new UsuarioDao();
			if (comando.equals("produtos")) {

				user = userDao.getUser(userID);

				List<Produto> produtos = produtoDAO.todos(user.getId());
				request.setAttribute("produtoList", produtos);
				request.setAttribute("usuario", user);
				requestDispatcher = request.getRequestDispatcher("arearestrita.jsp");

			} else if (comando.equals("excluir")) {

				int id = Integer.parseInt(idProduto);

				produtoDAO.excluir(id);

				requestDispatcher = request.getRequestDispatcher("/produto-controller?comando=produtos");

			} else if (comando.equals("update")) {

				String novoProduto = request.getParameter("novoProduto");
				
				if(novoProduto == "") {
					int id = Integer.parseInt(idProduto);

					UsuarioProduto userProd = new UsuarioProduto();
					UsuarioProdutoDao userProdDao = new UsuarioProdutoDao();
					userProd = userProdDao.getDados(userID, id);
					request.setAttribute("usuario", userProd);
					
					request.setAttribute("erros", "Necessário um nome do produto para salvar.");
					
					request.getRequestDispatcher("atualizalista.jsp").forward(request, response);;
				} else {
				
				int id = Integer.parseInt(idProduto);

				Produto produto = new Produto();
				produto.setProduto(novoProduto);
				produto.setId(id);
				produtoDAO.atualizar(produto);

				requestDispatcher = request.getRequestDispatcher("/produto-controller?comando=produtos");
				}
			} else if (comando.equals("atualizar")) {

				int id = Integer.parseInt(idProduto);

				UsuarioProduto userProd = new UsuarioProduto();
				UsuarioProdutoDao userProdDao = new UsuarioProdutoDao();
				userProd = userProdDao.getDados(userID, id);
				request.setAttribute("usuario", userProd);
				requestDispatcher = request.getRequestDispatcher("atualizalista.jsp");

			} else if (comando.equals("procura")) {
				String valorProcura = request.getParameter("produtoSearch");

				List<Produto> produtos = produtoDAO.pesquisaPorNomeProduto(valorProcura, userID);

				user = userDao.getUser(userID);

				request.setAttribute("produtoList", produtos);
				request.setAttribute("usuario", user);

				requestDispatcher = request.getRequestDispatcher("resultadopesquisa.jsp");

			} else if (comando.equals("add")) {

				user = userDao.getUser(userID);

				request.setAttribute("usuario", user);

				requestDispatcher = request.getRequestDispatcher("addproduto.jsp");

			} else if (comando.equals("salvar")) {
				
				
				String novoProduto = request.getParameter("txtRq");
				
				
				if(novoProduto == "") {
					
					user = userDao.getUser(userID);

					request.setAttribute("usuario", user);
					request.setAttribute("erros", "Necessário um nome do produto para salvar.");

					requestDispatcher = request.getRequestDispatcher("addproduto.jsp");
					
				}else {
				
				produtoDAO.addProduto(novoProduto, userID);

				requestDispatcher = request.getRequestDispatcher("/produto-controller?comando=produtos");
				}
			}else if(comando.equals("imagem")) {
				int id = Integer.parseInt(idProduto);
		
				Produto produto = new Produto();
				produto = produtoDAO.pesquisaPorID(id);
				
				user = userDao.getUser(userID);

				request.setAttribute("usuario", user);
				
				request.setAttribute("produto", produto);
				
				
				requestDispatcher = request.getRequestDispatcher("/addimagem.jsp");
				
			}else if(comando.equals("galeria")){
				int id = Integer.parseInt(idProduto);
				
				Produto produto = new Produto();
				produto = produtoDAO.pesquisaPorID(id);
				
				

				
				
				request.setAttribute("produto", produto);
				
				
				requestDispatcher = request.getRequestDispatcher("nome-imagem");
				
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
