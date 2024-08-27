package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import model.DoacoesDAO;
import model.DoacoesJavaBeans;
import model.FilialDAO;
import model.FilialJavaBeans;

@WebServlet(urlPatterns = { "/ControllerDoacoes", "/maindoacao", "/insertdoacao", "/selectdoacao", "/updatedoacao",
		"/deletedoacao", "/reportdoacao", "/cadastroDoacoes"})
public class ControllerDoacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DoacoesDAO dao = new DoacoesDAO();
	DoacoesJavaBeans doacao = new DoacoesJavaBeans();
	FilialDAO daoFilial = new FilialDAO();
	FilialJavaBeans filial = new FilialJavaBeans();

	public ControllerDoacao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getServletPath();

		System.out.println(action);
		if (action.equals("/maindoacao")) {
			doacao(request, response);
		} else if (action.equals("/insertdoacao")) {
			try {
				novaDoacao(request, response);
				System.out.println(action);
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (action.equals("/selectdoacao")) {
			listarDoacao(request, response);
		} else if (action.equals("/updatedoacao")) {
			editarDoacao(request, response);
		} else if (action.equals("/deletedoacao")) {
			removerDoacao(request, response);
		} else if (action.equals("/reportdoacao")) {
			comprovanteDoacao(request, response);
		} else if(action.equals("/cadastroDoacoes")) {
			listarFilial(request, response);
		}else {
		
			response.sendRedirect("cadastrodoacoes.jsp");
		}
	}

	// Listar Doações cadastradas
	protected void doacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int filial = Integer.parseInt(request.getParameter("doaidfilial"));
		System.out.println(filial);

		// Criando um objeto que irá receber os dados JavaBeansDoacoes
		ArrayList<DoacoesJavaBeans> lista = dao.listarDoacao(filial);
		request.setAttribute("doacao", lista);
		RequestDispatcher rd = request.getRequestDispatcher("listardoacoes.jsp");
		rd.forward(request, response);
	}
	protected void listarFilial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<FilialJavaBeans> filiais = daoFilial.listarFilial();
		request.setAttribute("filiais", filiais);
		RequestDispatcher rd = request.getRequestDispatcher("cadastrodoacoes.jsp");
		rd.forward(request, response);
		
	
	}

	// NOVA DOAÇÃO
	protected void novaDoacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setar as variáveis JavaBeansDoacao
		doacao.setDoanomedoador(request.getParameter("doanomedoador"));
		doacao.setDoamembroid(request.getParameter("doamembroid"));
		doacao.setDoadescricao(request.getParameter("doadescricao"));
		String dateStr = request.getParameter("doadtdoacao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			doacao.setDoadtdoacao(sdf.parse(dateStr));

		} catch (Exception e) {

		}
		doacao.setDoavalor(Double.parseDouble(request.getParameter("doavalor")));
		doacao.setStatus(request.getParameter("status"));
		doacao.setDoaidfilial(request.getParameter("doaidfilial"));

		// Invocar o método inserirDoacao passando o objeto doacao
		if (dao.inserirdoacao(doacao)) {
			// redirecionar para o documento com parametro para exibir tela de cadastro
			// realizado
			response.sendRedirect("cadastroDoacoes?success=true");

		} else {
			// redirecionar para o documento com parametro para exibir tela de falha no
			// cadastro
			response.sendRedirect("cadastroDoacoes?success=false");

		}

	}

	// EDITAR Doacao
	protected void listarDoacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String iddoacao = request.getParameter("iddoacao");
		System.out.println(iddoacao);

		doacao = dao.selecionarDoacao(iddoacao);
		request.setAttribute("iddoacao", doacao.getIddoacao());
		request.setAttribute("doanomedoador", doacao.getDoanomedoador());
		request.setAttribute("doamembroid", doacao.getDoamembroid());
		request.setAttribute("doadescricao", doacao.getDoadescricao());
		request.setAttribute("doadtdoacao", doacao.getDoadtdoacao());
		request.setAttribute("doavalor", doacao.getDoavalor());
		request.setAttribute("status", doacao.getStatus());
		request.setAttribute("doaidfilial", doacao.getDoaidfilial());
		System.out.println();

		RequestDispatcher rd = request.getRequestDispatcher("editardoacoes.jsp");
		rd.forward(request, response);
	}

	protected void editarDoacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doacao.setIddoacao(request.getParameter("iddoacao"));
		doacao.setDoanomedoador(request.getParameter("doanomedoador"));
		doacao.setDoamembroid(request.getParameter("doamembroid"));
		doacao.setDoadescricao(request.getParameter("doadescricao"));
		String dateStr = request.getParameter("doadtdoacao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			doacao.setDoadtdoacao(sdf.parse(dateStr));

		} catch (Exception e) {

		}
		doacao.setDoavalor(Double.parseDouble(request.getParameter("doavalor")));
		doacao.setStatus(request.getParameter("status"));
		doacao.setDoaidfilial(request.getParameter("doaidfilial"));

		if (dao.alterarDoacao(doacao)) {
			response.sendRedirect("cadastroDoacoes?success=true-edit");
		} else {
			response.sendRedirect("cadastroDoacoes?success=false-edit");
		}

		System.out.println("Matricula do membro na edicao: " + doacao.getDoamembroid());
		System.out.println("doaidfilial: " + doacao.getDoaidfilial());
	}

	// REMOVER DOAÇÃO
	protected void removerDoacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do patrimonio a ser removido
		String iddoacao = request.getParameter("iddoacao");
		System.out.println("ID para remoção: " + iddoacao);
		// setar a variavel idpatrimonio JavabeansDoacao
		doacao.setIddoacao(iddoacao);
		// executar o metodo deletarDoacao (DAODOACAO) passando o objeto doacao
		dao.deletarDoacao(doacao);
		// redirecionar para o documento atualizando as alterações
		response.sendRedirect("cadastrodoacoes.jsp");
	}

	// COMPROVANTE DOAÇÃO
	protected void comprovanteDoacao(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    // Criando um objeto de documento com margens personalizadas
	    Document documento = new Document(PageSize.A4, 50, 50, 50, 50); // Margens: esquerda, direita, superior, inferior
	    try {
	        // Definindo o tipo de conteúdo como PDF
	        response.setContentType("application/pdf");
	        // Definindo o cabeçalho para o nome do arquivo PDF
	        response.addHeader("Content-Disposition", "attachment; filename=comprovante.pdf");
	        // Inicializando o PdfWriter com o OutputStream da resposta
	        PdfWriter writer = PdfWriter.getInstance(documento, response.getOutputStream());

	        // Adicionando numeração de páginas
	        writer.setPageEvent(new PdfPageEventHelper() {
	            public void onEndPage(PdfWriter writer, Document document) {
	                PdfContentByte cb = writer.getDirectContent();
	                Phrase header = new Phrase("Comprovante de Doação", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
	                Phrase footer = new Phrase("Página " + writer.getPageNumber(), FontFactory.getFont(FontFactory.HELVETICA, 8));
	                
	                // Adicionando cabeçalho
	                ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header,
	                        (document.right() - document.left()) / 2 + document.leftMargin(),
	                        document.top() + 10, 0);

	                // Adicionando rodapé
	                ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,
	                        (document.right() - document.left()) / 2 + document.leftMargin(),
	                        document.bottom() - 10, 0);
	            }
	        });

	        // Abrindo o documento para adicionar conteúdo
	        documento.open();
	        String path = request.getServletContext().getRealPath("/assets/img/");
	        Image logo = Image.getInstance(path + "logo.png");
	        Image logo_2 = Image.getInstance(path + "SGI.png");

	        // Redimensionando as imagens
	        logo.scaleToFit(100, 100);
	        logo_2.scaleToFit(100, 100);

	        // Criando tabela para os logos
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(100);
	        table.setWidths(new float[]{2, 1}); 

	        PdfPCell cell1 = new PdfPCell(logo);
	        cell1.setBorder(PdfPCell.NO_BORDER); 
	        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

	        PdfPCell cell2 = new PdfPCell(logo_2);
	        cell2.setBorder(PdfPCell.NO_BORDER); 
	        cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

	        table.addCell(cell1);
	        table.addCell(cell2);

	        documento.add(table);

	        // Adicionando data de emissão
	        Date dataAtual = new Date();
	        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	        Paragraph dataEmissao = new Paragraph("Data de Emissão: " + formatoData.format(dataAtual));
	        dataEmissao.setAlignment(Element.ALIGN_RIGHT);
	        documento.add(dataEmissao);

	
	        documento.add(new Paragraph(" "));

	        // Criando e configurando a tabela de informações
	        PdfPTable tabela = new PdfPTable(7);
	        tabela.setWidthPercentage(100); // Definindo a largura da tabela como 100% da página
	        tabela.setSpacingBefore(10f);
	        tabela.setSpacingAfter(10f);
	        tabela.setWidths(new float[]{2, 2, 3, 2, 2, 2, 2}); // Definindo as larguras das colunas
	        
	        // Cabeçalho da tabela
	        PdfPCell col1 = new PdfPCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        PdfPCell col2 = new PdfPCell(new Paragraph("Cod Membro", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        PdfPCell col3 = new PdfPCell(new Paragraph("Descrição", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        PdfPCell col4 = new PdfPCell(new Paragraph("Data", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        PdfPCell col5 = new PdfPCell(new Paragraph("Valor", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        PdfPCell col6 = new PdfPCell(new Paragraph("Status", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        PdfPCell col7 = new PdfPCell(new Paragraph("Filial", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));

	        // Centralizando o texto no cabeçalho e aplicando cor de fundo
	        PdfPCell[] cells = {col1, col2, col3, col4, col5, col6, col7};
	        for (PdfPCell cell : cells) {
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        }

	        tabela.addCell(col1);
	        tabela.addCell(col2);
	        tabela.addCell(col3);
	        tabela.addCell(col4);
	        tabela.addCell(col5);
	        tabela.addCell(col6);
	        tabela.addCell(col7);

	        // Populando a tabela com dados
	        String iddoacao = request.getParameter("iddoacao");
	        DoacoesJavaBeans doacao = dao.selecionarDoacao(iddoacao);
	        tabela.addCell(doacao.getDoanomedoador());
	        tabela.addCell(doacao.getDoamembroid());
	        tabela.addCell(doacao.getDoadescricao());
	        tabela.addCell(new SimpleDateFormat("dd/MM/yyyy").format(doacao.getDoadtdoacao()));
	        tabela.addCell(String.valueOf(doacao.getDoavalor()));
	        tabela.addCell(doacao.getStatus());
	        tabela.addCell(doacao.getDoaidfilial());

	        documento.add(tabela);
	        
	        documento.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o comprovante");
	    } finally {
	        if (documento.isOpen()) {
	            documento.close();
	        }
	    }
	}


	
}
