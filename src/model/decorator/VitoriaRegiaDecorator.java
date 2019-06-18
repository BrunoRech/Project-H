package model.decorator;

import model.VitoriaRegia;

//Classe abstrata dos decoradores das vitorias regias
public abstract class VitoriaRegiaDecorator extends VitoriaRegia {

	private VitoriaRegia vr;
	//construtor
	public VitoriaRegiaDecorator(VitoriaRegia vr) {
		this.vr = vr;
	}
	
	//Metodo que pega a cor da flor da vitoria regia
	@Override
	public String getFlorCor() {
		return vr.getFlorCor();
	}
	
	//Metodo que retorna a vitoria regia do decorador
	@Override
	public VitoriaRegia getVr() {
		return vr;
	}
	
	//Reseta a vitoria regia e retorna ao tabuleiro
	@Override
	public VitoriaRegia reset() {

		if (getVr().isHasFlor() || getVr().isVirada()) {//significa que ainda existe outro decorador
			return getVr().getVr();
		} else {
			return getVr();
		}
	}

}
