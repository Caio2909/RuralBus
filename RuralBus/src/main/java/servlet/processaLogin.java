package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import classes.Cliente;
import dao.ClienteDAO;

@WebServlet("/processaLogin.do")
public class processaLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClienteDAO clienteDAO;
    @Override
    public void init() throws ServletException {
        clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (email == null || email.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Email e senha são obrigatórios.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        Cliente cliente = clienteDAO.getClienteByEmail(email);

        if (cliente != null && cliente.getSenha().equals(senha)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", cliente);
            session.setMaxInactiveInterval(30 * 60);

            response.sendRedirect("Inicio.jsp");
        } else {
            request.setAttribute("errorMessage", "Email ou senha inválidos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}