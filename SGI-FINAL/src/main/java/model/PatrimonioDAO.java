package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class PatrimonioDAO {
	/** Módulo de conexao **/
	// Parametros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3308/bdsgi?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";

	// Metodos de conexão
	private Connection conectar() {
		Connection con = null;
		// tratamento de exceções
		try {
			Class.forName(driver);// ler o drive do banco de dados
			con = DriverManager.getConnection(url, user, password);// con estabelece uma conexão com o banco e
																	// driveManager vai gerenciar o driver
			return con;
		} catch (Exception e) {
			System.out.println(e);// Mostrar a exceção
			return null;

		}
	}

	/****** CREATE ******/
	public boolean inserirpatrimonio(PatrimonioJavaBeans patrimonio) {
		String create = "insert into patrimonio (ptrnome, ptridfil, ptrdescricao, ptrvalor, ptrdataaquisicao, ptrestado) value(?,?,?,?,?,?)";
		try {
			// Abrir a conexão com o banco
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parametros '(?)' pelo conteudo das variaveis
			pst.setString(1, patrimonio.getPtrnome());
			pst.setString(2, patrimonio.getPtridfil());
			pst.setString(3, patrimonio.getPtrdescricao());
			pst.setDouble(4, patrimonio.getPtrvalor());
			pst.setDate(5, new java.sql.Date(patrimonio.getPtrdataaquisicao().getTime()));
			pst.setString(6, patrimonio.getPtrestado());

			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexao com banco
			con.close();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	// READ LISTAR CONTATOS
	public ArrayList<PatrimonioJavaBeans> listarPatrimonio(int filial) {
		ArrayList<PatrimonioJavaBeans> patrimonios = new ArrayList<>();
		String read = "call listar_patrimonios_por_filial(?);";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setInt(1, filial);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idpatrimonio = rs.getString(1);
				String ptrnome = rs.getString(2);
				String ptridfil = rs.getString(3);
				String ptrdescricao = rs.getString(4);
				double ptrvalor = rs.getDouble(5);
				Date ptrdataaquisicao = rs.getDate(6);
				String ptrestado = rs.getString(7);
				patrimonios.add(new PatrimonioJavaBeans(idpatrimonio, ptrnome, ptridfil, ptrdescricao, ptrvalor,
						ptrdataaquisicao, ptrestado));
			}
			con.close();
			return patrimonios;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// UPDATE - Necessita de 2 métodos, um para selecionar o dado e ou outro para
	// atualizar o dado
	public PatrimonioJavaBeans selecionarPatrimonio(PatrimonioJavaBeans patrimonio) {
		String read2 = "select * from patrimonio where idpatrimonio = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, patrimonio.getIdpatrimonio());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// setar as variaveis JavabeansPatri
				patrimonio.setIdpatrimonio(rs.getString(1));
				patrimonio.setPtrnome(rs.getString(2));
				patrimonio.setPtridfil(rs.getString(3));
				patrimonio.setPtrdescricao(rs.getString(4));
				patrimonio.setPtrvalor(rs.getDouble(5));
				patrimonio.setPtrdataaquisicao(rs.getDate(6));
				patrimonio.setPtrestado(rs.getString(7));
			}
			con.close();
			return patrimonio;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// editar patrimonio
	public boolean alterarpatrimonio(PatrimonioJavaBeans patrimonio) {
		String create = "update patrimonio set ptrnome=?, ptridfil=?, ptrdescricao=?, ptrvalor=?, ptrdataaquisicao=?, ptrestado=? where idpatrimonio=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(7, patrimonio.getIdpatrimonio());
			pst.setString(1, patrimonio.getPtrnome());
			pst.setString(2, patrimonio.getPtridfil());
			pst.setString(3, patrimonio.getPtrdescricao());
			pst.setDouble(4, patrimonio.getPtrvalor());
			pst.setDate(5, new java.sql.Date(patrimonio.getPtrdataaquisicao().getTime()));
			pst.setString(6, patrimonio.getPtrestado());
			
			
			pst.executeUpdate();
			con.close();
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	//Deletar Patrimonio
	public void deletarPatrimonio(PatrimonioJavaBeans patrimonio) {
		String deletepatri = "delete from patrimonio where idpatrimonio=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(deletepatri);
			pst.setString(1, patrimonio.getIdpatrimonio());
			
			pst.executeUpdate();
			con.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public float totalPatrimonio() {
		String functionSQL = "SELECT f_total_patrimonio();";
		float total = 0;
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(functionSQL);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				
				total = rs.getFloat(1);
			}
			
			con.close();
			return total;
			
		} catch (Exception e) {
			return 0;
		}
	}
}
