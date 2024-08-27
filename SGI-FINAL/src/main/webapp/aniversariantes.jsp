<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.MembroJavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<MembroJavaBeans> lista = (ArrayList<MembroJavaBeans>) request.getAttribute("aniversariantes");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SGI - Ajuda</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" href="./css/aniversario.css">

</head>

<body>
	<div class="container close">
		<div class="logo">
			<span class="icon"><img src="assets/img/logo.png"
				alt="Logo SGI"></span> <span class="title">SGI</span>
		</div>

		<ul class="nav-list">
			<li><a href="home.jsp"> <i class="fa fa-home"
					aria-hidden="true"></i> <span class="link-name"> Painel
						Principal</span>
			</a></li>
			
			<%
			if (session.getAttribute("nivel").equals("Administrador")) {
			%>
			<li><a href="grupos.jsp"> <i class="fa fa-users"
					aria-hidden="true"></i> <span class="link-name"> Grupos</span>
			</a></li>
			<li>
				<div class="icon-link">
					<a href="#"> <i class="fa fa-users" aria-hidden="true"></i> <span
						class="link-name"> Cadastro </span>
					</a> <i class="fa fa-caret-down arrow" aria-hidden="true"></i>
				</div>

				<ul class="sub-menu">
					<li><a href="#" class="link-name"></a></li>
					<li><a href="cadastroMembros">Membros</a></li>
					<li><a href="cadastro_eventos.jsp">Eventos</a></li>
					<li><a href="cadastroTarefa">Tarefas</a></li>
				</ul>
			</li>
			<%
			}
			%>

			<li>
				<div class="icon-link">
					<a href="#"> <i class="fa fa-calendar" aria-hidden="true"></i>
						<span class="link-name"> Eventos</span>
					</a> <i class="fa fa-caret-down arrow" aria-hidden="true"></i>
				</div>

				<ul class="sub-menu">
					<li><a href="#" class="link-name"></a></li>
					<li><a href="MembroController">Aniversariantes</a></li>
					<li><a href="eventos.jsp">Cultos</a></li>
				</ul>
			</li>

			<li>
				<div class="icon-link">
					<a href="#"> <i class="fa fa-line-chart" aria-hidden="true"></i>
						<span class="link-name"> Financeiro</span>
					</a> <i class="fa fa-caret-down arrow" aria-hidden="true"></i>
				</div>

				<ul class="sub-menu">
					<li><a href="#" class="link-name"></a></li>
					<li><a href="metricas.jsp">Métricas</a></li>
					<li><a href="CadastroContas">Contas</a></li>
					<li><a href="membroparadizimo.jsp">Dízimos</a></li>
					<li><a href="cadastroDoacoes">Doações</a></li>
					<li><a href="membroparaoferta.jsp">Ofertas</a></li>
					<li><a href="cadastroPatrimonio">Patrimônios</a></li>
				</ul>
			</li>

			<li><a href="ajuda.jsp"> <i class="fa fa-info-circle"
					aria-hidden="true"></i> <span class="link-name"> Ajuda</span>
			</a></li>

			<li>
				<div class="profile-details">
					<div class="profile-content">
						<img src="img/<%out.print(session.getAttribute("userphoto"));%>"
							alt="">
					</div>

					<div class="name-job">
						<div class="name">
							<%
							out.print(session.getAttribute("username"));
							%>
						</div>
					</div>
					<a href="LogoutController"> <i class="fa fa-sign-out"
						aria-hidden="true"></i></a>
				</div>
			</li>
		</ul>
	</div>

	<div class="home-container">
		<div class="home-content">
			<div class="ic">
				<i class="fa fa-bars" aria-hidden="true"></i>
			</div>

			<!--BARRA DE PESQUISA-->
			<div class="search">
				<label> <input type="text" placeholder="Buscar"> <i
					class="fa fa-search" aria-hidden="true"></i>
				</label>
			</div>

			<!--BARRA DE USUÁRIO-->
			<div class="user">
				<img src="img/<%out.print(session.getAttribute("userphoto"));%>">
			</div>
		</div>


		<!------------------------------------------------------------------------HOME-CONTAINER-------------------------------------------------------------------------->
		<!------------------------------------------------------------------------------------------------------------------------------------------------------>

		<div class="container-aniversariantes">
			<%
			if (lista == null) {
			%>
			<p>Não há aniversariantes</p>


			<%
			} else {
			for (int i = 0; i < lista.size(); i++) {
			%>


			<div class="results-summary-container">
				<div class="confetti">
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
					<div class="confetti-piece"></div>
				</div>
				<div class="results-summary-container__result">
					<div class="heading-tertiary">Feliz Aniversário!</div>
					<div class="result-box">
						<div class="heading-primary">
							<img class="imagem-foto"
								src="img/<%=lista.get(i).getMbrfotoCaminho()%>" alt="">
						</div>

					</div>
					<div class="result-text-box">
						<div class="heading-secondary">Parabéns!!!</div>
						<p class="heading-secondary">
							<%=lista.get(i).getMbrmemnome()%>
						</p>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>
		</div>

		<script
			src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
		<script src="my_chart.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.min.js"></script>
		<script src="./js/script.js"></script>
		<script src="./js/videos.js"></script>
		<script src="./js/verificarRespostas.js"></script>
</body>



</html>