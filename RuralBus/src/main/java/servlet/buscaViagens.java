package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import classes.Viagem;
import dao.ViagemDAO;

/**
 * Servlet implementation class buscaViagens
 */
@WebServlet("/buscaViagens.do")
public class buscaViagens extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscaViagens() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origem = request.getParameter("origin");
        String destino = request.getParameter("destination");
        String dataPartida = request.getParameter("departureDate");
        ViagemDAO viagemDAO = new ViagemDAO();
        List<Viagem> viagens = viagemDAO.buscarViagens(origem, destino, Date.valueOf(dataPartida));		
        request.setAttribute("viagens", viagens);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio.jsp");
        dispatcher.forward(request, response);        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
