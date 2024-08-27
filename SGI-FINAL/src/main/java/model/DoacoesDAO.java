package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class DoacoesDAO {
	//** Módulo de conexao **//
	// Parametros de conexao //
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3308/bdsgi?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";
	// Metodos de conexao
	private Connection conectar() {
		Connection con = null;
		//tratamento de exceções
		try {
			Class.forName(driver);// le o drive do banco de dados
			con = DriverManager.getConnection(url, user, password);//con estabelece uma conexão com o banco e driverManager vai gerenciar o driver
			
			return con;
		} catch (Exception e) {
			System.out.println(e);//Mostra a exceção
			return null;
		}
	}
	
	/********* CREATE **********/
	public boolean inserirdoacao(DoacoesJavaBeans doacao) {
		String create = "insert into doacoesdebens(doanomedoador, doamembroid, doadescricao, doadtdoacao, doavalor, status, doaidfilial) value(?,?,?,?,?,?,?)";
		try {
			//Abrir a conexao com o banco
			Connection con = conectar();
			//Preparar a query para execucao no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			//Substituir os parametros '(?)' pelo conteudo das variaveis
			pst.setString(1, doacao.getDoanomedoador());
			pst.setString(2, doacao.getDoamembroid());
			pst.setString(3, doacao.getDoadescricao());
			pst.setDate(4, new java.sql.Date(doacao.getDoadtdoacao().getTime()));
			pst.setDouble(5, doacao.getDoavalor());
			pst.setString(6,  doacao.getStatus());
			pst.setString(7, doacao.getDoaidfilial());
			
			//Executar a query
			pst.executeUpdate();
			//Encerrar a conexao com banco
			con.close();
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	/********* READ LISTAR DOACAO *********/
	public ArrayList<DoacoesJavaBeans> listarDoacao(int filial) {
		ArrayList<DoacoesJavaBeans> doacao = new ArrayList<>();
		String read = "select * from doacoesdebens where doaidfilial = ? order by doaidfilial";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setInt(1, filial);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String iddoacao = rs.getString(1);
				String doanomedoador = rs.getString(2);
				String doamembroid = rs.getString(3);
				String doadescricao = rs.getString(4);
				Date doadtdoacao = rs.getDate(5);
				double doavalor = rs.getDouble(6);
				String status = rs.getString(7);
				String doaidfilial = rs.getString(8);
				doacao.add(new DoacoesJavaBeans(iddoacao, doanomedoador, doamembroid, doadescricao, doadtdoacao, doavalor, status, doaidfilial));
			}
			con.close();
			System.out.println(filial);
			return doacao;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public DoacoesJavaBeans selecionarDoacao(String iddoacao) {
		String read2 = "select * from doacoesdebens where iddoacao =?";
		DoacoesJavaBeans doacaoRestul = new DoacoesJavaBeans();
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, iddoacao);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				doacaoRestul.setIddoacao(rs.getString(1));
				doacaoRestul.setDoanomedoador(rs.getString(2));
				doacaoRestul.setDoamembroid(rs.getString(3));
				doacaoRestul.setDoadescricao(rs.getString(4));
				doacaoRestul.setDoadtdoacao(rs.getDate(5));
				doacaoRestul.setDoavalor(rs.getDouble(6));
				doacaoRestul.setStatus(rs.getString(7));
				doacaoRestul.setDoaidfilial(rs.getString(8));
			}
			con.close();
			return doacaoRestul;
		}catch (Exception e) {
			System.out.println("Erro ao consultar registro: " + iddoacao + " : " +e);
			return null;
		}
	}
	
	//EDITAR DOACAO
	public boolean alterarDoacao(DoacoesJavaBeans doacao) {
		String create = "update doacoesdebens set doanomedoador=?, doamembroid=?, doadescricao=?, doadtdoacao=?, doavalor=?, status=?, doaidfilial=? where iddoacao=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			
			
			pst.setString(1, doacao.getDoanomedoador());
			pst.setString(2, doacao.getDoamembroid());
			pst.setString(3, doacao.getDoadescricao());
			pst.setDate(4, new java.sql.Date(doacao.getDoadtdoacao().getTime()));
			pst.setDouble(5, doacao.getDoavalor());
			pst.setString(6,  doacao.getStatus());
			pst.setString(7, doacao.getDoaidfilial());
			pst.setString(8, doacao.getIddoacao());
			
			pst.executeUpdate();
			con.close();
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	//DELETAR DOAÇÃO
	public boolean deletarDoacao(DoacoesJavaBeans doacao) {
		String deletedoacao = "delete from doacoesdebens where iddoacao=?";
		System.out.println("ID da doacao na classe Dao: " + doacao.getIddoacao());
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(deletedoacao);
			pst.setString(1, doacao.getIddoacao());
			
			pst.executeUpdate();
			con.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
}


