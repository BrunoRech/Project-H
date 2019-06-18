package model.decorator;

import model.VitoriaRegia;

//Decorador que adiciona um sapo vermelho na vitoria regia
public class SapoVermelhoDecorator extends VitoriaRegiaDecorator{

	public SapoVermelhoDecorator(VitoriaRegia vr) {
		super(vr);
		this.ovas = vr.getOvas();
		this.nomeFlor = vr.getNomeFlor();
		this.aditivo = "SapoVermelho";
		
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
