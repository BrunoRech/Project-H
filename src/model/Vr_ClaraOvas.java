package model;

//classe abstrata da peça vitória régia clara mas que possue ovas de sapo
public abstract class Vr_ClaraOvas extends Vr_Clara{
	private String cor;
	
	public abstract void adicionarSapoAmarelo();
	public abstract void adicionarSapoVermelho();
	
	public Vr_ClaraOvas(String cor, String imagem) {
		super();
		this.cor = cor;
	}
	
	public String getCor() {
		return this.cor;
	}

	

}
