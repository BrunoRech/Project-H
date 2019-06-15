package control.state;

import control.Observador;

public class SelecionarFloresState extends GameStateInterface{

	public SelecionarFloresState(Observador view) {
		super(view);
		System.out.println("Selecionar Flores state");
		this.view.notificarSelecaoFlorDisponivel();
		this.view.notificarVentoIndisponivel();
		this.view.notificarMovimentacaoDesabilitada();
		this.view.notificarSapoDesabilitado();
		this.view.notificarVirarFlorDesabilitada();
		this.view.notificarAdicionarFlorDesabilitado();
		this.view.notificarDesfazerDesabilitado();
		this.view.notificarRefazerDesabilitado();
		this.view.notificarSelecaoTabuleiroReprovada();
		
	}

	@Override
	public void nextState() {
		this.view.setState(new PosicionarState(this.view));
	}

}
