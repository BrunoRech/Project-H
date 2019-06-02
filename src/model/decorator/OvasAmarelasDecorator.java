package model.decorator;

import model.VitoriaRegia;

public class OvasAmarelasDecorator extends VitoriaRegiaDecorator{

	public OvasAmarelasDecorator(VitoriaRegia vr) {
		super(vr);
		adicionarSapoAmarelo();
	}

	@Override
	public void adicionarFlorAmarela() {
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasAmarelasFlorAmarela.png");
		getVitoriaRegia().setHasFlor(true);
		getVitoriaRegia().setCorFlor("amarela");
	}

	@Override
	public void adicionarFlorVermelha() {
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasAmarelasFlorVermelha.png");
		getVitoriaRegia().setHasFlor(true);
		getVitoriaRegia().setCorFlor("vermelha");
	}

	@Override
	public void reset() {
		getVitoriaRegia().setHasSapo(true);
		getVitoriaRegia().setHasFlor(false);
		getVitoriaRegia().setCorFlor("");
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasAmarelasSapoAmarelo.png");
	}

	@Override
	public void desvirar() {
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasAmarelas.png");
		getVitoriaRegia().setVirada(false);
	}

	@Override
	public void virar() {
		getVitoriaRegia().setImagem("imagens/Vr_EscuraOvasAmarela.png");
		getVitoriaRegia().setVirada(true);
	}

	@Override
	public String removerSapo() {

		if (getVitoriaRegia().isHasSapo()) {
			getVitoriaRegia().setHasSapo(false);
			getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasAmarelas.png");
			String cor = getVitoriaRegia().getSapo();
			getVitoriaRegia().setSapo("");
			return cor;
		}
		return null;
	}

	@Override
	public void removerFlor() {
		getVitoriaRegia().setHasFlor(false);
		getVitoriaRegia().setCorFlor("");
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasAmarelas.png");
	}
	
	public void adicionarSapoAmarelo() {
		getVitoriaRegia().setHasSapo(true);
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasAmarelasSapoAmarelo.png");
		getVitoriaRegia().setSapo("amarelo");
	}
	
	public void adicionarSapoVermelho() {
		getVitoriaRegia().setHasSapo(true);
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasAmarelasSapoVermelho.png");
		getVitoriaRegia().setSapo("vermelho");
	}

}
