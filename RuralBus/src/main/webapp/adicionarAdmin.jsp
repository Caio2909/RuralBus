<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adicionar Administrador</title>
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
            <h2>Adicionar Administrador</h2>
            <p style="color: rgba(0, 0, 0, 0.7)">Preencha as informações abaixo para adicionar um novo administrador:</p>

            <form action="processaAdicionarAdmin.do" method="post" class="admin-form">
                <label style="color:rgba(0, 0, 0, 0.7)" for="nome">Nome Completo:</label>
                <input type="text" id="nome" name="nome" required placeholder="Digite o nome completo">

                <label style="color:rgba(0, 0, 0, 0.7)" for="senha">Senha:</label>
                <input type="password" id="senha" name="senha" required placeholder="Digite a senha">

                <button type="submit" class="admin-button">Adicionar Administrador</button><br>
                
            </form>
            <a href="inicioAdmin.jsp" style="margin-top: 2rem; display:flex; margin-left:39%;margin-right:39%;justify-content: center" class="admin-button">Voltar ao Painel</a>
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
