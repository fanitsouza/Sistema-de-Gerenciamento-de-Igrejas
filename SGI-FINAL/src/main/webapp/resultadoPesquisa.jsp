<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.FilialJavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.MembroDAO"%>
<%@ page import="model.MembroJavaBeans"%>
<%
ArrayList<FilialJavaBeans> filiais = (ArrayList<FilialJavaBeans>) request.getAttribute("filiais");
MembroDAO daoMembro = new MembroDAO();
ArrayList<MembroJavaBeans> membros = daoMembro.listarMembros();
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SGI - Lista de Membros</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" href="./css/janela-modal-alerta.css">
<link rel="stylesheet" type="text/css" href="./css/tablelist.css">
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
				<li><a href="cadastroMembros">Cadastar Novo Membro</a></li>
				<li><a href="pesquisaMembros.jsp">Pesquisar</a></li>

			</ul>
		</div>

		<!------------------------------------------------------------------------HOME-CONTAINER-------------------------------------------------------------------------->
		<!------------------------------------------------------------------------------------------------------------------------------------------------------>
		<%
		ArrayList<MembroJavaBeans> listaMembros = (ArrayList<MembroJavaBeans>) request.getAttribute("pesquisaMembros");
		%>
		<div class="table-list-container">

			<div class="table-list">
				<div class="table__body">

					<table>
						<thead>
							<tr>
								<th>ID</th>
								<th>Nome</th>
								<th>CPF</th>
								<th>Email</th>
								<th>Sede</th>
								<th>Filial</th>
								<th>Superior</th>
								<th>Cargo</th>
								<th>Editar</th>
								<th>Desligar</th>
							</tr>
						</thead>
						<%
						if (listaMembros != null) {
						%>
						<%
						for (int i = 0; i < listaMembros.size(); i++) {
						%>
						<%
						int mbrid = listaMembros.get(i).getId_Membro();
						%>
						<tbody>
							<tr>
								<td><%=listaMembros.get(i).getId_Membro()%></td>
								<td><%=listaMembros.get(i).getMbrmemnome()%></td>
								<td id="cpf"><%=listaMembros.get(i).getMbrcpf()%></td>
								<td><%=listaMembros.get(i).getMbremail()%></td>
								<td><%=listaMembros.get(i).getSedenome()%></td>
								<td><%=listaMembros.get(i).getFilialnome()%></td>
								<td><%=listaMembros.get(i).getSupnome()%></td>
								<td><%=listaMembros.get(i).getCargoNome() != null ? listaMembros.get(i).getCargoNome() : "Membro Comum"%></td>
								<td>
								<a
									href="detalheMembro?mbrid=<%=listaMembros.get(i).getId_Membro()%>">
									<i class="fa fa-pencil-square-o" aria-hidden="true"></i>

								</a>
								</td>
								<td><button
									onclick="abrirModalAlertaMembro(<%=listaMembros.get(i).getId_Membro()%>)">
										<i class="fa fa-trash-o" aria-hidden="true"></i></button>
								</td>
								
							</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>


		<script
			src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
		<script src="my_chart.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.min.js"></script>
		<script src="./js/script.js"></script>
		<script src="./js/abrir-janela-modal-membro.js"> </script>
</body>

<div class="janela-modal-alerta" id="janela-modal-alerta">
	<div class="modal-alerta">
		<button class="fechar" id="fechar">X</button>
		<div class="container-modal">
			<img class="icone" alt="alertaIcone"
				src="./assets/img/alertaIcone.png">
			<h1>Alerta!</h1>
			<p>Deseja realmente exlcuir esse registro permanentemente?</p>
			<div class="container-btn">
				<button class="btnCancelar" id="btn-fechar-alerta">Cancelar</button>
				<a class="btnConfirmar"
					id="confirma-exclusao">Confirmar</a>
			</div>
		</div>

	</div>
</div>

</html>
