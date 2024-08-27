package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//import org.apache.tomcat.jni.Time;

import java.sql.Date;
import java.sql.Time;

public class EventoDAO {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3308/bdsgi?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";

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

	   public void testeConexao() {
	      try {
	         Connection con = this.conectar();
	         System.out.println(con);
	         con.close();
	      } catch (Exception var2) {
	         System.out.println(var2);
	      }

	   }

	   public boolean inserirEvento(EventoJavaBeans evento) {
	      String create = "insert into evento(evetitulo,evedescricao,evedata,evehora,evelocalizacao,evestatus,eveidfil) values(?,?,?,?,?,?,?)";

	      try {
	         Connection con = this.conectar();
	         PreparedStatement pst = con.prepareStatement(create);
	         pst.setString(1, evento.getEvetitulo());
	         pst.setString(2, evento.getEvedescricao());
	         pst.setDate(3, new Date(evento.getEvedata().getTime()));
	         pst.setTime(4, new Time(evento.getEvehora().getTime()));
	         pst.setString(5, evento.getEvelocailacao());
	         pst.setString(6, evento.getEvestatus());
	         pst.setString(7, evento.getEvefilial());
	         
	         pst.executeUpdate();
	         con.close();
	         return true;
	      } catch (Exception var5) {
	         System.out.println(var5);
	         return false;
	      }

	   }

	   public ArrayList<EventoJavaBeans> listarEventos(String eveidfilial) {
	      ArrayList<EventoJavaBeans> listarEvento = new ArrayList<>();
	      String read = "select idevento, evetitulo,evedescricao, evedata, evehora, evelocalizacao, evestatus, filnome from evento inner join filial on eveidfil = idfilial where eveidfil = ? order by idevento; ";

	      try {
	         Connection con = this.conectar();
	         PreparedStatement pst = con.prepareStatement(read);
	         pst.setString(1, eveidfilial);
	         ResultSet rs = pst.executeQuery();

	         while(rs.next()) {
	        	 String idevento = rs.getString(1);
	             String evetitulo = rs.getString(2);
	             String evedescricao = rs.getString(3); // Corrigir o index caso necessário
	             java.sql.Date evedata = rs.getDate(4); // Usando java.sql.Date
	             java.sql.Time evehora = rs.getTime(5); // Usando java.sql.Time
	             String local = rs.getString(6);
	             String status = rs.getString(7);
	             String filial = rs.getString(8);

	             listarEvento.add(new EventoJavaBeans(idevento, evetitulo, evedescricao, evedata, evehora, local, status, filial));
	         }

	         con.close();
	         return listarEvento;
	      } catch (Exception var14) {
	         System.out.println(var14);
	         return null;
	      }
	   }

	   public void selecionarEventos(EventoJavaBeans evento) {
	      String read2 = "select *from evento where idevento = ?";

	      try {
	         Connection con = this.conectar();
	         PreparedStatement pst = con.prepareStatement(read2);
	         pst.setString(1, evento.getIdevento());
	         ResultSet rs = pst.executeQuery();

	         while(rs.next()) {
	            evento.setIdevento(rs.getString(1));
	            evento.setEvetitulo(rs.getString(2));
	            evento.setEvedescricao(rs.getString(3));
	            evento.setEvedata(rs.getDate(4));
	            evento.setEvehora(rs.getTime(5));
	            evento.setEvelocailacao(rs.getString(6));
	            evento.setEvestatus(rs.getString(7));
	            evento.setEvefilial(rs.getString(8));
	         }

	         con.close();
	      } catch (Exception var6) {
	         System.out.println(var6);
	      }

	   }

	   public boolean alterarEvento(EventoJavaBeans evento) {
	      String change = "update evento set evetitulo = ?, evedescricao = ?, evedata = ?, evehora = ?, evelocalizacao = ?, evestatus = ?, eveidfil = ? where idevento = ?";

	      try {
	         Connection con = this.conectar();
	         PreparedStatement pst = con.prepareStatement(change);
	         pst.setString(8, evento.getIdevento());
	         pst.setString(1, evento.getEvetitulo());
	         pst.setString(2, evento.getEvedescricao());
	         pst.setDate(3, new Date(evento.getEvedata().getTime()));
	         pst.setTime(4, new Time(evento.getEvehora().getTime()));
	         pst.setString(5, evento.getEvelocailacao());
	         pst.setString(6, evento.getEvestatus());
	         pst.setString(7, evento.getEvefilial());
	         pst.executeUpdate();
	         con.close();
	         return true;
	      } catch (Exception var5) {
	         System.out.println(var5);
	         return false;
	      }

	   }

	   public void deletarEvento(EventoJavaBeans evento) {
	      String delete = "delete from evento where idevento = ?";

	      try {
	         Connection con = this.conectar();
	         PreparedStatement pst = con.prepareStatement(delete);
	         pst.setString(1, evento.getIdevento());
	         pst.executeUpdate();
	         con.close();
	      } catch (Exception var5) {
	         System.out.println(var5);
	      }

	   }
	   
	   public ArrayList<EventoJavaBeans> listarEventoCard(){
		   String sp = "CALL sp_listar_evento_card();";
		   ArrayList<EventoJavaBeans> eventos = new ArrayList<>();
		   
		   try {
			
			   Connection con = conectar();
			   PreparedStatement pst = con.prepareStatement(sp);
			   ResultSet rs = pst.executeQuery();	
			   
			   while(rs.next()) {
				   String titulo = rs.getString(1);
				   Date data = rs.getDate(2);
				   Time hora = rs.getTime(3);
				   
				   eventos.add(new EventoJavaBeans(titulo, data, hora));
			   }
			   
			   con.close();
			   return eventos;
		} catch (Exception e) {
			return null;
		}
	   }
}
