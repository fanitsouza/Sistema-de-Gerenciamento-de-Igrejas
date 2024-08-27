package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContasDAO;
import model.ContasJavaBeans;
import model.FilialDAO;
import model.FilialJavaBeans;

//Padrões de Url que chama os apelidos das páginas e passa os parametros
@WebServlet(urlPatterns = { "/Controller", "/ContasMain", "/ContasInsert", "/ContasSelect", "/ContasUpdate", "/ContasDelete",
		"/CadastroContas"})
public class ContasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContasDAO dao = new ContasDAO();
	ContasJavaBeans conta = new ContasJavaBeans();
	FilialDAO daoFilial = new FilialDAO();
	FilialJavaBeans filial = new FilialJavaBeans();


	public ContasController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Fazer a requisição e chamar o método correto para cada página
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/ContasMain")) {
			contas(request, response);
		} else if (action.equals("/ContasInsert")) {
			novaConta(request, response);
		} else if (action.equals("/ContasSelect")) {
			listarConta(request, response);
		} else if (action.equals("/ContasUpdate")) {
			editarConta(request, response);
		} else if (action.equals("/ContasDelete")) {
			removerConta(request, response);
		}else if(action.equals("/CadastroContas")){ 
			listarFilial(request, response);
			
		}else {
			response.sendRedirect("index.html");
		}
	}

	// listar contas
	protected void contas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<ContasJavaBeans> lista = dao.listarContas();
		// Encaminhar a lista ao documento contas
		request.setAttribute("contas", lista);
		RequestDispatcher rd = request.getRequestDispatcher("listarcontas.jsp");
		rd.forward(request, response);
	}
	
	protected void listarFilial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<FilialJavaBeans> filiais = daoFilial.listarFilial();
		request.setAttribute("filiais", filiais);
		RequestDispatcher rd = request.getRequestDispatcher("cadastrocontas.jsp");
		rd.forward(request, response);
		
	
	}
	// Cadastrar contas
	protected void novaConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setar variaveis JavaBeans
		conta.setCntnomefornecedor(request.getParameter("nomefornecedor"));
		conta.setCntidfilial(request.getParameter("filial"));
		conta.setCntdescricaoconta(request.getParameter("descricao"));
		String dateStr = request.getParameter("vencimento");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conta.setCntdtvencimento(sdf.parse(dateStr));

		} catch (Exception e) {

		}
		conta.setCntvalor(Double.parseDouble(request.getParameter("valor")));
		conta.setCntstatuspagamento(request.getParameter("status"));
		System.out.println(conta.getCntnomefornecedor());

		// Invocar o método inserirConta passando o objeto conta
		if(dao.inserirConta(conta)) {
			response.sendRedirect("CadastroContas?success=true");
		}else {
			response.sendRedirect("CadastroContas?success=false");
		}

		// redirecionar para o documento 
		

	}

	// Editar Contas
	protected void listarConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do conta que será editado
		String idcontapagar = request.getParameter("idcontapagar");
		// Setar a variável JavaBeans
		conta.setIdcontapagar(idcontapagar);
		// Executar o método selecionar Conta(DAO)
		dao.selecionarConta(conta);
		// Setar os atributos do formulario com o conteúdo JavaBeans
		request.setAttribute("idcontapagar", conta.getIdcontapagar());
		request.setAttribute("nome", conta.getCntnomefornecedor());
		request.setAttribute("idfilial", conta.getCntidfilial());
		request.setAttribute("descricaoconta", conta.getCntdescricaoconta());
		request.setAttribute("vencimento", conta.getCntdtvencimento());
		request.setAttribute("valor", conta.getCntvalor());
		request.setAttribute("status", conta.getCntstatuspagamento());
		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editarconta.jsp");
		rd.forward(request, response);

		/*
		 * //teste de recebimento System.out.println(conta.getIdcontapagar());
		 * System.out.println(conta.getCntnomefornecedor());
		 * System.out.println(conta.getCntidfilial());
		 * System.out.println(conta.getCntdescricaoconta());
		 * System.out.println(conta.getCntdtvencimento());
		 * System.out.println(conta.getCntvalor());
		 * System.out.println(conta.getCntstatuspagamento());
		 */
	}

	protected void editarConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * System.out.println(request.getParameter("Idcontapagar"));
		 * System.out.println(request.getParameter("Cntnomefornecedor"));
		 * System.out.println(request.getParameter("Cntidfilial"));
		 * System.out.println(request.getParameter("Cntdescricaoconta"));
		 * System.out.println(request.getParameter("Cntdtvencimento"));
		 * System.out.println(request.getParameter("Cntvalor"));
		 * System.out.println(request.getParameter("Cntstatuspagamento"));
		 */
		// Setar as variaveis JavaBeans
		conta.setCntnomefornecedor(request.getParameter("nomefornecedor"));
		conta.setCntidfilial(request.getParameter("filial"));
		conta.setCntdescricaoconta(request.getParameter("descricao"));
		String dateStr = request.getParameter("vencimento");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conta.setCntdtvencimento(sdf.parse(dateStr));

		} catch (Exception e) {

		}
		conta.setCntvalor(Double.parseDouble(request.getParameter("valor")));
		conta.setCntstatuspagamento(request.getParameter("status"));
		System.out.println(conta.getCntnomefornecedor());
		// executar o método alterarConta
		dao.alterarConta(conta);
		// redirecionar para o documento contasapagar.jsp (atualizando alterações)
		response.sendRedirect("ContasMain");
	}

	// Remover conta
	protected void removerConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id da conta a ser excluida (validador.js)
		String idcontapagar = request.getParameter("idcontapagar");
		// setar a variavel idcontapagar JavaBeans
		conta.setIdcontapagar(idcontapagar);
		// executar um metodo deletarConta (DAO) passando o objeto contato
		dao.deletarConta(conta);
		// redirecionar para o documento contasapagar.jsp (atualizando alterações)
		response.sendRedirect("ContasMain");

	}

}
