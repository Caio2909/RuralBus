package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import classes.Cliente;
import classes.Passagem;
import dao.PassagemDAO;
/**
 * Servlet implementation class minhasPassagens
 */
@WebServlet("/minhasPassagens")
public class minhasPassagens extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private PassagemDAO passagemDAO = new PassagemDAO();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public minhasPassagens() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioLogado");
        int id = cliente.getId();
        if (cliente != null) {
            List<Passagem> passagens = passagemDAO.getPassagensByClienteId(id);
            request.setAttribute("passagens", passagens);
        }

        request.getRequestDispatcher("minhasPassagens.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    
}
