package control.state;

import control.Observador;

public class VirarState extends GameStateInterface{

	public VirarState(Observador view) {
		super(view);
		System.out.println("Virar Flore state");
		this.view.notificarSelecaoFlorIndisponivel();
		this.view.notificarMovimentacaoDesabilitada();
		this.view.notificarSelecaoTabuleiroAprovada();
		this.view.notificarVirarFlorHabilitada();
	}

	@Override
	public void nextState() {
		this.view.setState(new SelecionarFloresState(this.view));
	}

}
