<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Minhas Passagens</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Minhas Passagens</h1>
        
    </header>

    <section class="passagens">
    	<a href="Inicio.jsp">Voltar ao Início</a>
        <c:choose>
            <c:when test="${not empty passagens}">
			   	<table>
			    <thead>
			        <tr>
			            <th>ID</th>
			            <th>Partida</th>
			            <th>Destino</th>
			            <th>Data de Partida</th>
			            <th>Data de Chegada</th>
			            <th>Assento</th>
			            <th>Placa do Veículo</th>
			            <th>Preço</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach var="passagem" items="${passagens}">
			            <tr>
			                <td>${passagem.id}</td>
			                <td>${passagem.viagem.partida}</td>
			                <td>${passagem.viagem.destino}</td>
			                <td><fmt:formatDate value="${passagem.viagem.data_partida}" pattern="dd/MM/yyyy HH:mm"/></td>
			                <td><fmt:formatDate value="${passagem.viagem.data_chegada}" pattern="dd/MM/yyyy HH:mm"/></td>
			                <td>${passagem.assento.numero}</td>
			                <td>${passagem.viagem.veiculo.placa}</td>
			                <td>R$ ${passagem.preco}</td>
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
            </c:when>
            <c:otherwise>
                <p>Você ainda não possui passagens compradas.</p>
            </c:otherwise>
        </c:choose>
    </section>

    <footer>
        <p>&copy; 2024 PassagensFácil. Todos os direitos reservados.</p>
    </footer>
</body>
</html>
