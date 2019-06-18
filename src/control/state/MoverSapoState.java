package control.state;

import control.Observador;
//Classe do estado de jogo onde o jogador move o sapo pelo tabuleiro
public class MoverSapoState extends GameState {

	public MoverSapoState(Observador view) {
		super(view);
		loadState();
	}
	
	public MoverSapoState(Observador view, GameState state) {
		super(view, state);
		loadState();
	}
	//proximo estado
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
	//atualiza a view com os botoes disponiveis nesse estado
	@Override
	public void loadState() {
		System.out.println("Mover sapo state");
		this.view.notificarVentoDisponivel(false);
		this.view.notificarSapoHabilitado(true);
		this.view.notificarDesfazerHabilitado(false);
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
