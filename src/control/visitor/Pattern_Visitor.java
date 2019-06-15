package control.visitor;

import control.strategy.PatternFinderInterface;
import model.Tabuleiro;

public class Pattern_Visitor extends TabuleiroVisitor {
	
	protected boolean amareloPontuou;
	protected boolean vermelhoPontuou;
	protected PatternFinderInterface finder;

	public boolean amareloPontuou() {
		return amareloPontuou;
	}

	public boolean vermelhoPontuou() {
		return vermelhoPontuou;
	}
	
	public void setStrategy(PatternFinderInterface finder) {
		this.finder = finder;
	}
	
	public int getPontuacao(){
		return this.finder.getPontuacao();
	}

	@Override
	public void visitar(Tabuleiro tabuleiro) {
		verificarPadraoAmarelo(tabuleiro);
		verificarPadraoVermelho(tabuleiro);
	}

	private void verificarPadraoVermelho(Tabuleiro tabuleiro) {
		this.vermelhoPontuou = this.finder.patternFinder("vermelha", tabuleiro);
	}

	private void verificarPadraoAmarelo(Tabuleiro tabuleiro) {
		this.amareloPontuou = this.finder.patternFinder("amarela", tabuleiro);

	}

}
