package model;

//classe da peça da vitória régia que possue dois lados escuros
public class Vr_Escura extends VitoriaRegia {
	
	public Vr_Escura() {
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Escura";
		setVirada(true);
	}

	@Override
	public VitoriaRegia reset() {
		setHasFlor(false);
		setVirada(true);
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Escura";
		corFlor = "";
		return this;
	}


}
