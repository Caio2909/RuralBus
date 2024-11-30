<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleção de Assento</title>
    <link rel="stylesheet" href="stylesCheckout.css">
    <link rel="stylesheet" href="styles.css">
</head>
<body><section class="checkout-page">
        <div class="container">
            <div class="checkout-layout">
                <!-- Setar link para voltar à seleção -->             
				<a href="Inicio.jsp" class="back-arrow">
                     Voltar para Seleção
                </a>
                <!-- Formulário de preenchimento de dados -->
                <div class="checkout-form">
                
                    <h2>Finalizar Compra</h2>
                    <form action="confirmarCompra.jsp" method="post">
                        <h3>Preencha seus dados:</h3>
                        <label for="nome">Nome Completo:</label>
                        <input type="text" id="nome" name="nome" placeholder="Digite seu nome" value="${usuarioLogado.nome}" required>

                        <label for="cpf">CPF:</label>
                        <input type="text" id="cpf" name="cpf" placeholder="Digite seu CPF" value="${usuarioLogado.CPF}" required>

                        <label for="email">E-mail:</label>
                        <input type="email" id="email" name="email"  placeholder="Digite seu e-mail" value="${usuarioLogado.email}" required>

                        <label for="telefone">Telefone:</label>
                        <input type="tel" id="telefone" name="telefone" placeholder="Digite seu telefone"  required>
                        	<h3>Escolha seu assento:</h3>
							       <!-- Formulário -->
							       <div class="checkout-form">
							            <h2>Escolha seu assento</h2> 
							                  <label for="seatNumber">Número do assento:</label>
							                    <select id="seatNumber" name="seatNumber" required>
							                    </select>							                  							          
							            </div>							
							            <div class="checkout-details">
							                <h2>Mapa do Ônibus</h2>
							                <img src="mapa_onibus.png" alt="Mapa de Assentos do Ônibus" style="width: 100%; border-radius: 10px;">
							            </div>							       									
							             <button type="submit">Confirmar Compra</button>
					</form>
				</div>

                <div class="checkout-details">
                    <h2>Detalhes da Viagem</h2>
                    <p><strong>Empresa:</strong> ${param.empresa}</p>
                    <p><strong>Horário:</strong> ${param.horario}</p>
                    <p><strong>Preço:</strong> R$ ${param.preco},00</p>
                </div>
            </div>
        </div>
    </section>
    <script src="seatSelection.js"></script>
</body>
</html>
