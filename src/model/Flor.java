package model;

//objeto abstrato da peça flor
public abstract class Flor extends GameObject{

	public Flor(int numero,String imagem) {
		this.setImagem(imagem);
		this.numero = numero;
		
	}
	
	private int numero;
	
	public int getNumero() {
		return this.numero;
	}

}
