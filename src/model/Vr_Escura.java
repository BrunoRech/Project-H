package model;

//classe da peça da vitória régia que possue dois lados escuros
public class Vr_Escura extends VitoriaRegia {
	
	public Vr_Escura() {
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Escura";
	}

	@Override
	public VitoriaRegia reset() {
		setHasFlor(false);
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Escura";
		corFlor = "";
		return this;
	}


}
