package br.com.esdrasferreira.service.sessao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import br.com.esdrasferreira.model.dao.UsuarioDao;
import br.com.esdrasferreira.model.entity.Usuario;



@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void destroy() {
		super.destroy();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		Usuario user = null;
		response.setContentType("text/html;charset=UTF-8");

		HttpSession sessao = request.getSession(true);
	
		
		
		try {
			UsuarioDao usuarioDao = new UsuarioDao();
			
			user = usuarioDao.login(usuario, senha);			
			
			usuarioDao.fecharConexao();			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		String html = "<html>";
		response.setContentType("text/html;charset=UTF-8");
		
		if(user != null) {
			sessao.setAttribute("id", user.getId());
			sessao.setAttribute("user", user.getUsuario());
			
			response.sendRedirect("area-restrita");
		
//			html += "<head><title>Login</title></head>" +
//					"<body>" +
//					"<h1>usuario e Senha</h1>" +
//					"Usuario logado: "+ user.getUsuario() +
//					"<br /><a href=\"area-restrita\">Navegar na Area restrita </a>";
		
		}else {
			
			html += "<head><title>Erro no Login</title></head>" +
					"<body>" +
					"<h1>Usuario e Senha Invalidos</h1>";
			
		}
		
		html += "</body>" +
				"</html>";
	
		
		PrintWriter saida = response.getWriter();
		saida.print(html);
		saida.close();
		
		
		
	}

}
