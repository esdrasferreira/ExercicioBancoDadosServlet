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
		
		
		String html2 = "<td>Mark</td>";
		
		
		
		
		
		
		response.setContentType("text/html;charset=UTF-8");
		
		if(user != null) {
			sessao.setAttribute("id", user.getId());
			sessao.setAttribute("user", user.getUsuario());
			
			response.sendRedirect("AreaRestrita.jsp");
		
//			html += "<head><title>Login</title></head>" +
//					"<body>" +
//					"<h1>usuario e Senha</h1>" +
//					"Usuario logado: "+ user.getUsuario() +
//					"<br /><a href=\"area-restrita\">Navegar na Area restrita </a>";
		
		}else {
			
			html += "<head><meta charset=\"ISO-8859-1\">\n" + 
					"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"><title>Erro no Login</title></head>" +
					"<body><form action=\"finaliza-sessao\" >" +
					"<h2>Usuario e Senha Inválidos</h2>"+
					" <button type=\"submit\"  value=\"Logar\" class=\"btn btn-primary\">Voltar</button></form>";
			
		}
		
		html += "</body>" +
				"</html>";
	
		
		PrintWriter saida = response.getWriter();
		saida.print(html);
		saida.close();
		
		
		
	}

}
