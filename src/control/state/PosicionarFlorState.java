package control.state;

import control.Observador;
//Classe do estado onde o jogador posiciona as flores no tabuleiro
public class PosicionarFlorState extends GameState{

	public PosicionarFlorState(Observador view) {
		super(view);
		loadState();
	}
	
	public PosicionarFlorState(Observador view, GameState state) {
		super(view, state);
		loadState();
	}
	//proximo estado
	@Override
	public void nextState() {
		this.view.setState(new VentoDaPrimaveraState(this.view, this));
	}
	
	//atualiza a view com os botoes disponiveis nesse estado
	@Override
	public void loadState() {
		System.out.println("posicionar flores state");
		this.view.notificarSapoHabilitado(false);
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
