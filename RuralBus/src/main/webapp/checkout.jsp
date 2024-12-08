<%@page import="dao.ViagemDAO"%>
<%@page import="dao.ClienteDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="classes.Cliente"%>
<%@ page import="dao.ViagemDAO"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Seleção de Assento</title>
<link rel="stylesheet" href="stylesCheckout.css">
<link rel="stylesheet" href="styles.css">
</head>
<body>

	<section class="checkout-page">
		<div class="container">
			<a href="Inicio.jsp" class="back-arrow"> Voltar para Seleção </a>
			<div class="checkout-layout">

				<div class="checkout-form">

					<h2>Finalizar Compra</h2>
					<form action="confirmarCompra.do" method="get">
						<h3>Seus dados:</h3>
						<label for="nome">Nome Completo:</label> <input type="text"
							id="nome" name="nome" placeholder="Digite seu nome"
							value="${usuarioLogado.nome}" required readonly> <label
							for="cpf">CPF:</label> <input type="text" id="cpf" name="cpf"
							placeholder="Digite seu CPF" value="${usuarioLogado.CPF}"
							required readonly> <label for="email">E-mail:</label> <input
							type="email" id="email" name="email"
							placeholder="Digite seu e-mail" value="${usuarioLogado.email}"
							required readonly>
						<h3>Escolha seu assento:</h3>
						<input type="hidden" name="idViagem" value="${viagem.id}">
						<div class="checkout-form">
							<h2>Escolha seu assento</h2>
							<label for="seatNumber">Número do assento:</label> <select id="seatNumber" name="seatNumber" required>
							</select>
						</div>
						<div class="checkout-details">
							<h2>Mapa do ônibus</h2>
							<img src="mapa_onibus.png" alt="Mapa de Assentos do ônibus"
								style="width: 100%; border-radius: 10px;">
						</div>
						<button type="submit">Confirmar Compra</button>
					</form>
				</div>

				<div class="checkout-details">
					<div class="checkout-details">
						<h2>Detalhes da Viagem</h2>
						<c:if test="${not empty viagem}">
							<p>
								<strong>Origem:</strong> ${viagem.partida}
							</p>
							<p>
								<strong>Destino:</strong> ${viagem.destino}
							</p>
							<p>
								<strong>Data de Partida:</strong> ${viagem.dataPartidaFormatada}
							</p>
							<p>
								<strong>Data de Chegada:</strong> ${viagem.dataChegadaFormatada}
							</p>
							<p>
								<strong>Preço:</strong> R$ ${viagem.preco}
							</p>
						</c:if>
						<c:if test="${empty viagem}">
							<p style="color: red;">Erro: Dados da viagem não encontrados.</p>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="seatSelection.js"></script>
</body>
</html>