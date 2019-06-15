package model;

//classe da peça vitória régia clara
public class VitoriaRegiaComponent extends VitoriaRegia {

	public VitoriaRegiaComponent() {
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Clara";
	}
	//retorna se a Vr possue um sapo em cima
	public boolean hasSapo() {
		return this.hasSapo;
	}
	@Override
	public VitoriaRegia reset() {
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
