package model;

//classe da peca vitoria regia clara
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

}
