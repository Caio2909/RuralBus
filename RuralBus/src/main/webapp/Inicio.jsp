<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compra de Passagens de Ônibus</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Cabeçalho -->
    <header>
        <div class="container">
            <div class="logo">
                <h1><a href="#">PassagensFácil</a></h1>
            </div>
            <nav>
                <ul>
                    <li><a href="#">Início</a></li>
                    <li><a href="#">Sobre Nós</a></li>
                    <li><a href="#">Ajuda</a></li>
                    <li><a href="#">Contato</a></li>
                </ul>
            </nav>
            <div class="auth">
                <button>Login</button>
                <select>
                    <option value="pt">PT</option>
                    <option value="en">EN</option>
                    <option value="es">ES</option>
                </select>
            </div>
        </div>
    </header>

<section class="hero">
    <div class="container">
        <h2>Encontre a sua passagem de ônibus!</h2>
        <form class="search-form">
            <!-- Campo de origem -->
            <label for="origin">De:</label>
            <div style="position: relative;">
                <input type="text" id="origin" name="origin" placeholder="Cidade de Origem" required>
                <ul id="originDropdown" class="dropdown-list"></ul>
            </div>

            <!-- Campo de destino -->
            <label for="destination">Para:</label>
            <div style="position: relative;">
                <input type="text" id="destination" name="destination" placeholder="Cidade de Destino" required>
                <ul id="destinationDropdown" class="dropdown-list"></ul>
            </div>

            <!-- Campo de data de ida -->
            <label for="departure-date">Data de Ida:</label>
            <input type="date" id="departure-date" name="departure-date" required>

            <!-- Campo de data de volta -->
            <label for="return-date">Data de Volta (Opcional):</label>
            <input type="date" id="return-date" name="return-date">

            <!-- Campo de número de passageiros -->
            <label for="passengers">Passageiros:</label>
            <input type="number" id="passengers" name="passengers" min="1" value="1">

            <!-- Botão de busca -->
            <button type="submit">Buscar Passagens</button>
        </form>
    </div>
</section>



    <!-- Seção de Resultados -->
    <section class="results">
        <div class="container">
            <h3>Resultados da Pesquisa</h3>
            <div class="filters">
                <h4>Filtros</h4>
                <label>Preço: <input type="range" min="0" max="500"></label>
                <label><input type="checkbox"> Empresa A</label>
                <label><input type="checkbox"> Empresa B</label>
                <label><input type="checkbox"> Leito</label>
                <label><input type="checkbox"> Executivo</label>
            </div>
            <div class="list">
    		<div class="result-item">
        		<h4>Empresa A</h4>
        			<p>08:00 - 12:00</p> <!-- Horário de saída e chegada em linha -->
        			<p>4h</p>           <!-- Duração sem rótulo -->
        			<p>R$ 120,00</p>    <!-- Preço sem rótulo -->
        		<button>Selecionar</button>
    		</div>
    		<div class="result-item">
        		<h4>Empresa B</h4>
        			<p>14:00 - 18:00</p> <!-- Horário de saída e chegada em linha -->
        			<p>4h</p>           <!-- Duração sem rótulo -->
        			<p>R$ 150,00</p>    <!-- Preço sem rótulo -->
        	<button>Selecionar</button>
    </div>
</div>
                </div>
            </div>
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
