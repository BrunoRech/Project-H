package control.state;

import control.Observador;
//Classe do estado onde o jogador escolhe 1 das 3 flores para competir
public class SelecionarFloresState extends GameState{

	public SelecionarFloresState(Observador view) {
		super(view);
		loadState();
	}

	public SelecionarFloresState(Observador view, GameState state) {
		super(view, state);
		loadState();
	}
	
	//proximo estado
	@Override
	public void nextState() {
		this.view.setState(new PosicionarFlorState(this.view,this));
	}
	//atualiza a view com os botoes disponiveis nesse estado
	@Override
	public void loadState() {
		System.out.println("Selecionar Flores state");
		this.view.notificarSelecaoFlorDisponivel(true);
		this.view.notificarVentoDisponivel(false);
		this.view.notificarMovimentacaoHabilitada(false);
		this.view.notificarSapoHabilitado(false);
		this.view.notificarVirarFlorHabilitada(false);
		this.view.notificarAdicionarFlorHabilitado(false);
		this.view.notificarSelecaoTabuleiroAprovada(false);
		
	}

	@Override
	public String toString() {
		return "SelecionarFlores";
	}

}
