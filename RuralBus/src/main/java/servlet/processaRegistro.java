package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import classes.Cliente;
import dao.ClienteDAO;
/**
 * Servlet implementation class processaRegistro
 */
@WebServlet("/processaRegistro.do")
public class processaRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public processaRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("name");
		String email = request.getParameter("email");
	    String senha = request.getParameter("password");
	    String confirmacao = request.getParameter("confirmPassword");
	    String cpf = request.getParameter("cpf");
	    if (senha == null || confirmacao == null) {
               request.setAttribute("errorMessage", "Senha não pode ser nula.");
               request.getRequestDispatcher("register.jsp").forward(request, response);
               return;
        }	
	    senha = senha.trim();
	    confirmacao = confirmacao.trim();
	    System.out.println( senha + confirmacao );
	    if (senha.equals(confirmacao) == false) {
            request.setAttribute("errorMessage", "Senhas não coincidem.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }		
	    Cliente cliente = new Cliente(nome, email, senha, cpf);
	    ClienteDAO clienteDAO = new ClienteDAO();
	    if (clienteDAO.getClienteByEmail(email) != null) {
			request.setAttribute("errorMessage", "Email já cadastrado.");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
	    clienteDAO.addCliente(cliente);
	    response.sendRedirect("login.jsp");	   	   
	}

}
