package model;

public class EnderecoJavaBeans {
	
	private int endid;
    private int endmbrid;
    private String endrua;
    private String endnumero;
    private String endbairro; 
    private String endcep;
    private String endcidade; 
    private String endestado;
    
    
    
    public EnderecoJavaBeans() {}
	public EnderecoJavaBeans(int endid, int endmbrid, String endrua, String endnumero, String endbairro, String endcep,
			String endcidade, String endestado) {
		super();
		this.endid = endid;
		this.endmbrid = endmbrid;
		this.endrua = endrua;
		this.endnumero = endnumero;
		this.endbairro = endbairro;
		this.endcep = endcep;
		this.endcidade = endcidade;
		this.endestado = endestado;
	}
	
	
	
	public int getEndid() {
		return endid;
	}
	public void setEndid(int endid) {
		this.endid = endid;
	}
	public int getEndmbrid() {
		return endmbrid;
	}
	public void setEndmbrid(int endmbrid) {
		this.endmbrid = endmbrid;
	}
	public String getEndrua() {
		return endrua;
	}
	public void setEndrua(String endrua) {
		this.endrua = endrua;
	}
	public String getEndnumero() {
		return endnumero;
	}
	public void setEndnumero(String endnumero) {
		this.endnumero = endnumero;
	}
	public String getEndbairro() {
		return endbairro;
	}
	public void setEndbairro(String endbairro) {
		this.endbairro = endbairro;
	}
	public String getEndcep() {
		return endcep;
	}
	public void setEndcep(String endcep) {
		this.endcep = endcep;
	}
	public String getEndcidade() {
		return endcidade;
	}
	public void setEndcidade(String endcidade) {
		this.endcidade = endcidade;
	}
	public String getEndestado() {
		return endestado;
	}
	public void setEndestado(String endestado) {
		this.endestado = endestado;
	}
    
    
    
    
    
}
