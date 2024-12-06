package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import classes.Admin;
import dao.AdminDAO;

/**
 * Servlet implementation class processaLoginAdmin
 */
@WebServlet("/processaLoginAdmin.do")
public class processaLoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AdminDAO adminDAO = new AdminDAO();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public processaLoginAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        
        Admin adm = adminDAO.getAdminByNome(nome);

        if (adm != null && adm.getSenha().equals(senha)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminLogado", adm);
            response.sendRedirect("inicioAdmin.jsp");
        } else {
            request.setAttribute("errorMessage", "Nome de usuário ou senha incorretos.");
            request.getRequestDispatcher("loginAdmin.jsp").forward(request, response);
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
