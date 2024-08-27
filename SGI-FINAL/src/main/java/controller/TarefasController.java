package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FilialDAO;
import model.FilialJavaBeans;
import model.TarefaDAO;
import model.TarefasJavaBeans;

/**
 * Servlet implementation class TarefasController
 */
@WebServlet(urlPatterns = { "/TarefasController", "/insertTarefa", "/listarTarefas", "/listarTarefasCanceladas",
		"/listarTarefasConcluida", "/detalheTarefa", "/updateTarefa", "/cadastroTarefa" })
public class TarefasController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TarefasJavaBeans tarefa = new TarefasJavaBeans();
	TarefaDAO dao = new TarefaDAO();
	FilialDAO daoFilial = new FilialDAO();
	FilialJavaBeans filial = new FilialJavaBeans();

	public TarefasController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		if (action.equals("/insertTarefa")) {
			try {
				novaTarefa(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("/listarTarefas")) {
			try {
				listarTarefas(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("/listarTarefasCanceladas")) {

			try {
				listarTarefasCancelada(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("/listarTarefasConcluida")) {
			try {
				listarTarefasConcluida(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("/detalheTarefa")) {
			try {
				detalheTarefa(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("/updateTarefa")) {
			try {
				updateTarefa(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(action.equals("/cadastroTarefa")) {
			listarFilial(request, response);
			
		}
	}

	protected void novaTarefa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		tarefa.setTarefatitulo(request.getParameter("trftitulo"));
		tarefa.setTarefafilialid(Integer.parseInt(request.getParameter("trffilial")));
		tarefa.setTarefadesc(request.getParameter("trfdesc"));
		tarefa.setTrfmbrid(Integer.parseInt(request.getParameter("trfmbrid")));

		String dateStr = request.getParameter("trfdata");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			tarefa.setTarefadata(sdf.parse(dateStr));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		String timeStr = request.getParameter("trfhora");
		SimpleDateFormat sdt = new SimpleDateFormat("HH:mm");
		try {
			java.util.Date date = sdt.parse(timeStr);
			tarefa.setTarefahora(new java.sql.Time(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tarefa.setStatus(request.getParameter("trfstatus"));

		System.out.println("Titulo: " + tarefa.getTarefatitulo());

		if(dao.inserirTarefa(tarefa)) {
			
			response.sendRedirect("cadastroTarefa?success=true");
		}else {
			response.sendRedirect("cadastroTarefa?success=false");
		}

	}
	
	protected void listarFilial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<FilialJavaBeans> filiais = daoFilial.listarFilial();
		request.setAttribute("filiais", filiais);
		RequestDispatcher rd = request.getRequestDispatcher("cadastroTarefas.jsp");
		rd.forward(request, response);
		
	
	}

	protected void listarTarefas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<TarefasJavaBeans> lista = dao.listaTarefas();

		request.setAttribute("tarefas", lista);
		RequestDispatcher rd = request.getRequestDispatcher("listaTarefas.jsp");
		rd.forward(request, response);

	}

	protected void detalheTarefa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int taskId = Integer.parseInt(request.getParameter("trfid"));
		TarefasJavaBeans tarefa = dao.selecaoTarefa(taskId);
		System.out.println(taskId);
		request.setAttribute("tarefaEdit", tarefa);
		RequestDispatcher rd = request.getRequestDispatcher("tarefaEdit.jsp");
		rd.forward(request, response);
		System.out.println(tarefa.getTarefaid());
	}

	protected void listarTarefasCancelada(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<TarefasJavaBeans> lista = dao.listaTarefasCancelada();

		request.setAttribute("tarefasCancelada", lista);
		RequestDispatcher rd = request.getRequestDispatcher("listaTarefaCancelada.jsp");
		rd.forward(request, response);

	}

	protected void listarTarefasConcluida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<TarefasJavaBeans> lista = dao.listaTarefasConcluido();

		request.setAttribute("tarefasConcluida", lista);
		RequestDispatcher rd = request.getRequestDispatcher("listaTarefaConcluida.jsp");
		rd.forward(request, response);

	}

	protected void updateTarefa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		tarefa.setTarefaid(Integer.parseInt(request.getParameter("trfid")));
		tarefa.setTarefatitulo(request.getParameter("trftitulo"));
		tarefa.setTarefafilialid(Integer.parseInt(request.getParameter("trffilial")));
		tarefa.setTarefadesc(request.getParameter("trfdesc"));
		tarefa.setTrfmbrid(Integer.parseInt(request.getParameter("trfmbrid")));

		String dateStr = request.getParameter("trfdata");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			tarefa.setTarefadata(sdf.parse(dateStr));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		String timeStr = request.getParameter("trfhora");
		SimpleDateFormat sdt = new SimpleDateFormat("HH:mm");
		try {
			java.util.Date date = sdt.parse(timeStr);
			tarefa.setTarefahora(new java.sql.Time(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tarefa.setStatus(request.getParameter("trfstatus"));

		System.out.println("Titulo: " + tarefa.getTarefatitulo());

		if(dao.updateTarefa(tarefa)) {
			
			response.sendRedirect("listarTarefas?success=true");
		}else {
			response.sendRedirect("listarTarefas?success=false");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
