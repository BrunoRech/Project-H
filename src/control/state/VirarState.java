package control.state;

import control.Observador;
//Classe do estado onde o jogador vira uma vitoria regia
public class VirarState extends GameState{

	public VirarState(Observador view) {
		super(view);
		loadState();	
	}
	
	public VirarState(Observador view, GameState state) {
		super(view, state);
		loadState();
	}
	
	//proximo estado
	@Override
	public void nextState() {
		this.view.setState(new SelecionarFloresState(this.view,this));
	}
	
	//atualiza a view com os botoes disponiveis nesse estado
	@Override
	public void loadState() {
		System.out.println("Virar Flor state");
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
