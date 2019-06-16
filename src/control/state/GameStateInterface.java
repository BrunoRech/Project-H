package control.state;

import control.Observador;

public abstract class GameStateInterface {

	protected Observador view;
	protected GameStateInterface antecessor;

	public abstract String toString();

	public abstract void loadState();

	public abstract void nextState();

	public GameStateInterface(Observador view) {
		this.view = view;
	}

	protected GameStateInterface(Observador view, GameStateInterface antecessor) {
		this.view = view;
		this.antecessor = antecessor;
	}

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
