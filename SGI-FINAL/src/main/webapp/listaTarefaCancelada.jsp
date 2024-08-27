<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.TarefasJavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SGI - Lista de Tarefas Canceladas</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" href="./css/janela-modal-sucesso-erro.css">
<link rel="stylesheet" type="text/css" href="./css/tablelist.css">
<link href="css/cardcss.css" rel="stylesheet">


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
            <li><a href="cadastroTarefa">Cadastar Nova Tarefa</a></li>
            <li><a href="listarTarefas">Tarefas Agendadas</a></li>
            <li><a href="listarTarefasCanceladas">Tarefas Canceladas</a></li>
            <li><a href="listarTarefasConcluida">Tarefas Concluídas</a></li>

        </ul>
    </div>

		<!------------------------------------------------------------------------HOME-CONTAINER-------------------------------------------------------------------------->
		<!------------------------------------------------------------------------------------------------------------------------------------------------------>
        <div class="container-tarefa">
		<div class="card_padding">
			<div class="card_legenda">
				<div class="legenda">Legenda</div>
				<div class="paleta">

					<div class="cores cor1">
						<div class="div1">
							<span class="func">Conluído</span>
						</div>


					</div>
					<div class="cores cor2">
						<div class="div3"></div>
						<div class="div1">
							<span class="func">Agendado</span>
						</div>


					</div>
					<div class="cores cor3">
						<div class="div5"></div>
						<div class="div1">
							<span class="func">Cancelado</span>
						</div>


					</div>
					<div class="cores cor4">
						<div class="div7"></div>
						<div class="div1">
							<span class="func">Atrasado</span>
						</div>


					</div>
				</div>
			</div>
		</div>
        
        <div class="table-list-container">
            <div class="table-list">
                <div class="table__body">
                    <table class="table table-hover text-center" id="tabela">
                        <thead>
                            <tr>
                                <th>Data</th>
                                <th>Hora</th>
                                <th>Filial</th>
                                <th>Título</th>
                                <th>Descrição</th>
                                <th>Responsável</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <%
                            ArrayList<TarefasJavaBeans> lista = (ArrayList<TarefasJavaBeans>) request.getAttribute("tarefasCancelada");
                                if (lista != null) {
						%>
                        <tbody>
                            <%
                            for (int i = 0; i < lista.size(); i++) {
                                LocalDateTime dataTarefa = LocalDateTime.parse(lista.get(i).getTarefadata() + " " + lista.get(i).getTarefahora(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
                                LocalDateTime dataAtual = LocalDateTime.now();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
                                String dataFormatada = sdf.format(lista.get(i).getTarefadata());
                                String horaFormatada = shf.format(lista.get(i).getTarefahora());
    
                            %>
                            <tr id="linhaCancelada">
                                <td><%=dataFormatada%></td>
                                <td><%=horaFormatada%></td>
                                <td><%=lista.get(i).getTrafilnome()%></td>
                                <td><%=lista.get(i).getTarefatitulo()%></td>
                                <td><%=lista.get(i).getTarefadesc()%></td>
                                <td><%=lista.get(i).getTrfmbrnome()%></td>
                                <td><a
									href="detalheTarefa?trfid=<%=lista.get(i).getTarefaid()%>">
										<i class="fa fa-pencil-square-o" aria-hidden="true"></i> <%=lista.get(i).getStatus()%></a></td>
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
        </div>
        


		<script
			src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
		<script src="my_chart.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.min.js"></script>
		<script src="./js/script.js"></script>

</body>

<%
String achouMembro = request.getParameter("success");
if (achouMembro != null) {
	if ("true".equals(achouMembro)) {
%>
<div class="janela-modal-sucesso" id="janela-modal-sucesso">
	<div class="modal-sucesso">
		<button class="fechar" id="fechar-sucesso">X</button>
		<div class="container-modal">
			<img class="icone" alt="sucessoIcon" src="./assets/img/sucesso.png">
			<h1>Sucesso!</h1>
			<p>O cadastro do tarefa atualiza realizado com sucesso.</p>
		</div>
	</div>
</div>
<%
} else if ("false".equals(achouMembro)) {
%>
<div class="janela-modal-erro" id="janela-modal-erro">
	<div class="modal-erro">
		<button class="fechar" id="fechar-erro">X</button>
		<div class="container-modal">
			<img class="icone" alt="sucessoIcon" src="./assets/img/erro.png">
			<h1>Erro!</h1>
			<p>Erro ao atualiza  tarefa!</p>
		</div>
	</div>
</div>
<%
}
}
%>
<script src="js/script-fechar.js"></script>
</html>
