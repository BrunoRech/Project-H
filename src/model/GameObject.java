package model;

//classe abstrata de uma peca do jogo padrao
public abstract class GameObject {

	private String imagem;
	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String i) {
		this.imagem = i;
	}
	
}
