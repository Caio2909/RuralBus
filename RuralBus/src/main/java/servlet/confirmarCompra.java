package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import classes.Assento;
import classes.Viagem;
import classes.Cliente;
import classes.Passagem;
import dao.AssentoDAO;
import dao.ViagemDAO;
import dao.PassagemDAO;

@WebServlet("/confirmarCompra.do")
public class confirmarCompra extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
    
            int idViagem = Integer.parseInt(request.getParameter("idViagem"));
            int numeroAssento = Integer.parseInt(request.getParameter("seatNumber"));
            Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioLogado");

            System.out.println("Parametros obtidos");
       
            ViagemDAO viagemDAO = new ViagemDAO();
            Viagem viagem = viagemDAO.getViagemById(idViagem);
           System.out.println("Viagem obtida");
   
             System.out.println("Cliente obtido");
            AssentoDAO assentoDAO = new AssentoDAO();
            Assento assento = assentoDAO.getAssentoPorNumeroEViagem(numeroAssento, viagem);
            System.out.println("Assento obtido");
      
            if (assento == null) {
                assento = new Assento(numeroAssento, viagem);
                assento = assentoDAO.criarAssento(assento);
            }


            if (assentoDAO.isOcupado(assento)) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Assento já ocupado");
                return;
            }

            // Criar passagem
            Passagem passagem = new Passagem();
            passagem.setCliente(cliente);
            passagem.setViagem(viagem);
            passagem.setAssento(assento);

            // Salvar passagem
            PassagemDAO passagemDAO = new PassagemDAO();
            boolean sucessoCriacao = passagemDAO.addPassagem(passagem);
            if (sucessoCriacao) {
            	 request.setAttribute("passagem", passagem);
                 RequestDispatcher dispatcher = request.getRequestDispatcher("sucesso.jsp");
                 dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao criar passagem");
            }

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro no processamento da compra");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}