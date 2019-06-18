package model.decorator;

import model.VitoriaRegia;

//Decorador que adiciona um sapo amarelo na vitoria regia
public class SapoAmareloDecorator extends VitoriaRegiaDecorator{

	public SapoAmareloDecorator(VitoriaRegia vr) {
		super(vr);
		this.ovas = vr.getOvas();
		this.nomeFlor = vr.getNomeFlor();
		this.aditivo = "SapoAmarelo";
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
