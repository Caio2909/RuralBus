<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel Administrativo</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Cabeçalho -->
    <header>
        <div class="container">
            <div class="logo">
                <h1><a href="#">PassagensFácil</a></h1>
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
        <div class="container">
            <h2>Painel Administrativo</h2>
            <p style="color: rgba(0, 0, 0, 0.7)">Bem-vindo ao painel de controle! Escolha uma das opções abaixo:</p>

            <div class="admin-options">
                <a href="adicionarViagem.jsp" class="admin-button">Adicionar Viagem</a>
                <a href="verViagens.jsp" class="admin-button">Ver Viagens</a>
                <a href="adicionarAdmin.jsp" class="admin-button">Adicionar Administrador</a>
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

</body>
</html>
