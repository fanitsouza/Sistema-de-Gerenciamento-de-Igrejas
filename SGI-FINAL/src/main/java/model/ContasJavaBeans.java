package model;

import java.util.Date;

public class ContasJavaBeans {
	private String idcontapagar;
	private String cntnomefornecedor;
	private String cntidfilial;
	private String cntdescricaoconta;
	private Date cntdtvencimento;
	private double cntvalor;
	private String cntstatuspagamento;
	
	//Construtores usados para iniciar automaticamente o objeto criado
	//Construtor padrão, cria um objeto sem passar parametro
	public ContasJavaBeans() {
		super();
	}
	//Criar um objeto já passando os dados
	public ContasJavaBeans(String idcontapagar, String cntnomefornecedor, String cntidfilial, String cntdescricaoconta,
			Date cntdtvencimento, double cntvalor, String cntstatuspagamento) {
		super();
		this.idcontapagar = idcontapagar;
		this.cntnomefornecedor = cntnomefornecedor;
		this.cntidfilial = cntidfilial;
		this.cntdescricaoconta = cntdescricaoconta;
		this.cntdtvencimento = cntdtvencimento;
		this.cntvalor = cntvalor;
		this.cntstatuspagamento = cntstatuspagamento;
	}

	public String getIdcontapagar() {
		return idcontapagar;
	}
	public void setIdcontapagar(String idcontapagar) {
		this.idcontapagar = idcontapagar;
	}
	public String getCntnomefornecedor() {
		return cntnomefornecedor;
	}
	public void setCntnomefornecedor(String cntnomefornecedor) {
		this.cntnomefornecedor = cntnomefornecedor;
	}
	public String getCntidfilial() {
		return cntidfilial;
	}
	public void setCntidfilial(String cntidfilial) {
		this.cntidfilial = cntidfilial;
	}
	public String getCntdescricaoconta() {
		return cntdescricaoconta;
	}
	public void setCntdescricaoconta(String cntdescricaoconta) {
		this.cntdescricaoconta = cntdescricaoconta;
	}
	public Date getCntdtvencimento() {
		return cntdtvencimento;
	}
	public void setCntdtvencimento(Date cntdtvencimento) {
		this.cntdtvencimento = cntdtvencimento;
	}
	public double getCntvalor() {
		return cntvalor;
	}
	public void setCntvalor(double cntvalor) {
		this.cntvalor = cntvalor;
	}
	public String getCntstatuspagamento() {
		return cntstatuspagamento;
	}
	public void setCntstatuspagamento(String cntstatuspagamento) {
		this.cntstatuspagamento = cntstatuspagamento;
	}

}
