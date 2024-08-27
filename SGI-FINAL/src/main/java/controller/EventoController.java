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


import model.EventoDAO;
import model.EventoJavaBeans;

/**
 * Servlet implementation class EventoController
 */
@WebServlet(urlPatterns = {"/EventoController", "/listarEventos", "/insertEvento","/selectEvento", "/updateEvento", "/deleteEvento"})
public class EventoController extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	   EventoDAO dao = new EventoDAO();
	   EventoJavaBeans evento = new EventoJavaBeans();

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
	      this.dao.testeConexao();
	      String action = request.getServletPath();
	      System.out.println(action);
	      if (action.equals("/listarEventos")) {
	         this.listaEventos(request, response);
	      } else if (action.equals("/insertEvento")) {
	         this.novoEvento(request, response);
	      } else if (action.equals("/selectEvento")) {
	         this.pesquisaEventos(request, response);
	      } else if (action.equals("/updateEvento")) {
	         this.editarEvento(request, response);
	      } else if (action.equals("/deleteEvento")) {
	         this.removerEvento(request, response);
	      } else {
	         response.sendRedirect("index.html");
	      }

	   }
	   

	   

	   protected void novoEvento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      EventoJavaBeans evento = new EventoJavaBeans();
	      evento.setEvetitulo(request.getParameter("evetitulo"));
	      evento.setEvedescricao(request.getParameter("evedescricao"));
	      evento.setEvelocailacao(request.getParameter("evelocalizacao"));
	      evento.setEvefilial(request.getParameter("evefilial"));
	      evento.setEvestatus(request.getParameter("evestatus"));
	      String dateStr = request.getParameter("evedata");
	      System.out.println(dateStr);
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	      try {
	    	  
	    	  java.util.Date date = sdf.parse(dateStr);

	    	    evento.setEvedata(new java.sql.Date(date.getTime()));

	      } catch (ParseException var9) {
	         var9.printStackTrace();
	      }

	      String timeStr = request.getParameter("evehora");
	      SimpleDateFormat sdt = new SimpleDateFormat("HH:mm");

	      try {
	    	  
	    	  java.util.Date time = sdt.parse(timeStr);

	    	    evento.setEvehora(new java.sql.Time(time.getTime()));
	    	 // 
	      } catch (ParseException var8) {
	         var8.printStackTrace();
	      }

	      if(dao.inserirEvento(evento)) {
	    	  response.sendRedirect("cadastro_eventos.jsp?success=true");
	      }else {
	    	  response.sendRedirect("cadastro_eventos.jsp?success=false");
	      }
	     
	   }

	   protected void listaEventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      ArrayList<EventoJavaBeans> lista = dao.listarEventos(request.getParameter("evefilial"));
	      System.out.println(request.getParameter("evefilial"));
	      request.setAttribute("listarEvento", lista);
	      RequestDispatcher rd = request.getRequestDispatcher("listaEventos.jsp");
	      rd.forward(request, response);
	   }
	   

	   protected void pesquisaEventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String idevento = request.getParameter("idevento");
	      this.evento.setIdevento(idevento);
	      this.dao.selecionarEventos(this.evento);
	      request.setAttribute("idevento", this.evento.getIdevento());
	      request.setAttribute("evetitulo", this.evento.getEvetitulo());
	      request.setAttribute("evedescricao", this.evento.getEvedescricao());
	      request.setAttribute("evedata", this.evento.getEvedata());
	      request.setAttribute("evehora", this.evento.getEvehora());
	      request.setAttribute("evendereco", this.evento.getEvelocailacao());
	      request.setAttribute("evenumero", this.evento.getEvestatus());
	      request.setAttribute("evebairro", this.evento.getEvefilial());
	      RequestDispatcher rd = request.getRequestDispatcher("editarEvento.jsp");
	      rd.forward(request, response);
	   }
	   

	   protected void editarEvento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      this.evento.setIdevento(request.getParameter("idevento"));
	      this.evento.setEvetitulo(request.getParameter("evetitulo"));
	      this.evento.setEvedescricao(request.getParameter("evedescricao"));
	      this.evento.setEvelocailacao(request.getParameter("evelocalizacao"));
	      this.evento.setEvestatus(request.getParameter("evestatus"));
	      this.evento.setEvefilial(request.getParameter("evefilial"));
	      String dateStr = request.getParameter("evedata");
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	      try {
	    	  java.util.Date date = sdf.parse(dateStr);
	    	  this.evento.setEvedata(new java.sql.Date(date.getTime()));
	        // this.evento.setEvedata((Date) sdf.parse(dateStr));
	      } catch (ParseException var9) {
	         var9.printStackTrace();
	         System.out.println("Formato da data inválido.Use yyyy-MM-dd.");
	      }

	      String timeStr = request.getParameter("evehora");
	      SimpleDateFormat sdt = new SimpleDateFormat("HH:mm");

	      try {
	    	  java.util.Date date = sdt.parse(timeStr);
	    	  this.evento.setEvehora(new java.sql.Time(date.getTime()));
	         //Date date = (Date) sdt.parse(timeStr);
	        // this.evento.setEvehora(new Time(date.getTime()));
	      } catch (ParseException var8) {
	         var8.printStackTrace();
	      }
	      
	      if(dao.alterarEvento(this.evento)) {
	    	  response.sendRedirect("pesquisarEvento.jsp?success=true");
	      }else {
	    	  response.sendRedirect("pesquisarEvento.jsp?success=false");
	      }
	      
	   }
	   

	   protected void removerEvento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String idevento = request.getParameter("idevento");
	      System.out.println(idevento);
	      this.evento.setIdevento(idevento);
	      this.dao.deletarEvento(this.evento);
	      response.sendRedirect("pesquisarEvento.jsp");
	   }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
