package model;

//objeto abstrato da peça flor
public abstract class Flor extends GameObject{

	public Flor(int numero,String imagem) {
		super(imagem);
		this.numero = numero;
		
	}
	
	private int numero;
	
	public int getNumero() {
		return this.numero;
	}

}
