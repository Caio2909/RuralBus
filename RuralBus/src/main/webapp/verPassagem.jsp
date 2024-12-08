<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ver Passagem</title>
<link rel="stylesheet" href="stylesVerPassagem.css">
</head>
<body>
	<section class="passagem-detalhes">
		<h1>Detalhes da Passagem</h1>

		<div class="cliente-detalhes">
			<h2>Informações do Cliente</h2>
			<ul>
				<li><strong>Nome:</strong> ${cliente.nome}</li>
				<li><strong>CPF:</strong> ${cliente.CPF}</li>
				<li><strong>Email:</strong> ${cliente.email}</li>
			</ul>
		</div>

		<div class="passagem-info">
			<h2>Informações da Passagem</h2>
			<ul>
				<li><strong>ID da Passagem:</strong> ${passagem.id}</li>
				<li><strong>Origem:</strong> ${passagem.viagem.partida}</li>
				<li><strong>Destino:</strong> ${passagem.viagem.destino}</li>
				<li><strong>Data de Partida:</strong> <fmt:formatDate
						value="${passagem.viagem.data_partida}" pattern="dd/MM/yyyy HH:mm" /></li>
				<li><strong>Data de Chegada:</strong> <fmt:formatDate
						value="${passagem.viagem.data_chegada}" pattern="dd/MM/yyyy HH:mm" /></li>
				<li><strong>Número do Assento:</strong>
					${passagem.assento.numero}</li>
				<li><strong>Placa do Veículo:</strong>
					${passagem.viagem.veiculo.placa}</li>
				<li><strong>Capacidade do Veículo:</strong>
					${passagem.viagem.veiculo.capacidade}</li>
			</ul>
		</div>

		<a href="minhasPassagens.do" class="btn-voltar">Voltar à Suas
			Passagens</a>
	</section>
</body>
</html>
