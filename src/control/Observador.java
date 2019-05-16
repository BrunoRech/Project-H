package control;

//interface do observador com a declaração de cada método de notificação que será usado
public interface Observador {

	void notificarMudouTabuleiro();
	
	void notificarVentoIndisponivel();
	
	void notificarVentoDisponivel();
	
	void notificarIconesAmarelos();
	
	void notificarIconesVermelhos();
	
	void notificarGanhadorDaRodada(String ganhador, int pontos);
	
	void notificarJogadorPescou(int[] mao);
	
	void notificarSapoHabilitado();
	
	void notificarEmpateFlor(); //coachar

	void notificarFlorAdicionada();
	
	void notificarExecute();
	
	void notificarSapoAdicionado();
	
	void notificarSelecaoFlorIndisponivel();
	
	void notificarSelecaoFlorDisponivel();
	
	void notificarFlorVirada();
	
	void notificarVirarFlorHabilitada();
	
	void notificarMovimentacaoHabilitada();
	
	void notificarMovimentacaoDesabilitada();
	
	void notificarSelecaoTabuleiroAprovada();
	
	void notificarSelecaoTabuleiroReprovada();
	
	void notificarAdicionarFlorHabilitado();

}
