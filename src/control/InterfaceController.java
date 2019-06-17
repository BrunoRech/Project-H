package control;

import javax.swing.Icon;

//interface do controlador com a declaração de cada método que será usado
public interface InterfaceController extends Observado{

	void virarFlor();
	
	void nextState();
	
	void verificarGanhador();
	
	void previousState();
	
	void sapoState(boolean empate);
	
	void empate(String jogador);
	
	void atualizarIndexFlor(int x, int y);
	
	void inicializarTabuleiro() throws Exception;

	Icon getPeca(int col, int row) throws Exception;

	void addObservador(Observador obs);
	
	boolean tabuleiroCheio();
	
	void removerSaposTabuleiro();
	
	void removerFlorDaMao();

	void embaralharMontes();
	
	void escolherFlor(int valor);
	
	void adicionarFlor();
	
	void pescar(int quantidade);
	
	void adicionarSapo();
	
	void limparMesa();
	
	void ventoDaPrimavera();
	
	void verificarPadroes(int estrategia);

	void removerFlor();

	void removerSapo();

	void desvirarFlor();

	void mudarJogador();
	
	void validarExecute(String action);
	void validarUndo(String action);
	
	void moverPecasTabuleiroParaCima(String action);
	void moverPecasTabuleiroParaBaixo(String action);
	void moverPecasTabuleiroParaEsquerda(String action);
	void moverPecasTabuleiroParaDireita(String action);
	
	
}
