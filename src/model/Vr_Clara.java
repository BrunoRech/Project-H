package model;

//classe da peça vitória régia clara
public class Vr_Clara extends VitoriaRegia {

	public Vr_Clara() {
		setImagem("imagens/Vr_Clara.png");
	}
	//retorna se a Vr possue um sapo em cima
	public boolean hasSapo() {
		return this.isHasSapo();
	}
	//adiciona um sapo vermelho
	public void adicionarSapoVermelho() {
			this.setHasSapo(true);
			setImagem("imagens/Vr_ClaraSapoVermelho.png");
			this.setSapo("vermelho");
	}
	//adiciona um sapo amarelo
	public void adicionarSapoAmarelo() {
			setImagem("imagens/Vr_ClaraSapoAmarelo.png");
			this.setSapo("amarelo");

		this.setHasSapo(true);
	}

	@Override
	public void adicionarFlorAmarela() {
		if (isVirada()) {
			setImagem("imagens/Vr_EscuraFlorAmarela.png");
		} else {
			setImagem("imagens/Vr_ClaraFlorAmarela.png");
		}
		setCorFlor("amarela");
		this.setHasFlor(true);
	}

	@Override
	public void adicionarFlorVermelha() {
		if (isVirada()) {
			setImagem("imagens/Vr_EscuraFlorVermelha.png");
		} else {
			setImagem("imagens/Vr_ClaraFlorVermelha.png");
		}
		setCorFlor("vermelha");
		this.setHasFlor(true);
	}
	

	@Override
	public void reset() {
		this.setHasFlor(false);
		this.setHasSapo(false);
		this.setSapo(null);
		setCorFlor("");
		setImagem("imagens/Vr_Clara.png");
	}

	@Override
	public void desvirar() {
			setImagem("imagens/Vr_Clara.png");
			this.setVirada(false);
	}

	@Override
	public void virar() {
		setImagem("imagens/Vr_Escura.png");
		this.setVirada(true);
	}

	@Override
	public String removerSapo() {
		if (isHasSapo()) {
			this.setHasSapo(false);

			setImagem("imagens/Vr_Clara.png");
			return this.getSapo();
		}
		return null;

	}

	@Override
	public void removerFlor() {
			this.setHasFlor(false);
			setCorFlor("");
			setImagem("imagens/Vr_Clara.png");
	}

}
