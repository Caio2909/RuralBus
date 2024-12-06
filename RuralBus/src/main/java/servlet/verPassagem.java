package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import classes.Cliente;
import classes.Passagem;
import dao.ClienteDAO;
import dao.PassagemDAO;

/**
 * Servlet implementation class verPassagem
 */
@WebServlet("/verPassagem.do")
public class verPassagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verPassagem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPassagem = request.getParameter("id");
        PassagemDAO passagemDAO = new PassagemDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        Passagem passagem = passagemDAO.getPassagemById(idPassagem);
        Cliente cliente = clienteDAO.getClienteById(passagem.getClienteId());
        
        request.setAttribute("passagem", passagem);
        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher("verPassagem.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
