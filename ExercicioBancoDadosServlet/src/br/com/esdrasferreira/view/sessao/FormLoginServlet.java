package br.com.esdrasferreira.view.sessao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/FormLoginServlet", "/area-login" })
public class FormLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String html = "<html><body>" +

				"<form action=\"login\" method=\"post\">"
				+ "Login: <input type=\"text\" name=\"usuario\" value=\"esdras\" />" + "<br />"
				+ "Senha: <input type=\"text\" name=\"senha\" value=\"1234\" /> " + "<br />" + "<br /> " +

				"<input type=\"submit\" value=\"Logar\" />" +

				"</form>" + "</body></html>";

		response.getWriter().append(html);
	}

}
