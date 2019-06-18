package control.visitor;

import control.strategy.PatternFinderInterface;
import model.Tabuleiro;

//Classe do visitor que vai procurar os padroes de pontuacao dentro do tabuleiro
public class Pattern_Visitor extends TabuleiroVisitor {
	
	protected boolean amareloPontuou;
	protected boolean vermelhoPontuou;
	protected PatternFinderInterface finder;
	
	//retorna se o amarelo pontuou na verificacao
	public boolean amareloPontuou() {
		return amareloPontuou;
	}

	//retorna se o vermelho pontuou na verificacao
	public boolean vermelhoPontuou() {
		return vermelhoPontuou;
	}
	
	//setter da estrategia que o visitor ira utilizar na verificacao dos padroes
	public void setStrategy(PatternFinderInterface finder) {
		this.finder = finder;
	}
	
	//pega a pontuacao do padrao de acordo com a estrategia utilizada
	public int getPontuacao(){
		return this.finder.getPontuacao();
	}
	
	//metodo que faz a visita no tabuleiro
	@Override
	public void visitar(Tabuleiro tabuleiro) {
		verificarPadraoAmarelo(tabuleiro);
		verificarPadraoVermelho(tabuleiro);
	}
	
	//metodo que verifica se encontrou algum padrao na cor vermelha
	private void verificarPadraoVermelho(Tabuleiro tabuleiro) {
		this.vermelhoPontuou = this.finder.patternFinder("vermelha", tabuleiro);
	}

	//metodo que verifica se encontrou algum padrao na cor amarela
	private void verificarPadraoAmarelo(Tabuleiro tabuleiro) {
		this.amareloPontuou = this.finder.patternFinder("amarela", tabuleiro);

	}

}
