package model;

//classe abstrata de da peça vitória régia
public abstract class VitoriaRegia extends GameObject{
	boolean hasFlor = false;
	boolean isVirada = false;
	//métodos abstratos
	public abstract void adicionarFlorAmarela();
	public abstract void adicionarFlorVermelha();
	public abstract void reset();
	public abstract void desvirar();
	public abstract void virar();
	public abstract String removerSapo();
	public abstract void removerFlor();
	
	//métodos em comum que não possuem variação
	public boolean isVirada() {
		return this.isVirada;
	}
	
	public VitoriaRegia(String imagem) {
		super(imagem);
	}
	
	public boolean hasFlor() {
		return this.hasFlor;
	}
	
	

}
