package model;

import java.util.Date;

public class PatrimonioJavaBeans {
	private String idpatrimonio;
	private String ptrnome;
	private String ptridfil;
	private String ptrdescricao;
	private double ptrvalor;
	private Date ptrdataaquisicao;
	private String ptrestado;
	
	
	public PatrimonioJavaBeans() {
		super();
		
	}
	
	public PatrimonioJavaBeans(String idpatrimonio, String ptrnome, String ptridfil, String ptrdescricao, double ptrvalor,
			Date ptrdataaquisicao, String ptrestado) {
		super();
		this.idpatrimonio = idpatrimonio;
		this.ptrnome = ptrnome;
		this.ptridfil = ptridfil;
		this.ptrdescricao = ptrdescricao;
		this.ptrvalor = ptrvalor;
		this.ptrdataaquisicao = ptrdataaquisicao;
		this.ptrestado = ptrestado;
	}
	
	public String getIdpatrimonio() {
		return idpatrimonio;
	}
	public void setIdpatrimonio(String idpatrimonio) {
		this.idpatrimonio = idpatrimonio;
	}
	public String getPtrnome() {
		return ptrnome;
	}
	public void setPtrnome(String ptrnome) {
		this.ptrnome = ptrnome;
	}
	public String getPtridfil() {
		return ptridfil;
	}
	public void setPtridfil(String ptridfil) {
		this.ptridfil = ptridfil;
	}
	public String getPtrdescricao() {
		return ptrdescricao;
	}
	public void setPtrdescricao(String ptrdescricao) {
		this.ptrdescricao = ptrdescricao;
	}
	public double getPtrvalor() {
		return ptrvalor;
	}
	public void setPtrvalor(double ptrvalor) {
		this.ptrvalor = ptrvalor;
	}
	public Date getPtrdataaquisicao() {
		return ptrdataaquisicao;
	}
	public void setPtrdataaquisicao(Date ptrdataaquisicao) {
		this.ptrdataaquisicao = ptrdataaquisicao;
	}
	public String getPtrestado() {
		return ptrestado;
	}
	public void setPtrestado(String ptrestado) {
		this.ptrestado = ptrestado;
	}
	
}
