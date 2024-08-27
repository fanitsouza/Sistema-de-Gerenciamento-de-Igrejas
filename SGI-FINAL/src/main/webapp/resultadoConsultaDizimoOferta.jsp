<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.DizimosOferta"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<%
String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro",
		"Novembro", "Dezembro" };
%>

<%
int mes = (int) request.getAttribute("p_mes");
int ano = (int) request.getAttribute("p_ano");
String tipo = (String) request.getAttribute("p_tipo");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SGI - Resultado Consulta <%=tipo%></title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" href="./css/janela-modal-alerta.css">
<link rel="stylesheet" type="text/css" href="./css/tablelist.css">
<link rel="stylesheet" href="./css/cardDoz.css">

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
				<%
				if (tipo.equals("Oferta")) {
				%>
				<li><a href="membroparaoferta.jsp">Cadastar Novo Oferta</a></li>
				<li><a href="consultaOferta.jsp">Consultar Oferta</a></li>
				<%
				} else if (tipo.equals("Dizimo")) {
				%>
				<li><a href="membroparadizimo.jsp">Cadastar Novo Dízimo</a></li>
				<li><a href="consultarDizimo.jsp">Consultar Dízimo</a></li>
				<%
				}
				%>
				<li><a href="relatorioDZO?tipo=<%=tipo%>&mes=<%=mes%>&ano=<%=ano%>"><i class="fa fa-file-pdf-o" aria-hidden="true"></i> Emitir Relatório</a></li>

			</ul>
		</div>

		<!------------------------------------------------------------------------HOME-CONTAINER-------------------------------------------------------------------------->
		<!------------------------------------------------------------------------------------------------------------------------------------------------------>


		<%
		ArrayList<DizimosOferta> listaDizimosOfertas = (ArrayList<DizimosOferta>) request.getAttribute("dizimosOfertas");
		float total = (float) request.getAttribute("totalDizimosOfertas");
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		if (listaDizimosOfertas != null) {
		%>
	<div class="consultaDoz-container">
		<div class="cardDizOferta">

			<div class="title">
				<p class="title-text">
					Total de
					<%=tipo%>
					do Mês de
					<%=meses[mes - 1]%>
					de
					<%=ano%></p>
			</div>
			<div class="data">
				<p><%=nf.format(total)%></p>
			</div>
		</div>
</div>
		<div class="table-list-container">
			<div class="table-list">
				<div class="table__body">
					<table>
						<thead>
							<tr>
								<th>ID</th>
								<th>Nome do Membro</th>
								<th>Filial</th>
								<th>Data da Contribuição</th>
								<th>Tipo</th>
								<th>Valor</th>
								<th>Excluir</th>
							</tr>
						</thead>
						<tbody>
							<%
							
							for (DizimosOferta dizimoOferta : listaDizimosOfertas) {
								LocalDateTime data = LocalDateTime.parse(dizimoOferta.getDzodtcontribuicao() + " " + "00:00:00",
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
								SimpleDateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
								String dataFormatada = dataFormatter.format(dizimoOferta.getDzodtcontribuicao());
							%>
							<tr>
								<td><%=dizimoOferta.getIddizimooferta()%></td>
								<td><%=dizimoOferta.getDzomembronome()%></td>
								<td><%=dizimoOferta.getDzoFilialNome()%></td>
								<td><%=dataFormatada%></td>
								<td><%=dizimoOferta.getDzotipo()%></td>
								<td><%=nf.format(dizimoOferta.getDzovalor())%></td>
								<td>
									<button 
										onclick="abrirModalAlerta('<%=dizimoOferta.getIddizimooferta()%>', '<%=mes%>', '<%=ano%>', '<%=tipo%>')">
										<i class="fa fa-trash-o" aria-hidden="true"></i>
									</button>
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
		<script src="js/script-fecha-alerta.js"></script>
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
				<a class="btnConfirmar" id="confirma-exclusao">Confirmar</a>
			</div>
		</div>

	</div>
</div>

</html>
