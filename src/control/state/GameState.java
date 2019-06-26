package control.state;

import control.Observador;

//Classe abstrata dos estados do jogo
public abstract class GameState {

	protected Observador view;
	protected GameState antecessor;
	
	//metodos abstratos
	public abstract String toString();

	public abstract void loadState();

	public abstract void nextState();
	
	//construtor
	public GameState(Observador view) {
		this.view = view;
	}
	
	//construtor que recebe o estado anterior
	protected GameState(Observador view, GameState antecessor) {
		this.view = view;
		this.antecessor = antecessor;
	}

}
