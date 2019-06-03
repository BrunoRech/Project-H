package model.decorator;

import model.VitoriaRegia;

public class SapoAmareloDecorator extends VitoriaRegiaDecorator{

	public SapoAmareloDecorator(VitoriaRegia vr) {
		super(vr);
		this.ovas = vr.getOvas();
		this.nomeFlor = vr.getNomeFlor();
		this.aditivo = "SapoAmarelo";
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isHasSapo() {
		return true;
	}
	
	@Override
	public String getSapo() {
		return "amarelo";
	}
	

}
