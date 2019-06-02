package model;

//classe abstrata de da peça vitória régia
public abstract class VitoriaRegia extends GameObject{
	private boolean hasFlor = false;
	private boolean isVirada = false;
	private boolean hasSapo = false;
	private String sapo;
	private String corFlor = "";
	//métodos abstratos
	public abstract void adicionarSapoVermelho();
	public abstract void adicionarSapoAmarelo();
	public abstract void adicionarFlorAmarela();
	public abstract void adicionarFlorVermelha();
	public abstract void desvirar();
	public abstract void virar();
	public abstract String removerSapo();
	public abstract void removerFlor();
	
	
	
	//métodos em comum que não possuem variação
	public boolean isVirada() {
		return this.isVirada;
	}
	
	public boolean hasFlor() {
		return this.isHasFlor();
	}
	
	public String getFlorCor() {
		return getCorFlor();
	}
	public boolean isHasSapo() {
		return hasSapo;
	}
	public void setHasSapo(boolean hasSapo) {
		this.hasSapo = hasSapo;
	}
	public String getSapo() {
		return sapo;
	}
	public void setSapo(String sapo) {
		this.sapo = sapo;
	}
	public void setVirada(boolean isVirada) {
		this.isVirada = isVirada;
	}
	public boolean isHasFlor() {
		return hasFlor;
	}
	public void setHasFlor(boolean hasFlor) {
		this.hasFlor = hasFlor;
	}
	public String getCorFlor() {
		return corFlor;
	}
	public void setCorFlor(String corFlor) {
		this.corFlor = corFlor;
	}
	
	

}
