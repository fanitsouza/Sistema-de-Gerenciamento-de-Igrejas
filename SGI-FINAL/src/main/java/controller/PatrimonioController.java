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

import model.ContasJavaBeans;
import model.FilialDAO;
import model.FilialJavaBeans;
import model.PatrimonioDAO;
import model.PatrimonioJavaBeans;

@WebServlet(urlPatterns = { "/ControllerPatrimonio", "/mainpatri", "/insertpatri", "/selectpatri", "/updatepatri", "/deletepatri",
		"/cadastroPatrimonio"})
public class PatrimonioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PatrimonioDAO dao = new PatrimonioDAO();
	PatrimonioJavaBeans patrimonio = new PatrimonioJavaBeans();
	ContasJavaBeans conta = new ContasJavaBeans();
	FilialDAO daoFilial = new FilialDAO();
	FilialJavaBeans filial = new FilialJavaBeans();

	public PatrimonioController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		
		System.out.println(action);
		if (action.equals("/mainpatri")) {
			patrimonio(request, response);
		}else if (action.equals("/insertpatri")) {
			try {
				novoPatrimonio(request, response);
				System.out.println(action);
			} catch (Exception e) {
				System.out.println(e);
			} 
		}else if (action.equals("/selectpatri")) {
			listarPatrimonio(request, response);
		}else if (action.equals("/updatepatri")) {
			editarPatrimonio(request, response);
		}else if (action.equals("/deletepatri")) {
			removerPatrimonio(request, response);
		}else if(action.equals("/cadastroPatrimonio")){
			listarFilial(request, response);
		}else {
		
			response.sendRedirect("indexpatri.html");
		}

	}
	
	protected void listarFilial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<FilialJavaBeans> filiais = daoFilial.listarFilial();
		request.setAttribute("filiais", filiais);
		RequestDispatcher rd = request.getRequestDispatcher("cadastropatrimonio.jsp");
		rd.forward(request, response);
		
	
	}
	//Listar Patrimonio
	protected void patrimonio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filial = Integer.parseInt(request.getParameter("filial"));
	
		//Criando um objeto que irá receber os dados JavaBeansPatri
		ArrayList<PatrimonioJavaBeans> lista = dao.listarPatrimonio(filial);
		//Encaminhar a lista ao documento registropatrimonio.jsp
		request.setAttribute("patrimonios", lista);
		RequestDispatcher rd = request.getRequestDispatcher("listarpatrimonio.jsp");
		rd.forward(request, response);
		/*for (int i = 0; i < lista.size(); i++) {
		System.out.println(lista.get(i).getIdpatrimonio());
		System.out.println(lista.get(i).getPtrnome());
		System.out.println(lista.get(i).getPtridfil());
		System.out.println(lista.get(i).getPtrdescricao());
		System.out.println(lista.get(i).getPtrvalor());
		System.out.println(lista.get(i).getPtrdataaquisicao());
		System.out.println(lista.get(i).getPtrestado());
		}*/
	}
		
	
	//Novo Patrimonio
	protected void novoPatrimonio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Setar as variáveis JavaBeansPatri
		patrimonio.setPtrnome(request.getParameter("ptrnome"));
		patrimonio.setPtridfil(request.getParameter("ptridfilfilial"));
		patrimonio.setPtrdescricao(request.getParameter("ptrdescricao"));
		patrimonio.setPtrvalor(Double.parseDouble(request.getParameter("ptrvalor")));
		String dateStr = request.getParameter("ptrdataaquisicao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			patrimonio.setPtrdataaquisicao(sdf.parse(dateStr));

		} catch (Exception e) {

		}
		patrimonio.setPtrestado(request.getParameter("ptrestado"));
		
		//Invocar o método inserirPatrimonio passando o objeto patrimonio
		
		if(dao.inserirpatrimonio(patrimonio)) {
			response.sendRedirect("cadastroPatrimonio?success=true");
		}else {
			response.sendRedirect("cadastroPatrimonio?success=false");
		}
		
	}
	
	//Editar Patrimonio
	protected void listarPatrimonio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idpatrimonio = request.getParameter("idpatrimonio");
		patrimonio.setIdpatrimonio(idpatrimonio);
		dao.selecionarPatrimonio(patrimonio);
		request.setAttribute("idpatrimonio", patrimonio.getIdpatrimonio());
		request.setAttribute("ptrnome", patrimonio.getPtrnome());
		request.setAttribute("ptridfil", patrimonio.getPtridfil());
		request.setAttribute("ptrdescricao", patrimonio.getPtrdescricao());
		request.setAttribute("ptrvalor", patrimonio.getPtrvalor());
		request.setAttribute("ptrdataaquisicao", patrimonio.getPtrdataaquisicao());
		request.setAttribute("ptrestado", patrimonio.getPtrestado());
		// Encaminhar ao documento editarpatrimonio.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editarpatrimonio.jsp");
		rd.forward(request, response);
		/*System.out.println(patrimonio.getIdpatrimonio());
		System.out.println(patrimonio.getPtrnome());
		System.out.println(patrimonio.getPtridfil());
		System.out.println(patrimonio.getPtrdescricao());
		System.out.println(patrimonio.getPtrvalor());
		System.out.println(patrimonio.getPtrdataaquisicao());
		System.out.println(patrimonio.getPtrestado());*/
	}
	protected void editarPatrimonio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		patrimonio.setIdpatrimonio(request.getParameter("idpatrimonio"));
		patrimonio.setPtrnome(request.getParameter("ptrnome"));
		patrimonio.setPtridfil(request.getParameter("ptridfil"));
		patrimonio.setPtrdescricao(request.getParameter("ptrdescricao"));
		patrimonio.setPtrvalor(Double.parseDouble(request.getParameter("ptrvalor")));
		String dateStr = request.getParameter("ptrdataaquisicao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			patrimonio.setPtrdataaquisicao(sdf.parse(dateStr));

		} catch (Exception e) {

		}
		patrimonio.setPtrestado(request.getParameter("ptrestado"));
		
		boolean aletracaoResposta = dao.alterarpatrimonio(patrimonio); 
		if(aletracaoResposta) {
			response.sendRedirect("mainpatri?filial="+patrimonio.getPtridfil()+"&success=true");
			
		}else {
			response.sendRedirect("mainpatri?filial="+patrimonio.getPtridfil()+"&success=false");
		}
		


	}
	
	//Remover patrimonio
	protected void removerPatrimonio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//recebimento do id do patrimonio a ser removido
		String idpatrimonio = request.getParameter("idpatrimonio");
		PatrimonioJavaBeans patrimonio = new PatrimonioJavaBeans();
		patrimonio.setIdpatrimonio(idpatrimonio);
		patrimonio = dao.selecionarPatrimonio(patrimonio);
		String idfilial = patrimonio.getPtridfil();
		//setar a variavel idpatrimonio JavabeansPatri
		patrimonio.setIdpatrimonio(idpatrimonio);
		//executar o metodo deletarPatrimonio (DAOPATRI) passando o objeto contato
		dao.deletarPatrimonio(patrimonio);
		//redirecionar para o documento registropatrimonio.jsp atualizando as alterações
		response.sendRedirect("mainpatri?filial="+idfilial);
		
		
	}
	
}
