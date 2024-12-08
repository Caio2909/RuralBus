<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adicionar Viagem</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        .form-container {
            margin-top: 2rem;
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
            padding: 1rem;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 8px;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 1rem;
        }
        .form-container label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: bold;
        }
        .form-container input, .form-container select, .form-container button {
            width: 100%;
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .form-container button {
            background-color: #007bff;
            color: white;
            font-weight: bold;
            border: none;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #0056b3;
        }
        .back-arrow {
            display: block;
            text-align: center;
            margin-top: 1rem;
        }
    </style>
</head>
<body>
    <!-- Cabeçalho -->
    <header>
        <div class="container">
            <div class="logo">
                <h1><a href="inicioAdmin.jsp">PassagensFácil</a></h1>
            </div>
            <div class="auth">
                <c:if test="${not empty adminLogado}">
                    <p>Bem-vindo, ${adminLogado.nome.split(' ')[0]}!</p>
                    <form action="processaLogout.do" method="post">
                        <button type="submit">Logout</button>
                    </form>
                </c:if>
            </div>
        </div>
    </header>

    <!-- Conteúdo Principal -->
    <section class="hero2">
        <div class="form-container">
        		<c:if test="${not empty successMessage}">
					<div class="sucess-message"
						style="color: green; margin-bottom: 1rem;">
						${successMessage}</div>
				</c:if>
				<c:if test="${not empty errorMessage}">
					<div class="error-message" style="color: red; margin-bottom: 1rem;">
						${errorMessage}</div>
				</c:if>
            <h2>Adicionar Nova Viagem</h2>
            <form action="processaAdicionarViagem.do" method="post">
                <div style="position: relative;">
                <label for="partida">Partida:</label>                
                <input type="text" id="origin" name="origin" required placeholder="Digite a cidade de partida">
				<ul id="originDropdown" class="dropdown-list"></ul>
				</div>
				<div style="position: relative;">
                <label for="destino">Destino:</label>                
                <input type="text" id="destination" name="destination" required placeholder="Digite a cidade de destino">
	            <ul id="destinationDropdown" class="dropdown-list"></ul>
	            </div>
                <label for="dataPartida">Data e Hora de Partida:</label>
                <input type="datetime-local" id="data_partida" name="data_partida" required>

                <label for="dataChegada">Data e Hora de Chegada:</label>
                <input type="datetime-local" id="data_chegada" name="data_chegada" required>

                <label for="veiculo">Veículo:</label>
                <select id="veiculo_id" name="veiculo_id" required>
                    <option value="">Selecione um veículo</option>
                    <c:forEach var="veiculo" items="${veiculos}">
                        <option value="${veiculo.id}">${veiculo.placa} - Capacidade: ${veiculo.capacidade}</option>
                    </c:forEach>
                </select>

                <label for="preco">Preço (R$):</label>
                <input type="number" id="preco" name="preco" step="0.01" required placeholder="Digite o preço da passagem">

                <button type="submit">Adicionar Viagem</button>
            </form>
        </div>
    </section>
	
    <!-- Rodapé -->
    <footer>
        <div class="container">
            <p>&copy; 2024 PassagensFácil. Todos os direitos reservados.</p>
            <ul class="footer-links">
                <li><a href="#">Termos de Uso</a></li>
                <li><a href="#">Política de Privacidade</a></li>
                <li><a href="#">FAQ</a></li>
            </ul>
            <div class="social-media">
                <a href="#">Facebook</a>
                <a href="#">Instagram</a>
                <a href="#">Twitter</a>
            </div>
        </div>
        
    </footer>
    <script src="buscaCidades.js"></script>
</body>
</html>
