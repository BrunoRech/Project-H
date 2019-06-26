package control.state;

import control.Observador;
//Classe do estado de jogo onde o jogador move as pecas
public class MoverPecasState extends GameState{

	public MoverPecasState(Observador view) {
		super(view);
		loadState();
	}

	public MoverPecasState(Observador view, GameState state) {
		super(view, state);
		loadState();
	}
	
	//proximo estado
	@Override
	public void nextState() {
		this.view.setState(new VirarState(this.view, this));
	}
	
	//atualiza a view com os botoes disponiveis nesse estado
	@Override
	public void loadState() {
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
