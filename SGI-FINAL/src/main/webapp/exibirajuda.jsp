<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.FilialJavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<FilialJavaBeans> filiais = (ArrayList<FilialJavaBeans>) request.getAttribute("filiais");
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

<link rel="stylesheet" href="./css/questionario.css">
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
	

		<!------------------------------------------------------------------------HOME-CONTAINER-------------------------------------------------------------------------->
		<!------------------------------------------------------------------------------------------------------------------------------------------------------>

        <div class="video-questionario-container" style="display: flex; flex-direction: column; align-items: center;" gap="10px;">
            <div class="video" id="video-container" style="width: 100%; max-width: 800px; margin-bottom: 20px;">
          
            </div>
    
    <div class="informacao" id="informacao">
      <div class="icon-container">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          stroke-width="0"
          fill="currentColor"
          stroke="currentColor"
          class="icon"
        >
          <path
            d="M13 7.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0Zm-3 3.75a.75.75 0 0 1 .75-.75h1.5a.75.75 0 0 1 .75.75v4.25h.75a.75.75 0 0 1 0 1.5h-3a.75.75 0 0 1 0-1.5h.75V12h-.75a.75.75 0 0 1-.75-.75Z"
          ></path>
          <path
            d="M12 1c6.075 0 11 4.925 11 11s-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1ZM2.5 12a9.5 9.5 0 0 0 9.5 9.5 9.5 9.5 0 0 0 9.5-9.5A9.5 9.5 0 0 0 12 2.5 9.5 9.5 0 0 0 2.5 12Z"
          ></path>
        </svg>
      </div>
      <div class="message-text-container">
        <p class="message-text">Atenção</p>
        <p class="sub-text" id="sub-text"></p>
      </div>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 15 15"
        stroke-width="0"
        fill="none"
        stroke="currentColor"
        class="cross-icon"
        id ="fechar"
      >
        <path
          fill="currentColor"
          d="M11.7816 4.03157C12.0062 3.80702 12.0062 3.44295 11.7816 3.2184C11.5571 2.99385 11.193 2.99385 10.9685 3.2184L7.50005 6.68682L4.03164 3.2184C3.80708 2.99385 3.44301 2.99385 3.21846 3.2184C2.99391 3.44295 2.99391 3.80702 3.21846 4.03157L6.68688 7.49999L3.21846 10.9684C2.99391 11.193 2.99391 11.557 3.21846 11.7816C3.44301 12.0061 3.80708 12.0061 4.03164 11.7816L7.50005 8.31316L10.9685 11.7816C11.193 12.0061 11.5571 12.0061 11.7816 11.7816C12.0062 11.557 12.0062 11.193 11.7816 10.9684L8.31322 7.49999L11.7816 4.03157Z"
          clip-rule="evenodd"
          fill-rule="evenodd"
        ></path>
      </svg>
    </div>
    
            <div class="questao1"  id="questao1">
            <div class="card-questão" style="width: 100%; max-width: 800px;">
                
                <div class="tools">
                    <div class="circle">
                        <span class="red box"></span>
                    </div>
                    <div class="circle">
                        <span class="yellow box"></span>
                    </div>
                    <div class="circle">
                        <span class="green box"></span>
                    </div>
                </div>
                <h3>Questionário de fixação: Membros!</h3>
                <div class="card-questão__content">
                    
                    
                    <form id="questionario">
                        
                        <!-- Pergunta 1 -->
                        <p>1) No caso do membro não possuir superior, como esse campo deve ser preenchido?</p>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Preencher com "0"
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Deixar vazio
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Com o prórprio nome do membro
                              </label>
                            
                        </div>
                    
                        <!-- Pergunta 2 -->
                        <p>2) Qual o a informação necessária para buscar um membro?</p>
    
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Número de CPF
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) E-mail
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Nome
                              </label>
                        </div>
                        
                        <!-- Pergunta 3 -->
                        <p>3) Uma vez desligado, o membro pode ser religado?</p>
                        
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Sim, mas refazendo o cadastro
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Sim, apenas atualizando o campo "Ativo"
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Não, não pode mais ser religado
                              </label>
                            
                        </div>
    
                            <button type="button" onclick="verificarRespostas(1)" class="button">
                            <span class="button-content">Verificar Respostas </span>
                            </button>
    
                
                    </form>
                    
                </div>
            </div>
        </div>
        <div class="questao2"  id="questao2">
            <div class="card-questão" style="width: 100%; max-width: 800px;">
                
                <div class="tools">
                    <div class="circle">
                        <span class="red box"></span>
                    </div>
                    <div class="circle">
                        <span class="yellow box"></span>
                    </div>
                    <div class="circle">
                        <span class="green box"></span>
                    </div>
                </div>
                <h3>Questionário de fixação: Tarefas!</h3>
                <div class="card-questão__content">
                    <form id="questionario">
                        <!-- Pergunta 1 -->
                        <p>1) Quais os status possíveis de uma tarefa?</p>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Agendado e Conluído
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Agendado, Atrasado e Cancelado
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Agendado, Conluído, Cancelado e Atrasado
                              </label>
                            
                        </div class="alternativa">
                    
                        <!-- Pergunta 2 -->
                        <p>2) Qual cor representa as tarefas agendadas sem atraso?</p>
    
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Verde
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Azul
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Amarelo
                              </label>
                            </div>
                        
    
                
                    
                        <!-- Pergunta 3 -->
                        <p>3) Como a listagem das Tarefas estar disposta ?</p>
                        
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Necessário pesquisar uma por uma
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) São todas Listadas, independente do status
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) As atividades agendadas e atrasadas são listadas na mesma tela,<br> quanto as outras possuem sua prórpria tela
                              </label>
                            
                        </div >
    
                            <button type="button" onclick="verificarRespostas(2)" class="button">
                            <span class="button-content">Verificar Respostas </span>
                            </button>
    
                
                    </form>
                    
                </div>
            </div>
            </div>
            <div class="questao3"  id="questao3">
    
            <div class="card-questão" style="width: 100%; max-width: 800px;">
                <div class="tools">
                    <div class="circle">
                        <span class="red box"></span>
                    </div>
                    <div class="circle">
                        <span class="yellow box"></span>
                    </div>
                    <div class="circle">
                        <span class="green box"></span>
                    </div>
                </div>
                <h3>Questionário de fixação: Contas!</h3>
                <div class="card-questão__content">
                    <form id="questionario">
                        <!-- Pergunta 1 -->
                        <p>1) Com relação ao fornecedor, qual o dado a ser preenchido?</p>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) CPF/CNPJ do fornecedor
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Nome do fornecedor
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Número de telefone
                              </label>
                            
                        </div>
                    
                        <!-- Pergunta 2 -->
                        <p>2) Como as contas são listadas ?</p>
    
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Por fornecedor
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Por filial
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Todas as contas são listadas
                              </label>
                            </div>
                        
    
                
                    
                        <!-- Pergunta 3 -->
                        <p>3) O que ocorre quando se confirma deleção de um registro de uma conta?</p>
                        
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) O Registro é apagado permanentemente
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Nada acontece
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Apenas some da lista, mas é possível recuperar depois
                              </label>
                            
                        </div>
    
                            <button type="button" onclick="verificarRespostas(3)" class="button">
                            <span class="button-content">Verificar Respostas </span>
                            </button>
    
                
                    </form>
                    
                </div>
            </div>
            </div>
            <div class="questao4"  id="questao4">
            <div class="card-questão" style="width: 100%; max-width: 800px;">
                <div class="tools">
                    <div class="circle">
                        <span class="red box"></span>
                    </div>
                    <div class="circle">
                        <span class="yellow box"></span>
                    </div>
                    <div class="circle">
                        <span class="green box"></span>
                    </div>
                </div>
                <h3>Questionário de fixação: Patrimonio'!</h3>
                <div class="card-questão__content">
                    <form id="questionario">
                        <!-- Pergunta 1 -->
                        <p>1) O que devo considerar como patrimônio?</p>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Objetos, ou bens, pertencentes a igreja
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Apenas dinheiro
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Materiais de limpeza
                              </label>
                            
                        </div>
                    
                        <!-- Pergunta 2 -->
                        <p>2) Em quais estados o patrimônio pode ser cadastrado ?</p>
    
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Apenas como novo
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Novo ou usado
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Novo, usado ou danificado
                              </label>
                            </div>
    
    
                
                    
                        <!-- Pergunta 3 -->
                        <p>3) Como é listado os patrimônios ?</p>
                        
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Por filial
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Por nome do patrimônio
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Por estado
                              </label>
                            
                        </div>
    
                            <button type="button" onclick="verificarRespostas(4)" class="button">
                            <span class="button-content">Verificar Respostas </span>
                            </button>
    
                
                    </form>
                    
                </div>
            </div>
            </div>
    
            <div class="questao5"  id="questao5">
    
            <div class="card-questão" style="width: 100%; max-width: 800px;" >
                <div class="tools">
                    <div class="circle">
                        <span class="red box"></span>
                    </div>
                    <div class="circle">
                        <span class="yellow box"></span>
                    </div>
                    <div class="circle">
                        <span class="green box"></span>
                    </div>
                </div>
                <h3>Questionário de fixação: Dízimos!</h3>
                <div class="card-questão__content">
                    <form id="questionario">
                        <!-- Pergunta 1 -->
                        <p>1) Qual o primeiro passo para cadastrado de dízimo?</p>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Infomrmar o valor do dízimo
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Infomrmar a data da contribuíção
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao1" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Pesquisar o membro pelo nome ou sobrenome
                              </label>
                            
                        </div>
                    
                        <!-- Pergunta 2 -->
                        <p>2) Como é feito a consulta dos registro de dízimos?</p>
    
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Por filial
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Pelo mês e ano que se quer consultar
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao2" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Apenas pelo ano
                              </label>
                            </div>	
                
    
                
                    
                        <!-- Pergunta 3 -->
                        <p>3) É possível emitir algum relatório?</p>
                        
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="a" />
                                <div class="cr-input"></div>
                                A) Sim, um relátorio com os registro da consulta realizada previamente
                              </label>
                        </div>
                        <div class="alternativa">
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="b" />
                                <div class="cr-input"></div>
                                B) Sim, um relatório de todos os registros existentes
                              </label>
                            
                        </div>
                        <div class="alternativa">
    
                            <label class="cr-wrapper">
                                <input name="questao3" type="radio" value="c" />
                                <div class="cr-input"></div>
                                C) Não, não possui essa funcionalidade
                              </label>
                            
                        </div >
    
                            <button type="button" onclick="verificarRespostas(5)" class="button">
                            <span class="button-content">Verificar Respostas </span>
                            </button>
    
                
                    </form>
                    
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
        <script src="./js/videos.js"></script>
	  <script src="./js/verificarRespostas.js"></script>
</body>

</html>