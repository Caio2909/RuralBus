package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import dao.AssentoDAO;

/**
 * Servlet implementation class assentosOcupados
 */
@WebServlet("/assentosOcupados.do")
public class assentosOcupados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AssentoDAO assentoDAO = new AssentoDAO();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public assentosOcupados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        int viagemId = Integer.parseInt(request.getParameter("viagemId"));
        try {
        	
            List<Integer> assentosOcupados = assentoDAO.getAssentosOcupadosPorViagem(viagemId);
            response.getWriter().write(new Gson().toJson(assentosOcupados));
            
        } catch (Exception e) {
        	
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"erro\": \"Erro ao buscar assentos ocupados.\"}");
            
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
