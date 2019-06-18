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
	
	//metodo que retorna ao estado anterior ao atual
	public void previousState() {
		if (antecessor != null) {
			switch (antecessor.toString()) {
			case "Mover":
				this.view.setState(new MoverPecasState(this.view));
				break;
			case "Sapo":
				this.view.setState(new MoverSapoState(this.view));
				break;
			case "PosicionarFlores":
				this.view.setState(new PosicionarFlorState(this.view));
				break;
			case "SelecionarFlores":
				this.view.setState(new SelecionarFloresState(this.view));
				break;
			case "Vento":
				this.view.setState(new VentoDaPrimaveraState(this.view));
				break;
			case "Virar":
				this.view.setState(new VirarState(this.view));
				break;

			default:
				break;
			}
		}
	}

}
