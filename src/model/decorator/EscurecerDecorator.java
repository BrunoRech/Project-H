package model.decorator;

import model.VitoriaRegia;

public class EscurecerDecorator extends VitoriaRegiaDecorator{

	public EscurecerDecorator(VitoriaRegia vr) {
		super(vr);
		this.nomeFlor = "Vr_Escura";
		this.ovas = vr.getOvas();
		this.aditivo = vr.getAditivo();
	}

	
	@Override
	public boolean isVirada() {
		return true;
	}


}
