<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - PassagensFácil</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <div class="container">
            <div class="logo">
                <h1><a href="inicio.jsp">PassagensFácil</a></h1>
            </div>
        </div>
    </header>

    <section class="hero">
        <div class="container">
            <h2>Bem-vindo de volta!</h2>
            <form class="search-form" action="processaLogin.do" method="post">
                <label for="email">E-mail:</label>
                <input type="email" id="email" name="email" placeholder="Digite seu e-mail" required>

                <label for="password">Senha:</label>
                <input type="password" id="password" name="senha" placeholder="Digite sua senha" required>

                <button type="submit">Login</button>

                <p style="color: #fff; margin-top: 1rem;">Ainda não tem uma conta? 
                    <a href="register.jsp" style="color: #007bff; text-decoration: underline;">Cadastre-se aqui</a>.
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
