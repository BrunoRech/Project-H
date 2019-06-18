package model.decorator;

import model.VitoriaRegia;

//Decorador que adiciona ovas amarelas na vitoria regia
public class OvasAmarelasDecorator extends VitoriaRegiaDecorator{

	public OvasAmarelasDecorator(VitoriaRegia vr) {
		super(vr);
		this.ovas = "OvasAmarelas";
		this.nomeFlor = vr.getNomeFlor();
		this.aditivo = vr.getAditivo();
	}


}
