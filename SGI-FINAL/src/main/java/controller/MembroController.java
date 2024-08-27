package controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.EnderecoJavaBeans;
import model.FilialDAO;
import model.FilialJavaBeans;
import model.MembroDAO;
import model.MembroJavaBeans;;

/**
 * Servlet implementation class MembroController
 */
@WebServlet(urlPatterns = { "/MembroController", "/insert", "/pesquisaMembros", "/select", "/updateMembro",
		"/desativa" ,"/detalheMembro", "/cadastroMembros"})
@MultipartConfig(maxFileSize = 16177215) // 16 MB
public class MembroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	MembroDAO dao = new MembroDAO();
	MembroJavaBeans membro = new MembroJavaBeans();
	EnderecoJavaBeans endereco = new EnderecoJavaBeans();
	FilialDAO daoFilial = new FilialDAO();
	FilialJavaBeans filial = new FilialJavaBeans();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MembroController() {
		super();

	}

	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		if (action.equals("/MembroController")) {
			listaraniversariantes(request, response);
		} else if (action.equals("/insert")) {
			try {
				novoMembro(request, response);
			} catch (NoSuchAlgorithmException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("/pesquisaMembros")) {
			try {
				listarpesquisa(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("/select")) {
			try {
				listarMembro(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		} else if (action.equals("/updateMembro")) {
			try {
				updateMembro(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("/desativa")) {
			
			try {
				desativaMembro(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if(action.equals("/detalheMembro")){
			try {
				listarMembro(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(action.equals("/cadastroMembros")) {
			try {
				listarFilial(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		else {
			}

		dao.testeConexao();

	}

	// listar
	protected void listaraniversariantes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira receber os dados JavaBenas
		ArrayList<MembroJavaBeans> lista = dao.listarAniveriantes();

		request.setAttribute("aniversariantes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("aniversariantes.jsp");
		rd.forward(request, response);

	}
	
	protected void listarFilial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<FilialJavaBeans> filiais = daoFilial.listarFilial();
		request.setAttribute("filiais", filiais);
		RequestDispatcher rd = request.getRequestDispatcher("cadastroMembros.jsp");
		rd.forward(request, response);
		
	
	}

	protected void listarpesquisa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira receber os dados JavaBenas
		ArrayList<MembroJavaBeans> lista = dao.pesquisarMembro(request.getParameter("nomePesquisa"),
				request.getParameter("status"));

		request.setAttribute("pesquisaMembros", lista);
		RequestDispatcher rd = request.getRequestDispatcher("resultadoPesquisa.jsp");
		rd.forward(request, response);
	}

	// Editar membro

	protected void listarMembro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


			// Recebimento do ID do membro que será visualizado
			String membroId = request.getParameter("mbrid");
			System.out.println("a matricula " + membroId);
			int idmembro = Integer.parseInt(membroId);
			System.out.println("a matricula " + idmembro);
			membro = dao.selecaoMembro(idmembro);

			// Configurando os atributos para a visualização do detalhe
			request.setAttribute("membroDetalhe", membro);
			
			System.out.println(membro.getCargoNome());

			// Encaminhando para a página de detalhes do membro
			RequestDispatcher rd = request.getRequestDispatcher("detalheMembro.jsp");
			rd.forward(request, response);
		
	}

	// insert
	protected void novoMembro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {

		membro.setMbrmemnome(request.getParameter("mbrnome"));
		String cpfComMascara = request.getParameter("mbrcpf");
		membro.setMbrcpf(cpfComMascara.replaceAll("[^\\d]", ""));

		membro.setMbrmsuperior((Integer.parseInt(request.getParameter("mbrsuperior"))));

		membro.setMbrsedeid(Integer.parseInt(request.getParameter("mbrsede")));
		String rgComMascara = request.getParameter("mbridentidade");
		membro.setMbrnumero_identidade(rgComMascara.replaceAll("[^\\d]",""));
		membro.setMbremail(request.getParameter("mbremail"));
		String telefoneComMascara = request.getParameter("mbrtelefone");
		membro.setMbrtelefone(telefoneComMascara.replaceAll("[^\\d]",""));
		membro.setMbrfilial(Integer.parseInt(request.getParameter("mbrfilial")));

		String senha = request.getParameter("mbrsenha");

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(senha.getBytes());
			byte[] digest = md.digest();

			// Convertendo bytes para representação hexadecimal
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}

			String senhaCriptografada = sb.toString();

			// Definindo a senha criptografada no objeto membro
			membro.setMbrsenha(senhaCriptografada);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	

		String dateStr = request.getParameter("mbrdatanascimento");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			membro.setMbrata_nascimento(sdf.parse(dateStr));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		membro.setMbrcivilId(Integer.parseInt(request.getParameter("mbrestadocivil")));

		// Tratamento do Upload do Arquivo
		String msg = "Arquivo enviado com sucesso";
		String path = getServletContext().getRealPath("/img");

		try {
			for (Part part : request.getParts()) {
				if (part.getName().equals("mbrfoto")) {
					String caminho = path + File.separator + part.getSubmittedFileName();
					part.write(caminho);
					membro.setMbrfotoCaminho(caminho);
					System.out.println(msg);
				}
			}

		} catch (Exception e) {
			msg = "Erro ao salvar a imagem";
		}

		String mbrativoValue = request.getParameter("mbrativo");

		boolean isAtivo = "1".equals(mbrativoValue);
		membro.setCargo(Integer.parseInt(request.getParameter("cargo")));

		membro.setMbrativo(isAtivo);
		membro.setMbracesso(request.getParameter("mbracesso"));
		endereco.setEndrua(request.getParameter("endrua"));
		endereco.setEndnumero(request.getParameter("endnumero"));
		endereco.setEndbairro(request.getParameter("endbairro"));
		String cepComMascara = request.getParameter("endcep");
		endereco.setEndcep(cepComMascara.replaceAll("[^\\d]",""));
		endereco.setEndcidade(request.getParameter("endcidade"));
		endereco.setEndestado(request.getParameter("endestado"));
		membro.setMbrendereco(endereco);
		// Invocando o metodo inserir
		System.out.println("Nome: " + membro.getMbrmemnome());
		System.out.println("CPF: " + membro.getMbrcpf());
		
		// Redirecionar
		if(dao.inserirMembro(membro)) {
			response.sendRedirect("cadastroMembros?success=true");
		}else {
			response.sendRedirect("cadastroMembros?success=false");
		}

	

	}

	// Update de Membros
	protected void updateMembro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {

		System.out.println(request.getParameter("mbrid"));

		membro.setId_Membro(Integer.parseInt(request.getParameter("mbrid")));
		membro.setMbrmemnome(request.getParameter("mbrnome"));
		String cpfComMascara = request.getParameter("mbrcpf");
		membro.setMbrcpf(cpfComMascara.replaceAll("[^\\d]", ""));

		membro.setMbrmsuperior((Integer.parseInt(request.getParameter("mbrsuperior"))));

		membro.setMbrsedeid(Integer.parseInt(request.getParameter("mbrsede")));
		String rgComMascara = request.getParameter("mbridentidade");
		membro.setMbrnumero_identidade(rgComMascara.replaceAll("[^\\d]",""));
		membro.setMbremail(request.getParameter("mbremail"));
		String telefoneComMascara = request.getParameter("mbrtelefone");
		membro.setMbrtelefone(telefoneComMascara.replaceAll("[^\\d]",""));
		membro.setMbrfilial(Integer.parseInt(request.getParameter("mbrfilial")));
		

		String dateStr = request.getParameter("mbrdatanascimento");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			membro.setMbrata_nascimento(sdf.parse(dateStr));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		membro.setMbrcivilId(Integer.parseInt(request.getParameter("mbrestadocivil")));

		// Tratamento do Upload do Arquivo

		String mbrativoValue = request.getParameter("mbrativo");

		boolean isAtivo = "1".equals(mbrativoValue);
		membro.setMbrativo(isAtivo);
		membro.setCargo(Integer.parseInt(request.getParameter("cargo")));

		membro.setMbracesso(request.getParameter("mbracesso"));
		endereco.setEndrua(request.getParameter("endrua"));
		endereco.setEndnumero(request.getParameter("endnumero"));
		endereco.setEndbairro(request.getParameter("endbairro"));
		String cepComMascara = request.getParameter("endcep");
		endereco.setEndcep(cepComMascara.replaceAll("[^\\d]",""));
		endereco.setEndcidade(request.getParameter("endcidade"));
		endereco.setEndestado(request.getParameter("endestado"));
		membro.setMbrendereco(endereco);

		// Invocando o metodo inserir
		System.out.println("Nome: " + membro.getMbrmemnome());
		System.out.println("CPF: " + membro.getMbrcpf());
		

		// Tratamento do Upload do Arquivo
		String msg = "Arquivo enviado com sucesso";
		String path = getServletContext().getRealPath("/img");

		try {
			for (Part part : request.getParts()) {
				if (part.getName().equals("mbrfoto")) {
					String caminho = path + File.separator + part.getSubmittedFileName();
					part.write(caminho);
					membro.setMbrfotoCaminho(caminho);
					System.out.println(msg);
				}
			}

		} catch (Exception e) {
			msg = "Erro ao salvar a imagem";
		}

		

		// Redirecionar
		if(dao.updateMembro(membro)) {
			response.sendRedirect("pesquisaMembros.jsp?success=true");
		}else {
			response.sendRedirect("pesquisaMembros.jsp?success=false");
		}
	}

	// Desativar Membro

	protected void desativaMembro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		membro.setId_Membro(Integer.parseInt(request.getParameter("mbrid")));
		dao.desativaMembro(membro);
		response.sendRedirect("pesquisaMembros.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
