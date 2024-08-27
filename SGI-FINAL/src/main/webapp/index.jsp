<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/login.css">
    <link rel="stylesheet" href="./css/janela-modal-sucesso-erro.css">
 
    <title>SGI - Sistema de Gestão de Igreja</title>
</head>

<body>
    <div class="container">
  <div class="container-login">
   
  
        <div class="left">
            
            <div class="nome-sistema">
                <h1>SGI</h1>
                <h3>Sistema de Gestão de Igreja</h3>
            </div>
            <form class="form" action = "LoginController" method = "post">
                <div class="input-block">
                    <input class="input" type="text" id="email" required="" name="email">
                    <label for="email">E-mail</label>
                </div>
                <div class="input-block">
                    <input class="input" type="password" id="pass" required="" name="senha">
                    <label for="pass">Senha</label>
                </div>
                <div class="input-block">
<span class="forgot"><a href="pesquisarUsuario.jsp">Esqueceu a senha?</a></span>
                <button>Acessar</button>
            </div>
            </form>
            
        </div>
        <div class = "circulo"></div>
        <div class="right">
             
            <div class="img">
                <img src="./assets/img/logo.png" alt="">
                
        </div>
        
    </div>

</div>
</div>
</body>
<%
String achouMembro = request.getParameter("error");
if (achouMembro != null) {
	if ("true".equals(achouMembro)) {
%>
<div class="janela-modal-erro" id="janela-modal-erro">
	<div class="modal-erro">
		<button class="fechar" id="fechar-erro">X</button>
			<div class="container-modal">
				<img class="icone"alt="sucessoIcon" src="./assets/img/erro.png">
			<div class ="erro-texto">
					<h1 >Erro!</h1>
					<p >Email ou senha inválidos!.</p>
			</div>
			</div>
	</div>
</div>
<%}
}%>

<script src="js/script-fechar.js"></script>
</html>