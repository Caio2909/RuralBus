<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registro - PassagensFácil</title>
<link rel="stylesheet" href="styles.css">
<script>
        function validarSenhas() {
            var senha = document.getElementById("password").value;
            var confirmacao = document.getElementById("confirmPassword").value;
            if (senha.trim() === "" || confirmacao.trim() === "") {
                alert("Por favor, preencha ambos os campos de senha.");
                return false;
            }
            if (senha.trim() !== confirmacao.trim()) {
                alert("As senhas não coincidem.");
                return false; 
            }
            return true;
        }     
        function formatarCPF(campo) {
          var cpf = campo.value.replace(/\D/g, '');
          if (cpf.length <= 11) {
            cpf = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4'); 
          }
          campo.value = cpf;
        }
    </script>
</head>
<body>
	<header>
		<div class="container">
			<div class="logo">
				<h1>
					<a href="inicio.jsp">PassagensFácil</a>
				</h1>
			</div>
		</div>
	</header>

	<section class="hero">
		<div class="container">
			<h2>Crie sua conta</h2>

			<form class="search-form" action="processaRegistro.do" method="post"
				onsubmit="return validarSenhas()">
				<c:if test="${not empty errorMessage}">
					<div class="error-message" style="color: red; margin-bottom: 1rem;">
						${errorMessage}</div>
				</c:if>
				<label for="name">Nome Completo:</label> 
				<input type="text" id="name" name="name" placeholder="Digite seu nome completo" required> 
				
				<label for="email">E-mail:</label> 
				<input type="email" id="email" name="email" placeholder="Digite seu e-mail" required> 
				
				<label for="cpf">CPF:</label>
				<input type="text" id="cpf" name="cpf" maxlength="14" oninput="formatarCPF(this)" placeholder="xxx.xxx.xxx-xx" /> 
				
				<label for="password">Senha:</label> 
				<input type="password" id="password" name="password" placeholder="Digite sua senha" required> 
				
				<label for="confirm-password">Confirme a Senha:</label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirme sua senha" required>

				<button type="submit">Registrar</button>
				<p style="color: #fff; margin-top: 1rem;">
					Já tem uma conta? 
					<a href="login.jsp"style="color: #007bff; text-decoration: underline;">Faça login aqui</a>.
				</p>
			</form>
		</div>
	</section>

	<footer>
		<div class="container">
			<p>&copy; 2024 PassagensFácil. Todos os direitos reservados.</p>
		</div>
	</footer>
</body>
</html>
