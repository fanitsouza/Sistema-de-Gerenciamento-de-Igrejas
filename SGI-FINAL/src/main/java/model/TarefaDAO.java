package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class TarefaDAO {
	
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
	
	public boolean inserirTarefa(TarefasJavaBeans tarefa) {
		String callspinserir = "CALL sp_inserir_tarefa(?,?,?,?,?,?,?)";
		try {
			
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(callspinserir);
			
			pst.setString(1, tarefa.getTarefatitulo());
			pst.setInt(2,tarefa.getTarefafilialid());
			pst.setString(3, tarefa.getTarefadesc());
			pst.setInt(4, tarefa.getTrfmbrid());
			pst.setDate(5, new java.sql.Date(tarefa.getTarefadata().getTime()));
			pst.setTime(6, new java.sql.Time(tarefa.getTarefahora().getTime())) ;
			pst.setString(7, tarefa.getStatus());
			
			pst.executeUpdate();
			
			con.close();
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public ArrayList<TarefasJavaBeans> listaTarefas(){
		ArrayList<TarefasJavaBeans> listaTarefas = new ArrayList<>();
		String callSptarefas = "Call sp_listar_tarefa();";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(callSptarefas);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String titulo = rs.getString(1);
				String filial = rs.getString(2);
				String descricao = rs.getString(3);
				String responsavel = rs.getString(4);
				Date data = rs.getDate(5);
				Time hora = rs.getTime(6);
				String status = rs.getString(7);
				int id = rs.getInt(8);
				listaTarefas.add(new TarefasJavaBeans(id,titulo,filial,descricao, responsavel, data, hora, status));
			}
			con.close();
			return listaTarefas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public ArrayList<TarefasJavaBeans> listaTarefasCancelada(){
		ArrayList<TarefasJavaBeans> listaTarefas = new ArrayList<>();
		String callSptarefas = "Call sp_listar_tarefa_cancelada();";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(callSptarefas);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String titulo = rs.getString(1);
				String filial = rs.getString(2);
				String descricao = rs.getString(3);
				String responsavel = rs.getString(4);
				Date data = rs.getDate(5);
				Time hora = rs.getTime(6);
				String status = rs.getString(7);
				int id = rs.getInt(8);
				listaTarefas.add(new TarefasJavaBeans(id,titulo,filial,descricao, responsavel, data, hora, status));
			}
			con.close();
			return listaTarefas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	

	public ArrayList<TarefasJavaBeans> listaTarefasConcluido(){
		ArrayList<TarefasJavaBeans> listaTarefas = new ArrayList<>();
		String callSptarefas = "Call sp_listar_tarefa_concluido();";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(callSptarefas);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String titulo = rs.getString(1);
				String filial = rs.getString(2);
				String descricao = rs.getString(3);
				String responsavel = rs.getString(4);
				Date data = rs.getDate(5);
				Time hora = rs.getTime(6);
				String status = rs.getString(7);
				int id = rs.getInt(8);
				listaTarefas.add(new TarefasJavaBeans(id,titulo,filial,descricao, responsavel, data, hora, status));
			}
			con.close();
			return listaTarefas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public TarefasJavaBeans selecaoTarefa(int trfid) {
		String callSpselecao = "call sp_listar_tarefa_por_id(?);";
		TarefasJavaBeans tarefa = new TarefasJavaBeans();
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(callSpselecao);
			pst.setInt(1, trfid);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				tarefa.setTarefaid(rs.getInt(1));
				tarefa.setTarefatitulo(rs.getString(2));
				tarefa.setTrafilnome(rs.getString(3));
				tarefa.setTarefadesc(rs.getString(4));
				tarefa.setTrfmbrid(rs.getInt(5));
				tarefa.setTarefadata(rs.getDate(6));
				tarefa.setTarefahora(rs.getTime(7));
				tarefa.setStatus(rs.getString(8));
			}
			
			con.close();
			return tarefa;
			
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean updateTarefa(TarefasJavaBeans tarefa) {
		String callspinserir = "CALL sp_editar_tarefa(?,?,?,?,?,?,?,?);";
		try {
			
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(callspinserir);
			
			pst.setInt(1, tarefa.getTarefaid());
			pst.setString(2, tarefa.getTarefatitulo());
			pst.setInt(3,tarefa.getTarefafilialid());
			pst.setString(4, tarefa.getTarefadesc());
			pst.setInt(5, tarefa.getTrfmbrid());
			pst.setDate(6, new java.sql.Date(tarefa.getTarefadata().getTime()));
			pst.setTime(7, new java.sql.Time(tarefa.getTarefahora().getTime())) ;
			pst.setString(8, tarefa.getStatus());
			
			pst.executeUpdate();
			
			con.close();
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
