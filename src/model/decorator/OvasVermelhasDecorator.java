package model.decorator;

import model.VitoriaRegia;

public class OvasVermelhasDecorator extends VitoriaRegiaDecorator{

	public OvasVermelhasDecorator(VitoriaRegia vr) {
		super(vr);
		adicionarSapoVermelho();
	}

	@Override
	public void adicionarFlorAmarela() {
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhasFlorAmarela.png");
		getVitoriaRegia().setHasFlor(true);
		getVitoriaRegia().setCorFlor("amarela");
	}

	@Override
	public void adicionarFlorVermelha() {
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhasFlorVermelha.png");
		getVitoriaRegia().setHasFlor(true);
		getVitoriaRegia().setCorFlor("vermelha");
	}

	@Override
	public void reset() {
		getVitoriaRegia().setHasSapo(true);
		getVitoriaRegia().setHasFlor(false);
		getVitoriaRegia().setCorFlor("");
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhasSapoVermelho.png");
	}

	@Override
	public void desvirar() {
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhas.png");
		getVitoriaRegia().setVirada(false);
	}

	@Override
	public void virar() {
		getVitoriaRegia().setImagem("imagens/Vr_EscuraOvasVermelhas.png");
		getVitoriaRegia().setVirada(true);
	}

	@Override
	public String removerSapo() {

		if (getVitoriaRegia().isHasSapo()) {
			getVitoriaRegia().setHasSapo(false);
			getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhas.png");
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
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhas.png");
	}
	
	public void adicionarSapoAmarelo() {
		getVitoriaRegia().setHasSapo(true);
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhasSapoAmarelo.png");
		getVitoriaRegia().setSapo("amarelo");
	}
	
	public void adicionarSapoVermelho() {
		getVitoriaRegia().setHasSapo(true);
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhasSapoVermelho.png");
		getVitoriaRegia().setSapo("vermelho");
	}

}
