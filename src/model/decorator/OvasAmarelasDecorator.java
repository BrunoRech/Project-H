package model.decorator;

import model.VitoriaRegia;

public class OvasAmarelasDecorator extends VitoriaRegiaDecorator{

	public OvasAmarelasDecorator(VitoriaRegia vr) {
		super(vr);
		this.ovas = "OvasAmarelas";
		this.nomeFlor = vr.getNomeFlor();
		this.aditivo = vr.getAditivo();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}

}
