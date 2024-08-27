package model;

import java.util.Date;

public class DoacoesJavaBeans {
	private String iddoacao;
	private String doanomedoador;
	private String doamembroid;
	private String doadescricao;
	private Date doadtdoacao;
	private double doavalor;
	private String status;
	private String doaidfilial;
	
	public DoacoesJavaBeans() {
		super();
	}

	public DoacoesJavaBeans(String iddoacao, String doanomedoador, String doamembroid, String doadescricao,
			Date doadtdoacao, double doavalor, String status, String doaidfilial) {
		super();
		this.iddoacao = iddoacao;
		this.doanomedoador = doanomedoador;
		this.doamembroid = doamembroid;
		this.doadescricao = doadescricao;
		this.doadtdoacao = doadtdoacao;
		this.doavalor = doavalor;
		this.status = status;
		this.doaidfilial = doaidfilial;
	}

	public String getIddoacao() {
		return iddoacao;
	}

	public void setIddoacao(String iddoacao) {
		this.iddoacao = iddoacao;
	}

	public String getDoanomedoador() {
		return doanomedoador;
	}

	public void setDoanomedoador(String doanomedoador) {
		this.doanomedoador = doanomedoador;
	}

	public String getDoamembroid() {
		return doamembroid;
	}

	public void setDoamembroid(String doamembroid) {
		this.doamembroid = doamembroid;
	}

	public String getDoadescricao() {
		return doadescricao;
	}

	public void setDoadescricao(String doadescricao) {
		this.doadescricao = doadescricao;
	}

	public Date getDoadtdoacao() {
		return doadtdoacao;
	}

	public void setDoadtdoacao(Date doadtdoacao) {
		this.doadtdoacao = doadtdoacao;
	}

	public double getDoavalor() {
		return doavalor;
	}

	public void setDoavalor(double doavalor) {
		this.doavalor = doavalor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoaidfilial() {
		return doaidfilial;
	}

	public void setDoaidfilial(String doaidfilial) {
		this.doaidfilial = doaidfilial;
	}

}