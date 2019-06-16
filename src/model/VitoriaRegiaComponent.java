package model;

//classe da peça vitória régia clara
public class VitoriaRegiaComponent extends VitoriaRegia {

	public VitoriaRegiaComponent() {
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Clara";
		setVirada(false);
	}
	//retorna se a Vr possue um sapo em cima
	public boolean hasSapo() {
		return this.hasSapo;
	}
	@Override
	public VitoriaRegia reset() {
		setVirada(false);
		setHasFlor(false);
		this.hasSapo = false;
		this.sapo = null;
		corFlor = "";
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Clara";
		return this;
	}

}
