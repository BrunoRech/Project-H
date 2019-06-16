package control.state;

import control.Observador;

public class PosicionarFlorState extends GameStateInterface{

	public PosicionarFlorState(Observador view) {
		super(view);
		loadState();
	}
	
	public PosicionarFlorState(Observador view, GameStateInterface state) {
		super(view, state);
		loadState();
	}

	@Override
	public void nextState() {
		this.view.setState(new VentoDaPrimaveraState(this.view, this));
	}

	@Override
	public void loadState() {
		System.out.println("posicionar flores state");
		this.view.notificarSapoHabilitado(false);
		this.view.notificarDesfazerHabilitado(false);
		this.view.notificarVentoDisponivel(false);
		this.view.notificarAdicionarFlorHabilitado(true);
		this.view.notificarSelecaoTabuleiroAprovada(true);
		this.view.notificarSelecaoFlorDisponivel(false);
	}

	@Override
	public String toString() {
		return "PosicionarFlores";
	}

}
