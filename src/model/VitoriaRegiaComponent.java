package model;

//classe da peça vitória régia clara
public class VitoriaRegiaComponent extends VitoriaRegia {

	public VitoriaRegiaComponent() {
		this.ovas = "";
		this.aditivo = "";
		this.nomeFlor = "Vr_Clara";
		
		setImagem(this.getImagem());
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
		
		setImagem(this.getImagem());
		return this;
	}

//	@Override
//	public void desvirar() {
//			setImagem("imagens/Vr_Clara.png");
//			setVirada(false);
//	}
//
//	@Override
//	public void virar() {
//		setImagem("imagens/Vr_Escura.png");
//		setVirada(true);
//	}

//	@Override
//	public String removerSapo() {
//		if (hasSapo) {
//			this.hasSapo = false;
//
//			setImagem("imagens/Vr_Clara.png");
//			return this.sapo;
//		}
//		return null;
//
//	}
//
//	@Override
//	public void removerFlor() {
//			setHasFlor(true);
//			corFlor = "";
//			setImagem("imagens/Vr_Clara.png");
//	}

}
