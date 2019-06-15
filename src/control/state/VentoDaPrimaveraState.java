package control.state;

import control.Observador;

public class VentoDaPrimaveraState extends GameStateInterface{

	public VentoDaPrimaveraState(Observador view) {
		super(view);
		System.out.println("Vento state");
		this.view.notificarVentoDisponivel();
		this.view.notificarMudouTabuleiro();
	}

	@Override
	public void nextState() {
		this.view.setState(new MoverPecasState(this.view));	
	}

}
