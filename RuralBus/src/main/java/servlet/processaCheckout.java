package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import classes.Viagem;
import dao.ViagemDAO;

/**
 * Servlet implementation class processaCheckout
 */
@WebServlet("/processaCheckout")
public class processaCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public processaCheckout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String travelIdParam = request.getParameter("idViagem"); 
		int travelId = Integer.parseInt(travelIdParam);		
		System.out.println("Travel ID: " + travelId);
		ViagemDAO viagemDAO = new ViagemDAO(); 
		Viagem viagem = viagemDAO.getViagemById(travelId);
		request.setAttribute("viagem", viagem); 
		request.getRequestDispatcher("checkout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
