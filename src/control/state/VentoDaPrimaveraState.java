package control.state;

import control.Observador;

public class VentoDaPrimaveraState extends GameStateInterface{

	public VentoDaPrimaveraState(Observador view) {
		super(view);
		loadState();
	}
	
	public VentoDaPrimaveraState(Observador view, GameStateInterface state) {
		super(view, state);
		loadState();
	}

	@Override
	public void nextState() {
		this.view.setState(new MoverPecasState(this.view, this));	
	}

	@Override
	public void loadState() {
		System.out.println("Vento state");
		this.view.notificarVentoDisponivel(true);
		this.view.notificarDesfazerHabilitado(true);
	}

	@Override
	public String toString() {
		return "Vento";
	}

}
