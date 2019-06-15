package control;

import control.state.GameStateInterface;

//interface do observador com a declaração de cada método de notificação que será usado
public interface Observador {

	void setState(GameStateInterface state);
	
	void nextState();
	
	void notificarMudouTabuleiro();
	
	void notificarVentoIndisponivel();
	
	void notificarVentoDisponivel();
	
	void notificarIconesAmarelos();
	
	void notificarIconesVermelhos();
	
	void notificarGanhadorDaRodada(int pontosAmarelo, int pontosVermelho);
	
	void notificarJogadorPescou(int[] mao);
	
	void notificarSapoHabilitado();
	
	void notificarSapoDesabilitado();
	
	void notificarEmpateFlor(); //coachar

	void notificarFlorAdicionada();
	
	void notificarExecute();
	
	void notificarSapoAdicionado();
	
	void notificarSelecaoFlorIndisponivel();
	
	void notificarSelecaoFlorDisponivel();
	
	void notificarFlorVirada();
	
	void notificarVirarFlorHabilitada();
	
	void notificarVirarFlorDesabilitada();
	
	void notificarMovimentacaoHabilitada();
	
	void notificarMovimentacaoDesabilitada();
	
	void notificarSelecaoTabuleiroAprovada();
	
	void notificarSelecaoTabuleiroReprovada();
	
	void notificarAdicionarFlorHabilitado();
	
	void notificarAdicionarFlorDesabilitado();
	
	void notificarDesfazerDesabilitado();
	
	void notificarRefazerDesabilitado();

}
