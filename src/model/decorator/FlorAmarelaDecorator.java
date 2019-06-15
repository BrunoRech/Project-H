package model.decorator;

import model.VitoriaRegia;

public class FlorAmarelaDecorator extends VitoriaRegiaDecorator{

	public FlorAmarelaDecorator(VitoriaRegia vr) {	
		super(vr);
		this.nomeFlor = vr.getNomeFlor();
		this.ovas = vr.getOvas();
		this.aditivo = "FlorAmarela";
	}

	@Override
	public String getFlorCor() {
		return "amarela";
	}
	
	@Override
	public boolean isHasFlor() {
		return true;
	}



}
