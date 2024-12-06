<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmação de Compra</title>
    <link rel="stylesheet" href="stylesCheckout.css">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <section class="checkout-page">
        <div class="container">
            <div class="checkout-layout">
                <div class="checkout-form">
                    <h2>Confirmação de Compra</h2>
                    <c:choose>
                        <c:when test="${not empty passagem}">
                            <ul>
                                <li><strong>ID da Passagem:</strong> ${passagem.id}</li>
                                <li><strong>Partida:</strong> ${passagem.viagem.partida}</li>
                                <li><strong>Destino:</strong> ${passagem.viagem.destino}</li>
                                <li><strong>Data de Partida:</strong> 
                                    <fmt:formatDate value="${passagem.viagem.data_partida}" pattern="dd/MM/yyyy HH:mm"/>
                                </li>
                                <li><strong>Data de Chegada:</strong> 
                                    <fmt:formatDate value="${passagem.viagem.data_chegada}" pattern="dd/MM/yyyy HH:mm"/>
                                </li>
                                <li><strong>Assento:</strong> ${passagem.assento.numero}</li>
                                <li><strong>Placa do Veículo:</strong> ${passagem.viagem.veiculo.placa}</li>
                                <li><strong>Preço:</strong> R$ ${passagem.viagem.preco}</li>
                            </ul>
                            <button onclick="window.location.href='Inicio.jsp'" class="btn-voltar">Voltar à Página Inicial</button>
                        </c:when>
                        <c:otherwise>
                            <p style="color: red;">Erro: Não foi possível confirmar a compra. Por favor, tente novamente.</p>
                            <button onclick="window.location.href='Inicio.jsp'" class="btn-voltar">Voltar para Seleção</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
