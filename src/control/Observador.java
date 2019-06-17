package control;

import control.state.GameState;

//interface do observador com a declaração de cada método de notificação que será usado
public interface Observador {

	void setState(GameState state);
	
	void nextState();
	
	void previousState();
	
	void reloadState();
	
	void sapoState(boolean empate);
	
	void notificarGanhadorDoJogo(String vencedor);
	
	void notificarMudouTabuleiro();
	
	void notificarVentoDisponivel(boolean disponivel);
	
	void notificarIconesAmarelos();
	
	void notificarIconesVermelhos();
	
	void notificarGanhadorDaRodada(int pontosAmarelo, int pontosVermelho);
	
	void notificarJogadorPescou(int[] mao);
	
	void notificarSapoHabilitado(boolean disponivel);
	
	void notificarEmpateFlor(); //coachar
	
	void notificarSelecaoFlorDisponivel(boolean disponivel);
	
	void notificarVirarFlorHabilitada(boolean disponivel);
	
	void notificarMovimentacaoHabilitada(boolean disponivel);
	
	void notificarSelecaoTabuleiroAprovada(boolean disponivel);
	
	void notificarAdicionarFlorHabilitado(boolean disponivel);
	
	void notificarDesfazerHabilitado(boolean disponivel);
}
