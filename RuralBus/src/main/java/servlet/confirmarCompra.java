package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import classes.Assento;
import classes.Viagem;
import dao.AssentoDAO;

/**
 * Servlet implementation class confirmarCompra
 */
@WebServlet("/confirmarCompra.do")
public class confirmarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmarCompra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Viagem viagem = (Viagem)request.getAttribute("viagem");
		int numeroAssento = Integer.parseInt(request.getParameter("seatNumber"));
		Assento assento = new Assento();
		assento.setNumero(numeroAssento);
		assento.setVeiculoId(viagem.getVeiculo().getId());
		assento.setOcupado(true);
		AssentoDAO assentoDAO = new AssentoDAO();
		assentoDAO.atualizarOcupacao(assento);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
