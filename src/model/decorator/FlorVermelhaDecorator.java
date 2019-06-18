package model.decorator;

import model.VitoriaRegia;

//Decorador que adiciona uma flor vermelha na vitoria regia
public class FlorVermelhaDecorator extends VitoriaRegiaDecorator{

	public FlorVermelhaDecorator(VitoriaRegia vr) {	
		super(vr);
		this.nomeFlor = vr.getNomeFlor();
		this.ovas = vr.getOvas();
		this.aditivo = "FlorVermelha";
	}

	@Override
	public String getFlorCor() {
		return "vermelha";
	}
	
	
	@Override
	public boolean isHasFlor() {
		return true;
	}


}
