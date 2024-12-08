<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - Admin</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<!-- Cabeçalho -->
	<header>
		<div class="container">
			<div class="logo">
				<h1>
					<a href="#">PassagensFácil</a>
				</h1>
			</div>
			<nav>
				<ul>
					<li><a href="#">Sobre Nós</a></li>
					<li><a href="#">Ajuda</a></li>
					<li><a href="#">Contato</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<!-- Seção de Login -->
	<section class="hero">
		<div class="container">
			<h2>Login de Administrador</h2>
			<c:if test="${not empty successMessage}">
				<div class="sucess-message"
					style="color: green; margin-bottom: 1rem;">${successMessage}
				</div>
			</c:if>
			<c:if test="${not empty errorMessage}">
				<div class="error-message" style="color: red; margin-bottom: 1rem;">
					${errorMessage}</div>
			</c:if>
			<form class="search-form" action="processaLoginAdmin.do"
				method="post">
				<label for="nome">Nome de Usuário:</label> <input type="text"
					id="nome" name="nome" placeholder="Digite seu nome" required>

				<label for="senha">Senha:</label> <input type="password" id="senha"
					name="senha" placeholder="Digite sua senha" required>

				<button type="submit">Entrar</button>
			</form>
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
				<a href="#">Facebook</a> <a href="#">Instagram</a> <a href="#">Twitter</a>
			</div>
		</div>
	</footer>
</body>
</html>
