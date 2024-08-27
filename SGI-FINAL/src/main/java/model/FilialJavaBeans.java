package model;

public class FilialJavaBeans {
	

	private String idfilial;
	private String filsedeid;
	private String filnome;
	private String filtefone;
	private String filemail;
	
	public FilialJavaBeans(){}
	
	public FilialJavaBeans(String idfilial, String filsedeid, String filnome, String filtefone, String filemail) {
		this.idfilial = idfilial;
		this.filsedeid = filsedeid;
		this.filnome = filnome;
		this.filtefone = filtefone;
		this.filemail = filemail;
	}


	public String getIdfilial() {
		return idfilial;
	}

	public void setIdfilial(String idfilial) {
		this.idfilial = idfilial;
	}

	public String getFilsedeid() {
		return filsedeid;
	}

	public void setFilsedeid(String filsedeid) {
		this.filsedeid = filsedeid;
	}

	public String getFilnome() {
		return filnome;
	}

	public void setFilnome(String filnome) {
		this.filnome = filnome;
	}

	public String getFiltefone() {
		return filtefone;
	}

	public void setFiltefone(String filtefone) {
		this.filtefone = filtefone;
	}

	public String getFilemail() {
		return filemail;
	}

	public void setFilemail(String filemail) {
		this.filemail = filemail;
	}
	
	
	
	
	

}
