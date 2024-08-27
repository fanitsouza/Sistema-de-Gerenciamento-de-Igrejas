package model;


import java.util.Date;

public class MembroJavaBeans {
	private int id_Membro;
	private int mbrsedeid;
	private String mbrnome;
	private String mbrcpf;
	private int mbrmsuperior;
	private String mbrnumero_identidade;
	private String mbremail;
	private String mbrtelefone;
	private Date mbrata_nascimento;
	private int mbrcivilId;
	private EnderecoJavaBeans mbrendereco;
	private String mbrfotoCaminho;
	private boolean mbrativo;
	private String mbracesso;
	private int mbrfilial;
	private String filialnome;
	private String mbrsenha;
	private int cargo;
	private String sedenome;
	private String supnome;
	private String estdescricao;
	private String cargoNome;
	
	
	
	
	public MembroJavaBeans(int id_Membro, String mbrnome) {
		this.id_Membro = id_Membro;
		this.mbrnome = mbrnome;
	}





	public MembroJavaBeans(int id_Membro, int mbrsedeid, String mbrmemnome, String mbrcpf, int mbrmsuperior,
			String mbrnumero_identidade, String mbremail, String mbrtelefone, Date mbrata_nascimento, int mbrcivilId,
			EnderecoJavaBeans mbrendereco, String mbrfotoCaminho, boolean mbrativo, String mbracesso, int mbrfilial,
			String mbrsenha, int cargo) {
		super();
		this.id_Membro = id_Membro;
		this.mbrsedeid = mbrsedeid;
		this.mbrnome = mbrmemnome;
		this.mbrcpf = mbrcpf;
		this.mbrmsuperior = mbrmsuperior;
		this.mbrnumero_identidade = mbrnumero_identidade;
		this.mbremail = mbremail;
		this.mbrtelefone = mbrtelefone;
		this.mbrata_nascimento = mbrata_nascimento;
		this.mbrcivilId = mbrcivilId;
		this.mbrendereco = mbrendereco;
		this.mbrfotoCaminho = mbrfotoCaminho;
		this.mbrativo = mbrativo;
		this.mbracesso = mbracesso;
		this.mbrfilial = mbrfilial;
		this.mbrsenha = mbrsenha;
		this.cargo = cargo;
	}
	
	
	
	
	
	public MembroJavaBeans(int id_Membro, String mbrnome, String mbrcpf, String mbremail, String sedenome,
			String supnome, String cargoNome, String filialnome) {
		this.id_Membro = id_Membro;
		this.mbrnome = mbrnome;
		this.mbrcpf = mbrcpf;
		this.mbremail = mbremail;
		this.sedenome = sedenome;
		this.supnome = supnome;
		this.cargoNome = cargoNome;
		this.filialnome = filialnome;
		
	}


	public MembroJavaBeans(String mbrnome, String mbrfotoCaminho, String mbracesso) {
		this.mbrnome = mbrnome;
		this.mbrfotoCaminho = mbrfotoCaminho;
		this.mbracesso = mbracesso;
	}

	public MembroJavaBeans(int id_Membro, String mbrmemnome, String mbrcpf, String mbremail, String sedenome, String supnome) {
		this.id_Membro = id_Membro;
		this.mbrnome = mbrmemnome;
		this.sedenome = sedenome;
		this.mbrcpf = mbrcpf;
		this.mbremail = mbremail;
		this.supnome = supnome;
	}
	
	public String getFilialnome() {
		return filialnome;
	}

	public void setFilialnome(String filialnome) {
		this.filialnome = filialnome;
	}

	public String getMbrnome() {
		return mbrnome;
	}

	public void setMbrnome(String mbrnome) {
		this.mbrnome = mbrnome;
	}

	public String getCargoNome() {
		return cargoNome;
	}

	public void setCargoNome(String cargoNome) {
		this.cargoNome = cargoNome;
	}

	public String getEstdescricao() {
		return estdescricao;
	}

	public void setEstdescricao(String estdescricao) {
		this.estdescricao = estdescricao;
	}

	public String getSedenome() {
		return sedenome;
	}

	public void setSedenome(String sedenome) {
		this.sedenome = sedenome;
	}

	public String getSupnome() {
		return supnome;
	}

	public void setSupnome(String supnome) {
		this.supnome = supnome;
	}

	
	public int getCargo() {
		return cargo;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	
	public String getMbrsenha() {
		return mbrsenha;
	}
	public void setMbrsenha(String mbrsenha) {
		this.mbrsenha = mbrsenha;
	}
	
	public int getMbrfilial() {
		return mbrfilial;
	}
	public void setMbrfilial(int mbrfilial) {
		this.mbrfilial = mbrfilial;
	}

	public boolean isMbrativo() {
		return mbrativo;
	}
	public void setMbrativo(boolean mbrativo) {
		this.mbrativo = mbrativo;
	}
	public String getMbracesso() {
		return mbracesso;
	}
	public void setMbracesso(String mbracesso) {
		this.mbracesso = mbracesso;
	}
	public MembroJavaBeans() {
		super();  
	}
	public MembroJavaBeans(int id_Membro, int mbrsedeid, String mbrmemnome, String mbrcpf, int mbrmsuperior,
			String mbrnumero_identidade, String mbremail, String mbrtelefone, Date mbrata_nascimento, int mbrcivilId,
			EnderecoJavaBeans mbrendereco, String mbrfotoCaminho, boolean mbrativ) {
		super();
		this.id_Membro = id_Membro;
		this.mbrsedeid = mbrsedeid;
		this.mbrnome = mbrmemnome;
		this.mbrcpf = mbrcpf;
		this.mbrmsuperior = mbrmsuperior;
		this.mbrnumero_identidade = mbrnumero_identidade;
		this.mbremail = mbremail;
		this.mbrtelefone = mbrtelefone;
		this.mbrata_nascimento = mbrata_nascimento;
		this.mbrcivilId = mbrcivilId;
		this.mbrendereco = mbrendereco;
		this.mbrfotoCaminho = mbrfotoCaminho;
		this.mbrativo = mbrativ;
	}
	public MembroJavaBeans(String id, String nome, String foto, String ativo, Date data) {
		this.id_Membro = Integer.parseInt(id);
		this.mbrnome = nome;
		this.mbrfotoCaminho = foto;
		this.mbrativo = Boolean.parseBoolean(ativo);
		this.mbrata_nascimento =data;
	
	}
	public int getId_Membro() {
		return id_Membro;
	}
	public void setId_Membro(int id_Membro) {
		this.id_Membro = id_Membro;
	}
	public int getMbrsedeid() {
		return mbrsedeid;
	}
	public void setMbrsedeid(int mbrsedeid) {
		this.mbrsedeid = mbrsedeid;
	}
	public String getMbrmemnome() {
		return mbrnome;
	}
	public void setMbrmemnome(String mbrmemnome) {
		this.mbrnome = mbrmemnome;
	}
	public String getMbrcpf() {
		return mbrcpf;
	}
	public void setMbrcpf(String mbrcpf) {
		this.mbrcpf = mbrcpf;
	}
	public int getMbrmsuperior() {
		return mbrmsuperior;
	}
	public void setMbrmsuperior(int mbrmsuperior) {
		this.mbrmsuperior = mbrmsuperior;
	}
	public String getMbrnumero_identidade() {
		return mbrnumero_identidade;
	}
	public void setMbrnumero_identidade(String mbrnumero_identidade) {
		this.mbrnumero_identidade = mbrnumero_identidade;
	}
	public String getMbremail() {
		return mbremail;
	}
	public void setMbremail(String mbremail) {
		this.mbremail = mbremail;
	}
	public String getMbrtelefone() {
		return mbrtelefone;
	}
	public void setMbrtelefone(String mbrtelefone) {
		this.mbrtelefone = mbrtelefone;
	}
	public Date getMbrata_nascimento() {
		return mbrata_nascimento;
	}
	public void setMbrata_nascimento(Date mbrata_nascimento) {
		this.mbrata_nascimento = mbrata_nascimento;
	}
	public int getMbrcivilId() {
		return mbrcivilId;
	}
	public void setMbrcivilId(int mbrcivilId) {
		this.mbrcivilId = mbrcivilId;
	}
	public EnderecoJavaBeans getMbrendereco() {
		return mbrendereco;
	}
	public void setMbrendereco(EnderecoJavaBeans mbrendereco) {
		this.mbrendereco = mbrendereco;
	}
	
	public String getMbrfotoCaminho() {
		return mbrfotoCaminho;
	}
	public void setMbrfotoCaminho(String mbrfotoCaminho) {
		this.mbrfotoCaminho = mbrfotoCaminho;
	}

	
	
}
