package model;

//classe da peça vitória régia clara
public class Vr_Clara extends VitoriaRegia {
	boolean hasSapo = false;
	String sapo;

	public Vr_Clara() {
		super("imagens/Vr_Clara.png");
	}
	//retorna se a Vr possue um sapo em cima
	public boolean hasSapo() {
		return this.hasSapo;
	}
	//adiciona um sapo vermelho
	public void adicionarSapoVermelho() {
			this.hasSapo = true;
			setImagem("imagens/Vr_ClaraSapoVermelho.png");
			this.sapo = "vermelho";


	}
	//adiciona um sapo amarelo
	public void adicionarSapoAmarelo() {
			setImagem("imagens/Vr_ClaraSapoAmarelo.png");
			this.sapo = "amarelo";

		this.hasSapo = true;
	}

	@Override
	public void adicionarFlorAmarela() {
		if (isVirada) {
			setImagem("imagens/Vr_EscuraFlorAmarela.png");
		} else {
			setImagem("imagens/Vr_ClaraFlorAmarela.png");
		}
		corFlor = "amarela";
		this.hasFlor = true;
	}

	@Override
	public void adicionarFlorVermelha() {
		if (isVirada) {
			setImagem("imagens/Vr_EscuraFlorVermelha.png");
		} else {
			setImagem("imagens/Vr_ClaraFlorVermelha.png");
		}
		corFlor = "vermelha";
		this.hasFlor = true;
	}
	

	@Override
	public void reset() {
		this.hasFlor = false;
		this.hasSapo = false;
		this.sapo = null;
		corFlor = "";
		setImagem("imagens/Vr_Clara.png");
	}

	@Override
	public void desvirar() {
			setImagem("imagens/Vr_Clara.png");
			this.isVirada = false;
	}

	@Override
	public void virar() {
		setImagem("imagens/Vr_Escura.png");
		this.isVirada = true;
	}

	@Override
	public String removerSapo() {
		if (hasSapo) {
			this.hasSapo = false;

			setImagem("imagens/Vr_Clara.png");
			return this.sapo;
		}
		return null;

	}

	@Override
	public void removerFlor() {
			this.hasFlor = false;
			corFlor = "";
			setImagem("imagens/Vr_Clara.png");
	}

}
