<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="classes.Cliente" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compra de Passagens de Ônibus</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="a">
    <!-- Cabeçalho -->
    <header>
        <div class="container">
            <div class="logo">
                <h1><a href="#">PassagensFácil</a></h1>
            </div>
            <nav>
			    <ul>
			        <li><a href="#">Sobre Nós</a></li>
			        <li><a href="#">Ajuda</a></li>
			        <li><a href="#">Contato</a></li>
			        <c:if test="${not empty usuarioLogado}">
			            <li><a href="minhasPassagens.do">Minhas Passagens</a></li>
			        </c:if>
			    </ul>
			</nav>

            <div class="auth">
    			 <c:choose>
                    <c:when test="${not empty usuarioLogado}">
                        <p>Bem-vindo, ${usuarioLogado.nome.split(' ')[0]}!</p>
                        <form action="processaLogout.do" method="post">
                            <button type="submit">Logout</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <button id="loginButton">Login</button>
                    </c:otherwise>
                </c:choose>
			</div>
			<script>
    		document.getElementById('loginButton').addEventListener('click', function () {
        	window.location.href = 'login.jsp';
    			});
				</script>
            </div>
    </header>

<section class="hero">
    <div class="container">
        <h2>Encontre a sua passagem de ônibus!</h2>
	        <form class="search-form" action="buscaViagens.do" method="get">
	    		<label for="origin">De:</label>
	    		<div style="position: relative;">
                	<input type="text" id="origin" name="origin" placeholder="Cidade de Origem" value="${param.origin}" required>
                	<ul id="originDropdown" class="dropdown-list"></ul>
            	</div>
            	
				<label for="destination">Para:</label>
	    		<div style="position: relative;">
                	<input type="text" id="destination" name="destination" placeholder="Cidade de Destino" value="${param.destination}" required>
                	<ul id="destinationDropdown" class="dropdown-list"></ul>
           		 </div>
	
	    		<label for="departure-date">Data de Ida:</label>
	    		<input type="date" id="departure-date" name="departureDate" value="${param.departureDate}" required>
	
	    <button type="submit">Buscar Viagem</button>
			</form>	

    </div>
</section>



    <!-- Seção de Resultados -->
    <section class="results">
        <div class="container">
            <h3>Resultados da Pesquisa</h3>
            <div class="list">
    			<c:forEach var="viagem" items="${viagens}">
        			<div class="result-item">
            		<h4>${viagem.partida} para ${viagem.destino}</h4>
		            <p>Data da Partida: ${viagem.dataPartidaFormatada}</p>
		            <p>Data da Chegada: ${viagem.dataChegadaFormatada} </p>
		            <p>Preço: R$ ${viagem.preco}</p>
		            <form action="processaCheckout" method="get">
               		<input type="hidden" name="idViagem" value="${viagem.id}">
                <button type="submit">Selecionar</button>					
					</form>
        			</div>    			
    			</c:forEach>    			
				</div>
				<c:if test="${empty viagens}">
						<p style="color: #ff0000; text-align:center;" >Nenhuma Viagem Encontrada.</p>
				</c:if>
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