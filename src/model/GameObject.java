package model;

//classe abstrata de uma peça do jogo padrão
public abstract class GameObject {

	private String imagem;
	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String i) {
		this.imagem = i;
	}
	
}
