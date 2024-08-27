package model;

import java.sql.Time;
import java.util.Date;

public class TarefasJavaBeans {
	
	private int tarefaid;
	private String tarefatitulo;
	private int tarefafilialid;
	private String trafilnome;
	private String	tarefadesc;
	private int trfmbrid;
	private String trfmbrnome;
	private Date tarefadata;
	private Time tarefahora;
	private	String status;
	
	
	
	public TarefasJavaBeans() {}
	
	
	
	



	public TarefasJavaBeans(int id, String tarefatitulo, String trafilnome, String tarefadesc, String trfmbrnome,
			Date tarefadata, Time tarefahora, String status) {
		super();
		this.tarefaid = id;
		this.tarefatitulo = tarefatitulo;
		this.trafilnome = trafilnome;
		this.tarefadesc = tarefadesc;
		this.trfmbrnome = trfmbrnome;
		this.tarefadata = tarefadata;
		this.tarefahora = tarefahora;
		this.status = status;
	}







	public TarefasJavaBeans(int tarefaid, String tarefatitulo, int tarefafilialid, String trafilnome, String tarefadesc,
			int trfmbrid, String trfmbrnome, Date tarefadata, Time tarefahora, String status) {
		super();
		this.tarefaid = tarefaid;
		this.tarefatitulo = tarefatitulo;
		this.tarefafilialid = tarefafilialid;
		this.trafilnome = trafilnome;
		this.tarefadesc = tarefadesc;
		this.trfmbrid = trfmbrid;
		this.trfmbrnome = trfmbrnome;
		this.tarefadata = tarefadata;
		this.tarefahora = tarefahora;
		this.status = status;
	}



	public int getTarefaid() {
		return tarefaid;
	}


	public void setTarefaid(int tarefaid) {
		this.tarefaid = tarefaid;
	}


	public String getTarefatitulo() {
		return tarefatitulo;
	}


	public void setTarefatitulo(String tarefatitulo) {
		this.tarefatitulo = tarefatitulo;
	}


	public int getTarefafilialid() {
		return tarefafilialid;
	}


	public void setTarefafilialid(int tarefafilialid) {
		this.tarefafilialid = tarefafilialid;
	}


	public String getTrafilnome() {
		return trafilnome;
	}


	public void setTrafilnome(String trafilnome) {
		this.trafilnome = trafilnome;
	}


	public String getTarefadesc() {
		return tarefadesc;
	}


	public void setTarefadesc(String tarefadesc) {
		this.tarefadesc = tarefadesc;
	}


	public int getTrfmbrid() {
		return trfmbrid;
	}


	public void setTrfmbrid(int trfmbrid) {
		this.trfmbrid = trfmbrid;
	}


	public String getTrfmbrnome() {
		return trfmbrnome;
	}


	public void setTrfmbrnome(String trfmbrnome) {
		this.trfmbrnome = trfmbrnome;
	}


	public Date getTarefadata() {
		return tarefadata;
	}


	public void setTarefadata(Date tarefadata) {
		this.tarefadata = tarefadata;
	}


	public Time getTarefahora() {
		return tarefahora;
	}


	public void setTarefahora(Time tarefahora) {
		this.tarefahora = tarefahora;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
