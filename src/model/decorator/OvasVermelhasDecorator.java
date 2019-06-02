package model.decorator;

import model.VitoriaRegia;

public class OvasVermelhasDecorator extends VitoriaRegiaDecorator{

	public OvasVermelhasDecorator(VitoriaRegia vr) {
		super(vr);
		vr.setImagem("imagens/Vr_ClaraOvasVermelhas.png");
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
		setImagem("imagens/Vr_ClaraOvasVermelhasSapoAmarelo.png");
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
			return getVitoriaRegia().getSapo();
		}
		return null;
	}

	@Override
	public void removerFlor() {
		getVitoriaRegia().removerFlor();
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhas.png");
	}
	
	public void adicionarSapoAmarelo() {
		getVitoriaRegia().setHasSapo(true);
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhasSapoAmarelo.png");
		getVitoriaRegia().setSapo("vermelho");
	}
	
	public void adicionarSapoVermelho() {
		getVitoriaRegia().setHasSapo(true);
		getVitoriaRegia().setImagem("imagens/Vr_ClaraOvasVermelhasSapoVermelho.png");
		getVitoriaRegia().setSapo("vermelho");
	}

}
