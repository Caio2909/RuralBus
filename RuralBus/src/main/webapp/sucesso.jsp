<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compra de Passagem - Sucesso</title>
    <link rel="stylesheet" href="stylesSucesso.css">
</head>
<body>
    <section class="sucesso-compra">
        <h1>Compra Realizada com Sucesso!</h1>
        <p>Detalhes da sua passagem:</p>
        
        <div class="passagem-detalhes">
            <ul>
                <li><strong>ID da Passagem:</strong> ${passagem.id}</li>
                <li><strong>Partida:</strong> ${passagem.viagem.partida}</li>
                <li><strong>Destino:</strong> ${passagem.viagem.destino}</li>
                <li><strong>Data de Partida:</strong> <fmt:formatDate value="${passagem.viagem.data_partida}" pattern="dd/MM/yyyy HH:mm"/></li>
                <li><strong>Data de Chegada:</strong> <fmt:formatDate value="${passagem.viagem.data_chegada}" pattern="dd/MM/yyyy HH:mm"/></li>
                <li><strong>Assento:</strong> ${passagem.assento.numero}</li>
                <li><strong>Placa do Veículo:</strong> ${passagem.viagem.veiculo.placa}</li>
                <li><strong>Capacidade do Veículo:</strong> ${passagem.viagem.veiculo.capacidade}</li>
                <li><strong>Preço:</strong> R$ ${passagem.preco}</li>
            </ul>
        </div>
        
        <a href="index.jsp" class="btn-voltar">Voltar à Página Inicial</a>
    </section>
</body>
</html>
