
package model;
//Importações necessárias
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class DizimosOfertaDAO {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3308/bdsgi?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";	
	
	private Connection conectar() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			return conn;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// teste de conexão
	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}
	
	
	public void insereContribuicao(DizimosOferta dizimoOferta) {
		
		//Comando sql que chama a procedure no banco de dados
		String callSpNovaContribuicao = "call novo_dizomo_oferta(?,?,?,?)";
		try {
			//Abrindo a conexão com banco de dados
			Connection con = conectar();
			
			//Preparando o comando sql
			PreparedStatement pst = con.prepareStatement(callSpNovaContribuicao);
			
			//Inseriando os parametros no comando sql
			pst.setInt(1, dizimoOferta.getDzombrid());
			pst.setString(2, dizimoOferta.getDzotipo());
			pst.setFloat(3, dizimoOferta.getDzovalor());
			pst.setDate(4, new java.sql.Date(dizimoOferta.getDzodtcontribuicao().getTime()));
			
			pst.executeUpdate();
			
			con.close();
			
			
			
		} catch (Exception e) {
			System.out.println("Erro Dizimo: " + e);
		}
	}
	
	public ArrayList<DizimosOferta> consultaDizimoOferta(int mes, int ano, String tipo){
		ArrayList<DizimosOferta> dizimosOfertas = new ArrayList<>();
		String strConsultasql = "call sp_dizimo_por_mes(?,?,?);";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(strConsultasql);
			pst.setInt(1, mes);
			pst.setInt(2, ano);
			pst.setString(3, tipo);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int dzoid = rs.getInt(1);
				String dzonome = rs.getString(2);
				String dzofilial = rs.getString(3);
				Date dzoDate = rs.getDate(4);
				String dzotipo = rs.getString(5);
				float dzoValor = rs.getFloat(6);
				
				dizimosOfertas.add(new DizimosOferta(dzoid,dzotipo,dzoValor,dzoDate,dzonome,dzofilial));
				
			}
			
			con.close();
			return dizimosOfertas;
		} catch (Exception e) {
			return null;
		}
		
				
				
	}
	
	public void excluirRegistroDizOferta(int dzoid) {
		String strProcedure = "call sp_excluir_dizimoOFerta(?);";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(strProcedure);
			pst.setInt(1, dzoid);
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println("Erro ao excluir registro: " + e);
		}
		
	}
	
	public ArrayList<DizimosOferta> listarDizimos(String tipo){
		String sp = "call sp_dizimosOfertas_por_tipo(?);";
		ArrayList<DizimosOferta> dizimosOfertas = new ArrayList<>();
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sp);
			ps.setString(1, tipo);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Float valor = rs.getFloat(1);
				Date data = rs.getDate(2);
				String filial = rs.getString(3);
				
				dizimosOfertas.add(new DizimosOferta(valor, data, filial));
			}
			
			con.close();
			return dizimosOfertas;
		} catch (Exception e) {
			return null;
		}
	}
	
	public float totalDizimoOferta(String tipo) {
		String functionSQL = "SELECT f_total_dizimo_oferta(?);";
		float total = 0;
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(functionSQL);
			pst.setString(1, tipo);
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
	
	public float totalDizimoOfertaMes(int mes, int ano, String tipo){
		float total = 0;
		String sp = "CALL sp_total_dizimo_oferta_mesano(?, ?, ?);";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(sp);
			pst.setInt(1, mes);
			pst.setInt(2, ano);
			pst.setString(3, tipo);
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
