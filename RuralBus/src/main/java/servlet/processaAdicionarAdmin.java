package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import classes.Admin;
import dao.AdminDAO;

/**
 * Servlet implementation class processaAdicionarAdmin
 */
@WebServlet("/processaAdicionarAdmin.do")
public class processaAdicionarAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public processaAdicionarAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String senhaString = request.getParameter("senha");
		String nomeString = request.getParameter("nome");
		Admin admin = new Admin();
		
		admin.setNome(nomeString);
		admin.setSenha(senhaString);
		AdminDAO adminDAO = new AdminDAO();
		if (adminDAO.addAdmin(admin)) {
			request.setAttribute("successMessage", "Administrador adicionado com sucesso.");
			request.getRequestDispatcher("adicionarAdmin.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Erro ao adicionar administrador.");
			request.getRequestDispatcher("adicionarAdmin.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
