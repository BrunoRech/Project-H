package control;

import javax.swing.Icon;

//interface do controlador com a declaração de cada método que será usado
public interface InterfaceController extends Observado{

	void virarFlor(String action);
	
	void atualizarIndexFlor(int x, int y);
	
	void inicializarTabuleiro() throws Exception;

	Icon getPeca(int col, int row) throws Exception;

	void addObservador(Observador obs);
	
	boolean tabuleiroCheio();
	
	void removerSaposTabuleiro();
	
	void removerFlorDaMao();

	void embaralharMontes();
	
	void escolherFlor(int valor);
	
	void adicionarFlor(String action);
	
	void pescar(int quantidade);
	
	void adicionarSapo(String action);
	
	void limparMesa();
	
	void ventoDaPrimavera();
	
	void verificarPadroes();

	void removerFlor(String action);

	void removerSapo(String action);

	void desvirarFlor(String action);

	void mudarJogador();
	
	void validarExecute(String action);
	void validarUndo(String action);
	
	void moverPecasTabuleiroParaCima(String action);
	void moverPecasTabuleiroParaBaixo(String action);
	void moverPecasTabuleiroParaEsquerda(String action);
	void moverPecasTabuleiroParaDireita(String action);
	
	
}
