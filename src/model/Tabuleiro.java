package model;

import control.abstractFactory.Spawner;

//classe do tabuleiro do jogo
public abstract class Tabuleiro {
	protected GameObject[][] tabuleiro;
	protected Spawner spawner;
	
	//seta o tipo de spawner que está sendo usado
	public void setSpawner(Spawner spawner) {
		this.spawner = spawner;
	}
	
	//pega o elemento na posição y e x
	public GameObject getElementAt(int y, int x) {
		return this.tabuleiro[y][x];
	}
	
	//seta o elemento na posição y e x
	public void setElementAt(GameObject object,int y, int x) {
		this.tabuleiro[y][x] = object;
	}
	
	//métodos abstratos que devem ser implementados nas classes derivadas desta
	public abstract void setAgua(int y, int x);
	public abstract void setVrClara(int y, int x);
	public abstract void setVrEscura(int y, int x);
	public abstract void setVrOvasAmarela(int y, int x);
	public abstract void setVrOvasVermelha(int y, int x);
}
