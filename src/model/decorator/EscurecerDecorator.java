package model.decorator;

import model.VitoriaRegia;
//Decorador que escurece a vitoria regia
public class EscurecerDecorator extends VitoriaRegiaDecorator{

	public EscurecerDecorator(VitoriaRegia vr) {
		super(vr);
		this.nomeFlor = "Vr_Escura";
		this.ovas = vr.getOvas();
		this.aditivo = vr.getAditivo();
		setVirada(true);
	}

}
