package control.state;

import control.Observador;

public abstract class GameStateInterface {
	
	protected Observador view;
	
	public GameStateInterface(Observador view) {
		this.view = view;
	}
	
	public abstract void nextState();
	
//	public abstract GameStateInterface selecionarFlores();
//	public abstract GameStateInterface ventoDaPrimavera();
//	public abstract GameStateInterface movimentarPecas();
//	public abstract GameStateInterface virarPecas();

	
}
