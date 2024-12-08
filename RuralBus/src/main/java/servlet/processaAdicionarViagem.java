package servlet;

import classes.Veiculo;
import classes.Viagem;
import dao.ViagemDAO;
import dao.VeiculoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/processaAdicionarViagem.do")
public class processaAdicionarViagem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String partida = request.getParameter("origin");
        System.out.println(partida);
        String destino = request.getParameter("destination");
        System.out.println(destino);
        String dataPartidaStr = request.getParameter("data_partida");
        System.out.println(dataPartidaStr);
        String dataChegadaStr = request.getParameter("data_chegada");
        System.out.println(dataChegadaStr);
        String veiculoIdStr = request.getParameter("veiculo_id");
        System.out.println(veiculoIdStr);
        String precoStr = request.getParameter("preco");
        System.out.println(precoStr);
        ViagemDAO viagemDAO = new ViagemDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            java.util.Date dataPartida = sdf.parse(dataPartidaStr);
            java.util.Date dataChegada = sdf.parse(dataChegadaStr);
            int veiculoId = Integer.parseInt(veiculoIdStr);
            BigDecimal preco = new BigDecimal(precoStr);

            Veiculo veiculo = veiculoDAO.getVeiculoById(veiculoId);
            if (veiculo == null) {
                request.setAttribute("errorMessage", "Veículo não encontrado!");
                request.getRequestDispatcher("adicionarViagem.do").forward(request, response);
                return;
            }

            Viagem viagem = new Viagem();
            viagem.setPartida(partida);
            viagem.setDestino(destino);
            viagem.setData_partida(dataPartida);
            viagem.setData_chegada(dataChegada);
            viagem.setVeiculo(veiculo);
            viagem.setPreco(preco);

            boolean sucesso = viagemDAO.addViagem(viagem);
            if (sucesso) {
                request.setAttribute("successMessage", "Viagem adicionada com sucesso!");
            } else {
                request.setAttribute("errorMessage", "Erro ao adicionar a viagem!");
            }

        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Formato de data inválido!");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Veículo ID ou preço inválido!");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro inesperado: " + e.getMessage());
        }


        request.getRequestDispatcher("adicionarViagem.do").forward(request, response);
    }
}
