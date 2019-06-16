package control.state;

import control.Observador;

public class VirarState extends GameStateInterface{

	public VirarState(Observador view) {
		super(view);
		loadState();	
	}
	
	public VirarState(Observador view, GameStateInterface state) {
		super(view, state);
		loadState();
	}

	@Override
	public void nextState() {
		this.view.setState(new SelecionarFloresState(this.view,this));
	}

	@Override
	public void loadState() {
		System.out.println("Virar Flor state");
		this.view.notificarDesfazerHabilitado(true);
		this.view.notificarSelecaoFlorDisponivel(false);
		this.view.notificarMovimentacaoHabilitada(false);
		this.view.notificarSelecaoTabuleiroAprovada(true);
		this.view.notificarVirarFlorHabilitada(true);
	}

	@Override
	public String toString() {
		return "Virar";
	}

}
