package controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DizimosOferta;
import model.DizimosOfertaDAO;
import model.MembroDAO;
import model.MembroJavaBeans;

/**
 * Servlet implementation class DizimoOfertaController
 */
@WebServlet(urlPatterns = { "/DizimoOfertaController", "/insertDizimo", "/insertOferta", "/consultaDizimo",
		"/desativaDizOferta", "/pequisaMembroDizimo", "/pesquisaMembroOferta", "/relatorioDZO" })
public class DizimoOfertaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DizimosOfertaDAO dao = new DizimosOfertaDAO();
	private MembroDAO mbrdao = new MembroDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DizimoOfertaController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getServletPath();

		if (action.equals("/insertDizimo")) {
			try {
				novoDizimo(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("/insertOferta")) {
			try {
				novaOferta(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (action.equals("/consultaDizimo")) {
			try {
				consultaDizimoOferta(request, response);
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (action.equals("/desativaDizOferta")) {
			try {
				excluirDizimoOferta(request, response);
			} catch (Exception e) {
				System.out.println(e);
			}

		} else if (action.equals("/pequisaMembroDizimo")) {
			try {
				listarpesquisaDizimo(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (action.equals("/pesquisaMembroOferta")) {
			try {
				listarpesquisaOferta(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if(action.equals("/relatorioDZO")) {
			try {
				relatorioDizimoOfertas(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	protected void novoDizimo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DizimosOferta dizimoOferta = new DizimosOferta();

		dizimoOferta.setDzombrid(Integer.parseInt(request.getParameter("dzombrid")));
		dizimoOferta.setDzotipo(request.getParameter("dzotipo"));
		dizimoOferta.setDzovalor(Float.parseFloat(request.getParameter("dzovalor")));

		String dateString = request.getParameter("dzodtcontribuicao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dizimoOferta.setDzodtcontribuicao(sdf.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Data inválida.");
			RequestDispatcher rd = request.getRequestDispatcher("membroparadizimo.jsp");
			rd.forward(request, response);
			return;
		}

		if (mbrdao.achaMembro(dizimoOferta.getDzombrid())) {
			dao.insereContribuicao(dizimoOferta);
			response.sendRedirect("membroparadizimo.jsp?success=true");
		} else {
			response.sendRedirect("membroparadizimo.jsp?success=false");
		}
	}

	protected void novaOferta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DizimosOferta dizimoOferta = new DizimosOferta();

		dizimoOferta.setDzombrid(Integer.parseInt(request.getParameter("dzombrid")));
		dizimoOferta.setDzotipo(request.getParameter("dzotipo"));
		dizimoOferta.setDzovalor(Float.parseFloat(request.getParameter("dzovalor")));

		String dateString = request.getParameter("dzodtcontribuicao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dizimoOferta.setDzodtcontribuicao(sdf.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Data inválida.");
			RequestDispatcher rd = request.getRequestDispatcher("membroparaoferta.jsp");
			rd.forward(request, response);
			return;
		}

		if (mbrdao.achaMembro(dizimoOferta.getDzombrid())) {
			dao.insereContribuicao(dizimoOferta);
			response.sendRedirect("membroparaoferta.jsp?success=true");
		} else {
			response.sendRedirect("membroparaoferta.jsp?success=false");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected float totalDizimoOferta(ArrayList<DizimosOferta> dizimosOfertas) {
		float dizimosOfertasTotal = 0;

		if (dizimosOfertas != null) {

			for (DizimosOferta dizmoOferta : dizimosOfertas) {
				dizimosOfertasTotal += dizmoOferta.getDzovalor();
			}

			return dizimosOfertasTotal;

		} else {
			return dizimosOfertasTotal;
		}
	}

	protected void consultaDizimoOferta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int mes = Integer.parseInt(request.getParameter("mes"));
		int ano = Integer.parseInt(request.getParameter("ano"));
		String tipo = request.getParameter("tipo");

		ArrayList<DizimosOferta> dizimosOfertas = dao.consultaDizimoOferta(mes, ano, tipo);

		request.setAttribute("dizimosOfertas", dizimosOfertas);
		request.setAttribute("totalDizimosOfertas", totalDizimoOferta(dizimosOfertas));
		request.setAttribute("p_mes", mes);
		request.setAttribute("p_ano", ano);
		request.setAttribute("p_tipo", tipo);
		System.out.println("mes: " + mes + " ano: " + ano + " tipo:" + tipo);
		RequestDispatcher rd = request.getRequestDispatcher("resultadoConsultaDizimoOferta.jsp");
		rd.forward(request, response);
	}

	protected void excluirDizimoOferta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int dzoid = Integer.parseInt(request.getParameter("dzoid"));
		String mes = request.getParameter("p_excluir_mes");
		String ano = request.getParameter("p_excluir_ano");
		String tipo = request.getParameter("p_excluir_tipo");
		System.out.println("Antes de excluir mes: " + mes + " ano: " + ano + "tipo: " + tipo);
		System.out.println(dzoid);

		try {
			dao.excluirRegistroDizOferta(dzoid);

			response.sendRedirect("consultaDizimo?mes=" + mes + "&ano=" + ano + "&tipo=" + tipo);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected ArrayList<MembroJavaBeans> listarpesquisa(String nome, String status) {
		ArrayList<MembroJavaBeans> lista = mbrdao.pesquisarMembro(nome, status);
		if (lista != null) {
			return lista;
		} else {
			return null;
		}
	}

	protected void listarpesquisaDizimo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira receber os dados JavaBenas

		request.setAttribute("pesquisaMembros",
				listarpesquisa(request.getParameter("nomePesquisa"), request.getParameter("status")));
		RequestDispatcher rd = request.getRequestDispatcher("resultamembrodizimo.jsp");
		rd.forward(request, response);
	}

	protected void listarpesquisaOferta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira receber os dados JavaBenas

		request.setAttribute("pesquisaMembros",
				listarpesquisa(request.getParameter("nomePesquisa"), request.getParameter("status")));
		RequestDispatcher rd = request.getRequestDispatcher("resultamembrooferta.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// COMPROVANTE Dizimo ou Oferta
	protected void relatorioDizimoOfertas(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	    String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
	            "Outubro", "Novembro", "Dezembro" };
	    int mes = Integer.parseInt(request.getParameter("mes"));
	    int ano = Integer.parseInt(request.getParameter("ano"));
	    float total = 0;
	    String tipo = request.getParameter("tipo");

	    Document documento = new Document();
	    try {
	        response.setContentType("application/pdf");
	        response.addHeader("Content-Disposition",
	                "attachment; filename=relatorio" + tipo + "_" + meses[mes - 1] + "_" + ano + ".pdf");
	        PdfWriter.getInstance(documento, response.getOutputStream());

	        documento.open();

	      
	        String path = request.getServletContext().getRealPath("/assets/img/");
	        Image logo = Image.getInstance(path + "logo.png");
	        Image logo_2 = Image.getInstance(path + "SGI.png");

	        
	        logo.scaleToFit(100, 100);
	        logo_2.scaleToFit(100, 100);

	        
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

	        
	        Date dataAtual = new Date();
	        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	        Paragraph dataEmissao = new Paragraph("Data de Emissão: " + formatoData.format(dataAtual));
	        dataEmissao.setAlignment(Element.ALIGN_RIGHT);
	        documento.add(dataEmissao);

	        
	        Paragraph titulo = new Paragraph("Relatório de " + tipo + ".");
	        titulo.setAlignment(Element.ALIGN_CENTER);
	        documento.add(titulo);

	        
	        Paragraph subtitulo = new Paragraph("Mês: " + meses[mes - 1] + ", Ano: " + ano);
	        subtitulo.setAlignment(Element.ALIGN_CENTER);
	        documento.add(subtitulo);

	        documento.add(new Paragraph(" "));

	        
	        PdfPTable tabela = new PdfPTable(new float[]{1, 1, 3, 3, 2, 2, 2});
	        tabela.setWidthPercentage(100);

	        // Cabeçalho da tabela
	        PdfPCell col1 = new PdfPCell(new Paragraph("Item"));
	        PdfPCell col2 = new PdfPCell(new Paragraph("ID"));
	        PdfPCell col3 = new PdfPCell(new Paragraph("Membro"));
	        PdfPCell col4 = new PdfPCell(new Paragraph("Filial"));
	        PdfPCell col5 = new PdfPCell(new Paragraph("Data"));
	        PdfPCell col6 = new PdfPCell(new Paragraph("Tipo"));
	        PdfPCell col7 = new PdfPCell(new Paragraph("Valor"));

	        col1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        col2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        col3.setHorizontalAlignment(Element.ALIGN_CENTER);
	        col4.setHorizontalAlignment(Element.ALIGN_CENTER);
	        col5.setHorizontalAlignment(Element.ALIGN_CENTER);
	        col6.setHorizontalAlignment(Element.ALIGN_CENTER);
	        col7.setHorizontalAlignment(Element.ALIGN_CENTER);

	        col1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        col2.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        col3.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        col4.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        col5.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        col6.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        col7.setBackgroundColor(BaseColor.LIGHT_GRAY);

	        tabela.addCell(col1);
	        tabela.addCell(col2);
	        tabela.addCell(col3);
	        tabela.addCell(col4);
	        tabela.addCell(col5);
	        tabela.addCell(col6);
	        tabela.addCell(col7);

	        ArrayList<DizimosOferta> lista = dao.consultaDizimoOferta(mes, ano, tipo);
	        SimpleDateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");

	        for (int i = 0; i < lista.size(); i++) {
	            DizimosOferta item = lista.get(i);
	            String dataFormatada = dataFormatter.format(item.getDzodtcontribuicao());

	            tabela.addCell(new PdfPCell(new Paragraph(Integer.toString(i + 1))));
	            tabela.addCell(new PdfPCell(new Paragraph(Integer.toString(item.getIddizimooferta()))));
	            tabela.addCell(new PdfPCell(new Paragraph(item.getDzomembronome())));
	            tabela.addCell(new PdfPCell(new Paragraph(item.getDzoFilialNome())));
	            tabela.addCell(new PdfPCell(new Paragraph(dataFormatada)));
	            tabela.addCell(new PdfPCell(new Paragraph(item.getDzotipo())));
	            tabela.addCell(new PdfPCell(new Paragraph(nf.format(item.getDzovalor()))));

	            total += item.getDzovalor();
	        }

	        documento.add(tabela);

	        documento.add(new Paragraph(" "));
	        documento.add(new Paragraph("Total de " + tipo + ": " + nf.format(total)));

	        documento.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o relatório");
	    } finally {
	        if (documento.isOpen()) {
	            documento.close();
	        }
	    }
	}






}
