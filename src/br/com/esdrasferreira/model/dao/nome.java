package br.com.esdrasferreira.model.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.com.esdrasferreira.model.entity.Produto;

@WebServlet({ "/nome", "/nome-imagem" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5, // 5mb
		maxFileSize = 1024 * 1024 * 20, // 20mb
		maxRequestSize = 1024 * 1024 * 40 // 40mb

)

public class nome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		RequestDispatcher requestDispatcher = null;
		Produto produto = (Produto) request.getAttribute("produto");
		String nomeProduto = produto.getImagem();

		

		String caminhoDoUsuario = System.getProperty("user.home");
		

		

				String caminhoDaImagem = "imagens"+File.separator + nomeProduto;

//				caminhoDaImagem = caminhoDoUsuario + File.separator +
//				 "appservers" + File.separator
//				 + "arquivos" + File.separator + "imagens"+File.separator + nomeProduto;

				System.out.println(caminhoDaImagem);
				produto.setImagem(caminhoDaImagem);
				
				request.setAttribute("produto", produto);
				
							
							
			requestDispatcher = request.getRequestDispatcher("imagens.jsp");
		
		requestDispatcher.forward(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
