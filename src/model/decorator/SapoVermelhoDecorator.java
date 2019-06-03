package model.decorator;

import model.VitoriaRegia;

public class SapoVermelhoDecorator extends VitoriaRegiaDecorator{

	public SapoVermelhoDecorator(VitoriaRegia vr) {
		super(vr);
		this.ovas = vr.getOvas();
		this.nomeFlor = vr.getNomeFlor();
		this.aditivo = "SapoVermelho";
		
	}

	@Override
	public void reset() {
		
	}
	
	@Override
	public boolean isHasSapo() {
		return true;
	}
	
	@Override
	public String getSapo() {
		return "vermelho";
	}

}
