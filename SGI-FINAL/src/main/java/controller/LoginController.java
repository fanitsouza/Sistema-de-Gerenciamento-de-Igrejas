package controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MembroDAO;
import model.MembroJavaBeans;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    MembroDAO dao = new MembroDAO();
	    String email = request.getParameter("email");
	    String senha = request.getParameter("senha");

	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(senha.getBytes());
	        byte[] digest = md.digest();

	        StringBuilder sb = new StringBuilder();
	        for (byte b : digest) {
	            sb.append(String.format("%02x", b & 0xff));
	        }
	        senha = sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }

	    try {
	        MembroJavaBeans membro = dao.login(email, senha);

	        if (membro != null && membro.getMbrnome() != null) {
	            HttpSession session = request.getSession();
	            String nomeCompleto = membro.getMbrnome();
	            String[] partesDoNome = nomeCompleto.split(" ");
	            String primeirosNomes;

	            if (partesDoNome.length >= 2) {
	                primeirosNomes = partesDoNome[0] + " " + partesDoNome[1];
	            } else {
	                primeirosNomes = nomeCompleto; // Ou apenas partesDoNome[0] se preferir apenas o primeiro nome
	            }

	            System.out.println(primeirosNomes);

	            session.setAttribute("username", primeirosNomes);
	            session.setAttribute("userphoto", membro.getMbrfotoCaminho());
	            session.setAttribute("nivel", membro.getMbracesso());
	            response.sendRedirect("home.jsp");
	        } else {
	            response.sendRedirect("index.jsp?error=true");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("index.jsp?error=true");
	    }
	}


}
