package control.state;

import control.Observador;

public class PosicionarState extends GameStateInterface{

	public PosicionarState(Observador view) {
		super(view);
		System.out.println("pos");
		this.view.notificarAdicionarFlorHabilitado();
		this.view.notificarSelecaoTabuleiroAprovada();
		this.view.notificarSelecaoFlorIndisponivel();
	}

	@Override
	public void nextState() {
		this.view.setState(new VentoDaPrimaveraState(this.view));
	}

}
