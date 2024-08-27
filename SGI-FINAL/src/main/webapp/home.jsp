<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.MembroDAO" %>
<%@ page import="model.DizimosOfertaDAO" %>
<%@ page import="model.PatrimonioDAO" %>
<%@ page import="model.EventoDAO" %>
<%@ page import="model.MembroJavaBeans" %>
<%@ page import="model.EventoJavaBeans" %>
<%@ page import="controller.JSONGenerators" %>
<%
	MembroDAO daoMembro = new MembroDAO();
	DizimosOfertaDAO daoDizimosOferta = new DizimosOfertaDAO();
	PatrimonioDAO daoPatrimonio = new PatrimonioDAO();
	EventoDAO daoEvento = new EventoDAO();
	JSONGenerators json = new JSONGenerators();
	
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	SimpleDateFormat dataFormatter = new SimpleDateFormat("dd/MM");
	SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
	SimpleDateFormat dataDia = new SimpleDateFormat("dd");
	SimpleDateFormat dataMes = new SimpleDateFormat("MM");
	
	String quantidadeMembros = daoMembro.totalMembros();
	String totalDizimo = nf.format(daoDizimosOferta.totalDizimoOferta("Dizimo"));
	String totalOferta = nf.format(daoDizimosOferta.totalDizimoOferta("Oferta"));
	String totalPatrimonio = nf.format(daoPatrimonio.totalPatrimonio());
	
	ArrayList<MembroJavaBeans> membros = daoMembro.listarAniveriantes();
	ArrayList<EventoJavaBeans> eventos = daoEvento.listarEventoCard();
	String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro",
			"Novembro", "Dezembro"};
	

	


%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>SGI</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
        integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="./css/style_chart.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">

    </head>
    <body>

<!------------------------------------------------------------------------BARRA DE NAVEGAÇÃO-------------------------------------------------------------------------->
<!------------------------------------------------------------------------------------------------------------------------------------------------------>

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
					<a href="LogoutController">
						<div class="profile-details">
							<span class="icon"><i class="fa fa-sign-out" aria-hidden="true"></i></span>
							<span class="logout"> Logout</span>
						</div>
					</a>
			</li>
		</ul>
	</div>

<!------------------------------------------------------------------------MAIN-------------------------------------------------------------------------->
<!------------------------------------------------------------------------------------------------------------------------------------------------------>
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

<!------------------------------------------------------------------------CARDS GERAIS-------------------------------------------------------------------------->
<!------------------------------------------------------------------------------------------------------------------------------------------------------>

                <div class="cardBox">
                    <div class="card">
                        <div>
                            <div class="numbers"><%=quantidadeMembros %></div>
                            <div class="cardName">Membros</div>
                        </div>
                        <div class="iconBox">
                            <i class="fa fa-user" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>




<!------------------------------------------------------------------------PAINEL PRINCIPAL - ANIVERSARIANTES-------------------------------------------------------------------------->
<!------------------------------------------------------------------------------------------------------------------------------------------------------>

                <div class="details">
                    <div class="recentOrders">
                        <div class="cardHeader">
                            <h2>Aniversariantes do mês</h2>
                            <a href="MembroController" class="btn">Geral</a>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <td>Foto</td>
                                    <td>Data</td>
                                    <td>Nome</td>
                                </tr>
                            </thead>
                            <tbody>
                            <%if(membros != null){
                            	for(MembroJavaBeans membro : membros){
                            		String dataFormatada = dataFormatter.format(membro.getMbrata_nascimento());
                            	
                            	%>
                            
                                <tr>
                                    <td width="60px"><div class="imgbx"><img src="img/<%=membro.getMbrfotoCaminho()%>"></div></td>
                                    <td><%=dataFormatada%></td>
                                    <td><span><%=membro.getMbrmemnome()%></span></td>
                                </tr>
                              <%}
                            	}%>
                                
                            </tbody>
                        </table>
                    </div>

<!------------------------------------------------------------------------PAINEL PRINCIPAL - EVENTOS -------------------------------------------------------------------------->
<!------------------------------------------------------------------------------------------------------------------------------------------------------>

                    <div class="eventosProximos">
                        <h2>Próximos Eventos</h2>
                            <div class="parent">
                            <%if(eventos != null){
                            	
                            	for(EventoJavaBeans evento : eventos){
                            		
                            		int mes = Integer.parseInt(dataMes.format(evento.getEvedata()));
                            		String horaFormatada = shf.format(evento.getEvehora());
                            	
                            	%>
                            
                                <div class="card">
                                    <div class="content-box">
                                        <span class="card-title"><%=evento.getEvetitulo() %></span>
                                        <p class="card-content">
                                            <i class="fa fa-clock-o" aria-hidden="true"><%=horaFormatada %></i> 
                                        </p>
                                       
                                    </div>
                                    <div class="date-box">
                                        <span class="month"><%=meses[mes-1] %></span> 
                                        <span class="date"><%=dataDia.format(evento.getEvedata()) %></span>
                                    </div>
                                </div>
                              <%
                              		 }
                            	} 
                            %>
                            </div>
                    </div>

                </div>
            </div>
        </div>
		    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
        <script src="./js/my_chart.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.min.js"></script>
        <script src="./js/script.js"></script>
        <script src="./js/jquery-3.1.1.min.js"></script>
	
    </body>
</html>
