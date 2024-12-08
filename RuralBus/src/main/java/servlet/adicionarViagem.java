package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import classes.Veiculo;
import dao.VeiculoDAO;

/**
 * Servlet implementation class adicionarViagem
 */
@WebServlet("/adicionarViagem.do")
public class adicionarViagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VeiculoDAO veiculoDAO = new VeiculoDAO();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adicionarViagem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Veiculo> veiculos = veiculoDAO.getVeiculos();
		request.setAttribute("veiculos", veiculos);
        request.getRequestDispatcher("adicionarViagem.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
