package model;

//classe da peça da vitória régia que possue dois lados escuros
public class Vr_Escura extends VitoriaRegia {
	
	public Vr_Escura() {
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Escura";
		setImagem(this.getImagem());
	}

	public void adicionarFlorAmarela() {
		setImagem("imagens/Vr_EscuraFlorAmarela.png");
		setHasFlor(true);
		corFlor = "amarela";
	}

	//@Override
	public void adicionarFlorVermelha() {
		setImagem("imagens/Vr_EscuraFlorVermelha.png");
		setHasFlor(true);
		corFlor = "vermelha";
		
	}

	@Override
	public VitoriaRegia reset() {
		setHasFlor(false);
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Escura";
		setImagem(this.getImagem());
		corFlor = "";
		return this;
	}


}
