package control.state;

import control.Observador;

//Classe do estado onde o jogador chama o vento da primavera
public class VentoDaPrimaveraState extends GameState {

	public VentoDaPrimaveraState(Observador view) {
		super(view);
		loadState();
	}

	public VentoDaPrimaveraState(Observador view, GameState state) {
		super(view, state);
		loadState();
	}

	// proximo estado
	@Override
	public void nextState() {
		this.view.setState(new MoverPecasState(this.view, this));
	}

	// atualiza a view com os botoes disponiveis nesse estado
	@Override
	public void loadState() {
		System.out.println("Vento state");
		this.view.notificarVentoDisponivel(true);
	}

	@Override
	public String toString() {
		return "Vento";
	}

}
