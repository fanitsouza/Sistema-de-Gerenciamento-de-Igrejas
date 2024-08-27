<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String nome = request.getParameter("mbrNome");
String id = request.getParameter("mbrID");
%>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SGI - Cadastro de Dízimo</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" href="./css/janela-modal-sucesso-erro.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
<script>
	$(document).ready(function() {
		$('#cpf').mask('000.000.000-00');
		$('#rg').mask('0000000-0');
		$('#telefone').mask('(00) 00000-0000');
		$('#cep').mask('00000-000');
	});
</script>
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
		<!------------------------------------------------------------------------OPÇÕES/HOME-CONTAINER-------------------------------------------------------------------------->
		<!------------------------------------------------------------------------------------------------------------------------------------------------------>

		<div class="j1">
			<ul class="opcoes">
				<li><a href="membroparadizimo.jsp">Cadastar Novo Dízimo</a></li>
				<li><a href="consultarDizimo.jsp">Consultar Dízimo</a></li>

			</ul>
		</div>

		<!------------------------------------------------------------------------HOME-CONTAINER-------------------------------------------------------------------------->
		<!------------------------------------------------------------------------------------------------------------------------------------------------------>
		<div class="formularios-container" id="form-normal">
			<div class="forms">
				<div class="form-title">
					<h2>Cadastro de Dízimo</h2>
				</div>
				<form method="get" name="frmDizimo" action="insertDizimo">
					<div class="form">
						<div class="input-box">
							<label>Nome</label> <input value="<%=nome%>" class="form-control"
								type="text" name="#" readonly>
						</div>
						<div class="input-box">
							<label>Matrícula do Membro</label> <input value="<%=id%>"
								class="form-control" type="number" name="dzombrid" readonly>
						</div>
						<input type="hidden" value="Dizimo" name="dzotipo">
						<div class="input-box">
							<label>Valor da Contribuição</label> <input class="form-control"
								type="number" name="dzovalor" step="0.01" min="0" required>
						</div>
						<div class="input-box">
							<label>Data da Contribuição</label> <input class="form_group"
								type="date" id="trfdata" name="dzodtcontribuicao" required>
						</div>
					</div>
			</div>

		</div>
		<div class="submit">
			<button>
				<i class="fa fa-floppy-o" aria-hidden="true"></i> Cadastrar
			</button>
		</div>
		</form>
	</div>






	<script
		src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
	<script src="my_chart.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.min.js"></script>
	<script src="./js/script.js"></script>
</body>



</html>