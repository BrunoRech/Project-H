package model.decorator;

import model.VitoriaRegia;

public abstract class VitoriaRegiaDecorator extends VitoriaRegia{

	private VitoriaRegia vr;
	
	
	public VitoriaRegiaDecorator(VitoriaRegia vr) {
		this.vr = vr;
	}
	
	@Override
	public String getFlorCor() {
		return vr.getFlorCor();
	}
	
	public VitoriaRegia getVr() {
		return vr;
	}
	
	

}
