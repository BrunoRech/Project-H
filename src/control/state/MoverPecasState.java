package control.state;

import control.Observador;

public class MoverPecasState extends GameStateInterface{

	public MoverPecasState(Observador view) {
		super(view);
		loadState();
	}

	public MoverPecasState(Observador view, GameStateInterface state) {
		super(view, state);
		loadState();
	}
	
	@Override
	public void nextState() {
		this.view.setState(new VirarState(this.view, this));
	}

	@Override
	public void loadState() {
		System.out.println("Mover pecas state");
		this.view.notificarDesfazerHabilitado(true);
		this.view.notificarSelecaoFlorDisponivel(false);
		this.view.notificarMovimentacaoHabilitada(true);
		this.view.notificarSelecaoTabuleiroAprovada(true);
		this.view.notificarVirarFlorHabilitada(false);
	}

	@Override
	public String toString() {
		return "Mover";
	}

}
