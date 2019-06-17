package control.state;

import control.Observador;

public class MoverSapoState extends GameStateInterface {

	public MoverSapoState(Observador view) {
		super(view);
		loadState();
	}
	
	public MoverSapoState(Observador view, GameStateInterface state) {
		super(view, state);
		loadState();
	}
	
	@Override
	public void nextState() {
		switch (antecessor.toString()) {
		case "AdicionarFlores":
			this.view.setState(new VentoDaPrimaveraState(view, this));
			break;
		case "Virar":
			this.view.setState(new SelecionarFloresState(view, this));
			break;
		case "Sapo":
			this.view.setState(new SelecionarFloresState(view, this));
			break;
		}
	}

	@Override
	public void loadState() {
		System.out.println("Mover sapo state");
		this.view.notificarVentoDisponivel(false);
		this.view.notificarSapoHabilitado(true);
		this.view.notificarDesfazerHabilitado(true);
		this.view.notificarSelecaoFlorDisponivel(false);
		this.view.notificarMovimentacaoHabilitada(false);
		this.view.notificarSelecaoTabuleiroAprovada(true);
		this.view.notificarVirarFlorHabilitada(false);
	}

	@Override
	public String toString() {
		return "Sapo";
	}

}
