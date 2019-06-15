package control.state;

import control.Observador;

public class MoverPecasState extends GameStateInterface{

	public MoverPecasState(Observador view) {
		super(view);
		System.out.println("Mover pecas state");
		this.view.notificarSelecaoFlorIndisponivel();
		this.view.notificarMovimentacaoHabilitada();
		this.view.notificarSelecaoTabuleiroAprovada();
		this.view.notificarVirarFlorDesabilitada();
	}

	@Override
	public void nextState() {
		this.view.setState(new VirarState(this.view));
	}

}
