<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Viagens</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <div class="container">
            <div class="logo">
                <h1>Viagens Cadastradas</h1>
            </div>
        </div>
        
    </header>
    <section class="passagens">
    	<a href="inicioAdmin.jsp">Voltar ao Início</a>
    	<section class="hero" style="background-image:url()">
    <div class="container">
        <h2 style="color:rgba(0, 0, 0, 0.7)">Busca de Viagens</h2>
	        <form class="search-form" action="verViagens.do" method="get">
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
    	
        <c:choose>
            <c:when test="${not empty viagens}">
			   	<table>
			    <thead>
			        <tr>
			            <th>ID</th>
			            <th>Partida</th>
			            <th>Destino</th>
			            <th>Data de Partida</th>
			            <th>Data de Chegada</th>
			            <th>Placa do Veículo</th>
			            <th>Preço</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach var="viagem" items="${viagens}">
			            <tr class="verPassagem" data-href="verViagem.do?id=${viagem.id}">
			                <td>${viagem.id}</td>
			                <td>${viagem.partida}</td>
			                <td>${viagem.destino}</td>
			                <td><fmt:formatDate value="${viagem.data_partida}" pattern="dd/MM/yyyy HH:mm"/></td>
			                <td><fmt:formatDate value="${viagem.data_chegada}" pattern="dd/MM/yyyy HH:mm"/></td>
			                <td>${viagem.veiculo.placa}</td>
			                <td>R$ ${viagem.preco}</td>
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
            </c:when>
            <c:otherwise>
                <p>Viagem não encontrada.</p>
            </c:otherwise>
        </c:choose>
    </section>
    <script>
    	document.addEventListener("DOMContentLoaded", function() {
        	const rows = document.querySelectorAll(".verPassagem");
        	rows.forEach(row => {
            	row.addEventListener("click", function() {
               	window.location.href = this.dataset.href;
            	});
        	});
    	});
	</script>
    
	<script src="buscaCidades.js"></script>
    <footer>
        <p>&copy; 2024 PassagensFácil. Todos os direitos reservados.</p>
    </footer>
</body>
</html>
