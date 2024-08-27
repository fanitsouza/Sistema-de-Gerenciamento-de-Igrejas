package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FilialDAO {
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
	
	public ArrayList<FilialJavaBeans> listarFilial() {
		ArrayList<FilialJavaBeans> filiais = new ArrayList<>();
		String view = "select * from vw_filial;";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(view);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String sede = rs.getString(2);
				String nome = rs.getString(3);
				String telefone = rs.getString(4);
				String email = rs.getString(5);
				
				filiais.add(new FilialJavaBeans(id, sede, nome, telefone, email));
			}
			
			con.close();
			return filiais;
		} catch (Exception e) {
			return null;
		}
	}

}
