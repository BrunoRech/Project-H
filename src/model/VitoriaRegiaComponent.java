package model;

//classe da peça vitória régia clara
public class VitoriaRegiaComponent extends VitoriaRegia {

	public VitoriaRegiaComponent() {
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Clara";
		
		setImagem(this.getImagem());
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


	public void adicionarFlorAmarela() {
		if (isVirada()) {
			setImagem("imagens/Vr_EscuraFlorAmarela.png");
		} else {
			setImagem("imagens/Vr_ClaraFlorAmarela.png");
		}
		corFlor = "amarela";
		setHasFlor(true);
	}

//	@Override
	public void adicionarFlorVermelha() {
		if (isVirada()) {
			setImagem("imagens/Vr_EscuraFlorVermelha.png");
		} else {
			setImagem("imagens/Vr_ClaraFlorVermelha.png");
		}
		corFlor = "vermelha";
		setHasFlor(true);
	}
	

	@Override
	public void reset() {
		setHasFlor(true);
		this.hasSapo = false;
		this.sapo = null;
		corFlor = "";
		setImagem("imagens/Vr_Clara.png");
	}

//	@Override
//	public void desvirar() {
//			setImagem("imagens/Vr_Clara.png");
//			setVirada(false);
//	}
//
//	@Override
//	public void virar() {
//		setImagem("imagens/Vr_Escura.png");
//		setVirada(true);
//	}

//	@Override
//	public String removerSapo() {
//		if (hasSapo) {
//			this.hasSapo = false;
//
//			setImagem("imagens/Vr_Clara.png");
//			return this.sapo;
//		}
//		return null;
//
//	}
//
//	@Override
//	public void removerFlor() {
//			setHasFlor(true);
//			corFlor = "";
//			setImagem("imagens/Vr_Clara.png");
//	}

}
