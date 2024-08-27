<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.FilialJavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.MembroDAO"%>
<%@ page import="model.MembroJavaBeans"%>
<%@ page import="model.FilialDAO"%>
<%
FilialDAO daoFilial = new FilialDAO();
ArrayList<FilialJavaBeans> filiais = daoFilial.listarFilial();
MembroDAO daoMembro = new MembroDAO();
ArrayList<MembroJavaBeans> membros = daoMembro.listarMembros();
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SGI - Edição de Membros</title>

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
					<li><a href="grupos.jsp"> <i class="fa fa-home"
					aria-hidden="true"></i> <span class="link-name"> Painel
						Grupos</span>
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
		<div class="formularios-container">
			<div class="forms">
				<div class="form-title">
					<h2>Editação de Membro</h2>
					<%
					MembroJavaBeans membro = (MembroJavaBeans) request.getAttribute("membroDetalhe");
					%>
				</div>
				<form method="post" enctype="multipart/form-data" name="frmMembro"
					action="updateMembro">
					<div class="form">
						<div class="input-box">
							<label>ID</label> <input required="" placeholder="Nome completo"
								type="text" name="mbrid" readonly
								value="<%=membro.getId_Membro()%>">
						</div>
						<div class="input-box">
							<label>Nome</label> <input required=""
								placeholder="Nome completo" type="text" name="mbrnome"
								value="<%=membro.getMbrmemnome()%>">
						</div>
						<div class="column">
							<div class="input-box">
								<label>CPF</label> <input required=""
									placeholder="000.000.000-00" type="text" id="cpf" name="mbrcpf"
									size="14" maxlength="14" value="<%=membro.getMbrcpf()%>"
									required>
							</div>
							<div class="input-box">
								<label>RG</label> <input required="" placeholder="000000-00"
									type="text" id="rg" size="9" maxlength="9" type="text"
									name="mbridentidade"
									value="<%=membro.getMbrnumero_identidade()%>">
							</div>
							<div class="input-box">
								<label>Data Nascimento</label> <input required=""
									placeholder="Enter birth date" type="date"
									name="mbrdatanascimento"
									value="<%=membro.getMbrata_nascimento()%>">
							</div>
						</div>

						<div class="column-ig">
							<label>Superior</label>
							<div class="select-box-ig">
								<select name="mbrsuperior" required>
									<option value="">Superior</option>
									<option value="0">Sem Superior</option>

									<%
									if (membros != null) {
										for (MembroJavaBeans membroLista : membros) {
									%>
									<option value="<%=membroLista.getId_Membro()%>"><%=membroLista.getMbrmemnome()%></option>
									<%
									}
									}
									%>
								</select>
							</div>

						</div>
						<div class="gen-box">
							<label>Estado Civil</label>
							<div class="gen-option">
								<div class="gen">
									<input checked="" name="mbrestadocivil" id="check-male"
										type="radio" value="1"> <label for="check-male">Solteiro(a)</label>
								</div>
								<div class="gen">
									<input name="mbrestadocivil" id="check-female" type="radio"
										value="2"> <label for="check-female">Divorciado(a)</label>
								</div>
								<div class="gen">
									<input name="mbrestadocivil" id="check-other" type="radio"
										value="3"> <label for="check-other">Viúvo(a)</label>
								</div>
								<div class="gen">
									<input name="mbrestadocivil" id="check-other" type="radio"
										value="4"> <label for="check-other">Casado(a)</label>
								</div>
							</div>
						</div>
						<div class="input-box address">
							<label>Email</label> <input required=""
								placeholder="Insira um Email" type="text" name="mbremail"
								value="<%=membro.getMbremail()%>">
						</div>
						<div class="input-box address">
							<label for="arquivo">Escolha uma imagem:</label> <input
								accept=".jpg, .jpeg, .png, .gif, .pdf" class="inpdddut"
								name="mbrfoto" id="arquivo" type="file"
								value="<%=membro.getMbrfotoCaminho()%>">>
						</div>
					</div>
			</div>
			<div class="forms-end">
				<div class="form-end-title">
					<h2>Endereço</h2>
				</div>
				<div class="form-end">
					<div class="input-box-end">
						<label>Rua</label> <input required="" placeholder="Rua"
							type="text" name="endrua"
							value="<%=membro.getMbrendereco().getEndrua()%>">
					</div>
					<div class="column-end">
						<div class="input-box-end">
							<label>Número</label> <input required="" placeholder="0000"
								type="number" name="endnumero"
								value="<%=membro.getMbrendereco().getEndnumero()%>">
						</div>
						<div class="input-box-end">
							<label>Bairro</label> <input required=""
								placeholder="Digite o nome do bairro" type="text"
								name="endbairro"
								value="<%=membro.getMbrendereco().getEndbairro()%>">
						</div>
						<div class="input-box-end">
							<label>CEP</label> <input required=""
								placeholder="Digite o nome do CEP" type="text" name="endcep"
								id="cep" size="9" maxlength="9"
								value="<%=membro.getMbrendereco().getEndcep()%>">
						</div>
						<div class="input-box-end">
							<label>Cidade</label> <input required=""
								placeholder="Digite o nome da Cidade" type="text"
								name="endcidade"
								value="<%=membro.getMbrendereco().getEndcidade()%>">
						</div>
						<div class="input-box-end">
							<label>Estado</label> <input required="" placeholder="Estado"
								type="text" name="endestado"
								value="<%=membro.getMbrendereco().getEndestado()%>">
						</div>
						<div class="input-box-end">
							<label>Telefone</label> <input required=""
								placeholder="(00) 00000-0000" type="text" name="mbrtelefone"
								id="telefone" size="15" maxlength="15"
								value="<%=membro.getMbrtelefone()%>">
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="form-info-ig">
			<div class="forms-ig">
				<div class="form-title-ig">
					<h2>Informações da Igreja</h2>
				</div>
				<div class="form-ig">

					<div class="column-ig">
						<div class="select-box-ig">
							<select name="mbrsede" required="">
								<option value="">Sede</option>
								<option value="1">Manaus</option>
							</select>
						</div>

					</div>
					<div class="column-ig">
						<div class="select-box-ig">
							<select name="mbrativo" required="">
								<option value="">Status</option>
								<option value="1">Ativo</option>
								<option value="0">Inativo</option>
							</select>
						</div>

					</div>
					<div class="column-ig">
						<div class="select-box-ig">
							<select name="mbrfilial" required="">
								<option hidden="">Filial</option>
								<%
								for (FilialJavaBeans filial : filiais) {
								%>
								<option value="<%=filial.getIdfilial()%>"><%=filial.getFilnome()%></option>
								<%
								}
								%>
							</select>
						</div>

					</div>


					<div class="column-ig">
						<div class="select-box-ig">
							<select name="mbracesso" required>
								<option value="">Nível de Acesso</option>
								<option value="Administrador">Administrador</option>
								<option value="Usuario">Usuário</option>
							</select>
						</div>

					</div>

					<div class="column-ig">
						<div class="select-box-ig">
							<select name="cargo" required>
								<option value="">Cargo</option>
								<option value="10">Membro Comum</option>
								<option value="1">Pastor</option>
								<option value="2">Presbitero</option>
								<option value="3">Secretaria</option>
								<option value="4">Diacono</option>
								<option value="5">Porteiro</option>
								<option value="6">Músico</option>
								<option value="7">Coral e Louvor</option>
								<option value="8">Oração e Intercessão</option>
								<option value="9">Sonoplastia</option>
							</select>
						</div>

					</div>
				</div>
			</div>
		</div>

		<div class="submit">
			<button>
				<i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar Edição
			</button>
			</button>
		</div>

		<script
			src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
		<script src="my_chart.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.min.js"></script>
		<script src="./js/script.js"></script>
</body>
</html>
