package model;

//classe da peça da vitória régia que possue dois lados escuros
public class Vr_Escura extends VitoriaRegia {
	
	public Vr_Escura() {
		super("imagens/Vr_Escura.png");
	}

	@Override
	public void adicionarFlorAmarela() {
		setImagem("imagens/Vr_EscuraFlorAmarela.png");
		this.hasFlor = true;
		corFlor = "amarela";
	}

	@Override
	public void adicionarFlorVermelha() {
		setImagem("imagens/Vr_EscuraFlorVermelha.png");
		this.hasFlor = true;
		corFlor = "vermelha";
		
	}

	@Override
	public void reset() {
		this.hasFlor = false;
		setImagem("imagens/Vr_Escura.png");
		corFlor = "";
	}

	@Override
	public void desvirar() {
		// N precisa
		
	}

	@Override
	public void virar() {
		// N precisa
		
	}

	@Override
	public String removerSapo() {
		// N precisa
		return null;
	}

	@Override
	public void removerFlor() {
		// N precisa
		
	}

}
