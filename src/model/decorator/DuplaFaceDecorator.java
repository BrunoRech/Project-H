package model.decorator;

import model.VitoriaRegia;

public class DuplaFaceDecorator extends VitoriaRegiaDecorator{

	public DuplaFaceDecorator(VitoriaRegia vr) {
		super(vr);
		getVitoriaRegia().setImagem("imagens/Vr_Escura.png");
	}

	@Override
	public void adicionarFlorAmarela() {
		getVitoriaRegia().setImagem("imagens/Vr_EscuraFlorAmarela.png");
		getVitoriaRegia().setHasFlor(true);
		getVitoriaRegia().setCorFlor("amarela");
	}

	@Override
	public void adicionarFlorVermelha() {
		getVitoriaRegia().setImagem("imagens/Vr_EscuraFlorVermelha.png");
		getVitoriaRegia().setHasFlor(true);
		getVitoriaRegia().setCorFlor("vermelha");	
	}

	@Override
	public void reset() {
		getVitoriaRegia().setHasFlor(false);
		getVitoriaRegia().setImagem("imagens/Vr_Escura.png");
		getVitoriaRegia().setCorFlor("");	
	}
	
	@Override
	public void adicionarSapoVermelho() {}

	@Override
	public void adicionarSapoAmarelo() {}

	@Override
	public void desvirar() {}

	@Override
	public void virar() {}

	@Override
	public String removerSapo() {return null;}

	@Override
	public void removerFlor() {}

}
