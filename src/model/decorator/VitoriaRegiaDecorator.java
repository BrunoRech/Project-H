package model.decorator;

import model.VitoriaRegia;

public abstract class VitoriaRegiaDecorator extends VitoriaRegia {

	private VitoriaRegia vr;

	public VitoriaRegiaDecorator(VitoriaRegia vr) {
		this.vr = vr;
	}

	@Override
	public String getFlorCor() {
		return vr.getFlorCor();
	}

	@Override
	public VitoriaRegia getVr() {
		return vr;
	}

	@Override
	public VitoriaRegia reset() {

		if (getVr().isHasFlor() || getVr().isVirada()) {//significa que ainda existe outro decorador
			return getVr().getVr();
		} else {
			return getVr();
		}
	}

}
