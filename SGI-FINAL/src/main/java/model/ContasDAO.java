package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class ContasDAO {
	/** Módulo de conexao **/
	// Parametros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3308/bdsgi?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";

	// Metodos de conexão
	private Connection conectar() {
		Connection con = null;
		//tratamento de exceções
		try {
			Class.forName(driver);//ler o drive do banco de dados
			con = DriverManager.getConnection(url, user, password);//con estabelece uma conexão com o banco e driveManager vai gerenciar o driver
			return con;
		} catch (Exception e) {
			System.out.println(e);//Mostrar a exceção
			return null;

		}
	}

	// CRUD CREATE
	public boolean inserirConta(ContasJavaBeans conta) {
		String create = "insert into contasapagar (cntnomefornecedor, cntidfilial, cntdescricaoconta, cntdtvencimento, cntvalor, cntstatuspagamento) value(?,?,?,?,?,?);";
		try {
			// abrir a conexao
			Connection con = conectar();
			// preparar a query para conexão no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// substituir os parametros '?' pelo conteúdo das variáveis JavaBeans
			pst.setString(1, conta.getCntnomefornecedor());
			pst.setString(2, conta.getCntidfilial());
			pst.setString(3, conta.getCntdescricaoconta());
			pst.setDate(4, new java.sql.Date(conta.getCntdtvencimento().getTime()));
			pst.setDouble(5, conta.getCntvalor());
			pst.setString(6, conta.getCntstatuspagamento());

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

	// CRUD READ
	public ArrayList<ContasJavaBeans> listarContas() {
		// criando um objeto para acessar a classe JavaBeans
		ArrayList<ContasJavaBeans> contas = new ArrayList<>();//cria uma lista dinamica do tipo JavaBeans para recuperar os registros
		String read = "select * from contasapagar order by cntdtvencimento;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado enquanto houver contas registradas
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idcontapagar = rs.getString(1);
				String cntnomefornecedor = rs.getString(2);
				String cntidfilial = rs.getString(3);
				String cntdescricaoconta = rs.getString(4);
				Date cntdtvencimento = rs.getDate(5);
				String cntstatuspagamento = rs.getString(6);
				double cntvalor = rs.getDouble(7);
				
				//metodo da classe ArrayLista que vai adicionar cada registro no ArrayList
				contas.add(new ContasJavaBeans(idcontapagar, cntnomefornecedor, cntidfilial, cntdescricaoconta,
						cntdtvencimento, cntvalor, cntstatuspagamento));
			}
			con.close();
			return contas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// CRUD UPDATE
	// selecionar a conta
	//Exibir e preencher dinamicamente na página de edição
	public void selecionarConta(ContasJavaBeans conta) {
		String read2 = "select * from contasapagar where idcontapagar = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, conta.getIdcontapagar());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// setar as variaveis Javabeans
				conta.setIdcontapagar(rs.getString(1));
				conta.setCntnomefornecedor(rs.getString(2));
				conta.setCntidfilial(rs.getString(3));
				conta.setCntdescricaoconta(rs.getString(4));
				conta.setCntdtvencimento(rs.getDate(5));
				conta.setCntvalor(rs.getDouble(7));
				conta.setCntstatuspagamento(rs.getString(6));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// editar a conta
	public void alterarConta(ContasJavaBeans conta) {
		String create = "update contasapagar set cntnomefornecedor=?, cntidfilial=?, cntdescricaoconta=?, cntdtvencimento=?, cntvalor=?, cntstatuspagamento=? where idcontapagar=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(7, conta.getIdcontapagar());
			pst.setString(1, conta.getCntnomefornecedor());
			pst.setString(2, conta.getCntidfilial());
			pst.setString(3, conta.getCntdescricaoconta());
			pst.setDate(4,new java.sql.Date(conta.getCntdtvencimento().getTime()));
			pst.setDouble(5, conta.getCntvalor());
			pst.setString(6, conta.getCntstatuspagamento());
			
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//Deletar conta
	public  void deletarConta(ContasJavaBeans conta) {
		String delete = "delete from contasapagar where idcontapagar=?;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, conta.getIdcontapagar());
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
