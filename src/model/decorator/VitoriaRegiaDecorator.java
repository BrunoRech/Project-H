package model.decorator;

import model.VitoriaRegia;

public abstract class VitoriaRegiaDecorator extends VitoriaRegia{

	private VitoriaRegia vr;
	
	public VitoriaRegiaDecorator(VitoriaRegia vr) {
		this.vr = vr;
	}
	
	public VitoriaRegia getVitoriaRegia() {
		return vr;
	}
	
	@Override
	public String getImagem() {
		return vr.getImagem();
	}
	
	@Override
	public boolean isVirada() {
		return vr.isVirada();
	}
	
	@Override
	public boolean isHasFlor() {
		return vr.isHasFlor();
	}
	
	@Override
	public boolean isHasSapo() {
		return vr.isHasSapo();
	}
	
	@Override
	public String getSapo() {
		return vr.getSapo();
	}
	
	@Override
	public String getCorFlor() {
		return vr.getCorFlor();
	}
	
	

}
 