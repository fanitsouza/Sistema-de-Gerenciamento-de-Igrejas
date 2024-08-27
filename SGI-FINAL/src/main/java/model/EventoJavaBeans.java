package model;

import java.sql.Date;

import java.sql.Time;

public class EventoJavaBeans {

		private String idevento;
	   private String evetitulo;
	   private String evedescricao;
	   private Date evedata;
	   private Time evehora;
	   private String evelocailacao;
	   private String evestatus;
	   private String evefilial;
	   

	   public EventoJavaBeans() {
	   }

	 
	   
	   
	   
	   public EventoJavaBeans(String evetitulo, Date evedata, Time evehora) {
		this.evetitulo = evetitulo;
		this.evedata = evedata;
		this.evehora = evehora;
	}





	public EventoJavaBeans(String evetitulo, String evedescricao, Date evedata, Time evehora, String evelocailacao,
			String evestatus, String evefilial) {
		this.evetitulo = evetitulo;
		this.evedescricao = evedescricao;
		this.evedata = evedata;
		this.evehora = evehora;
		this.evelocailacao = evelocailacao;
		this.evestatus = evestatus;
		this.evefilial = evefilial;
	}





	public EventoJavaBeans(String idevento, String evetitulo, String evedescricao, Date evedata, Time evehora,
			String evelocailacao, String evestatus, String evefilial) {
		this.idevento = idevento;
		this.evetitulo = evetitulo;
		this.evedescricao = evedescricao;
		this.evedata = evedata;
		this.evehora = evehora;
		this.evelocailacao = evelocailacao;
		this.evestatus = evestatus;
		this.evefilial = evefilial;
	}





	public String getIdevento() {
	      return this.idevento;
	   }

	   public void setIdevento(String idevento) {
	      this.idevento = idevento;
	   }

	   public String getEvetitulo() {
	      return this.evetitulo;
	   }

	   public void setEvetitulo(String evetitulo) {
	      this.evetitulo = evetitulo;
	   }

	   public String getEvedescricao() {
	      return this.evedescricao;
	   }

	   public void setEvedescricao(String evedescricao) {
	      this.evedescricao = evedescricao;
	   }

	   public Date getEvedata() {
	      return this.evedata;
	   }

	   public void setEvedata(Date evedata) {
	      this.evedata = evedata;
	   }

	   public Time getEvehora() {
	      return this.evehora;
	   }

	   public void setEvehora(Time evehora) {
	      this.evehora = evehora;
	   }



	public String getEvelocailacao() {
		return evelocailacao;
	}



	public void setEvelocailacao(String evelocailacao) {
		this.evelocailacao = evelocailacao;
	}



	public String getEvestatus() {
		return evestatus;
	}



	public void setEvestatus(String evestatus) {
		this.evestatus = evestatus;
	}



	public String getEvefilial() {
		return evefilial;
	}



	public void setEvefilial(String evefilial) {
		this.evefilial = evefilial;
	}

	  
}
