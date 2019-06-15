package model.decorator;

import model.VitoriaRegia;

public class OvasVermelhasDecorator extends VitoriaRegiaDecorator{

	public OvasVermelhasDecorator(VitoriaRegia vr) {
		super(vr);
		this.ovas = "OvasVermelhas";
		this.nomeFlor = vr.getNomeFlor();
		this.aditivo = vr.getAditivo();
	}



}
