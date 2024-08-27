package controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MembroDAO;
import model.MembroJavaBeans;

/**
 * Servlet implementation class TrocarSenhaController
 */
@WebServlet("/TrocarSenhaController")
public class TrocarSenhaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MembroDAO dao = new MembroDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrocarSenhaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String senha = request.getParameter("senha");
		String confirmaSenha = request.getParameter("senha_confirma");
		int mbrid = Integer.parseInt(request.getParameter("mbrid"));
		MembroJavaBeans membro = new MembroJavaBeans();
		membro.setId_Membro(mbrid);
		if(senha.equals(confirmaSenha)) {
			
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
				membro.setMbrsenha(senhaCriptografada);

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			 if(dao.alteraSenha(membro)) {
				 response.sendRedirect("index.jsp");
			 }else {
				 response.sendRedirect("pesquisarUsuario.jsp?error=false");
			 }
			
			
		}else {
			response.sendRedirect("pesquisarUsuario.jsp?error=false");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cpfComMascara = request.getParameter("CPF");
		String cpfSemMascara = cpfComMascara.replaceAll("[^\\d]", "");
		System.out.println(cpfSemMascara);
		String id = dao.achaMembroCPF(cpfSemMascara);
		if(id != null) {
			request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher("trocarSenha.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect("index.jsp");
		}
		

		
	}

}
