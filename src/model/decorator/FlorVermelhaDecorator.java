package model.decorator;

import model.VitoriaRegia;

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
	public boolean hasFlor() {
		return true;
	}

	@Override
	public void reset() {}

}
