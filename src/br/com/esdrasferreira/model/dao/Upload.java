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

@WebServlet({ "/Upload", "/upload" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5, // 5mb
		maxFileSize = 1024 * 1024 * 20, // 20mb
		maxRequestSize = 1024 * 1024 * 40 // 40mb

)

public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		RequestDispatcher requestDispatcher = null;
		String id = request.getParameter("id");

		String caminhoDoMeuAplicativo = request.getServletContext().getRealPath("");

		String caminhoDoUsuario = System.getProperty("user.home");
//		String caminhoDaClasse = System.getProperty("user.dir");
//		String nomeDoArquivo = request.getPart("upload").getName();
//		long tamanhoDoArquivo = request.getPart("upload").getSize();
		String tipoDoArquivo = request.getPart("upload").getContentType();

		if (request.getContentType().indexOf("multipart/form-data") >= 0) {

			List<String> tiposPermitidos = new ArrayList<String>();
			tiposPermitidos.add("image/jpeg");
			tiposPermitidos.add("image/png");

			if (tiposPermitidos.contains(tipoDoArquivo)) {

				String diretorioParaUploadDeImagens = caminhoDoMeuAplicativo + "arquivos" + File.separator + "imagens";

				 diretorioParaUploadDeImagens = caminhoDoUsuario + File.separator +
				 "appservers" + File.separator
				 + "arquivos" + File.separator + "imagens";

				System.out.println(diretorioParaUploadDeImagens);
				String nomeOriginalDoArquivo = null;

				for (Part part : request.getParts()) {
					nomeOriginalDoArquivo = nomeDoArquivo(part);
					if (nomeOriginalDoArquivo != "") {
						part.write(diretorioParaUploadDeImagens + File.separator + nomeOriginalDoArquivo);

						try {
							int Id = Integer.parseInt(id);

							ProdutoDao produtoDao = new ProdutoDao();
							produtoDao.addImagemAoProduto(nomeOriginalDoArquivo, Id);
							
							request.getRequestDispatcher("produto-controller?comando=produtos").forward(request,
									response);

						} catch (Exception e) {

							e.printStackTrace();
						}

					} else {
						request.getRequestDispatcher("produto-controller?comando=produtos").forward(request, response);
					}
				}

			}

		} else {
			requestDispatcher = request.getRequestDispatcher("upload-controller?comando=produtos");
		}
		requestDispatcher.forward(request, response);
	}

	public String nomeDoArquivo(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}

		return "";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
