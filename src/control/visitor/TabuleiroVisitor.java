package control.visitor;

import control.strategy.PatternFinderInterface;
import model.Tabuleiro;

public abstract class TabuleiroVisitor {

	
	protected PatternFinderInterface finder;
	
	public TabuleiroVisitor(PatternFinderInterface finder) {
		this.finder = finder;
	}
	
	public abstract void visitar(Tabuleiro tabuleiro);

	public int getPontuacao() {
		return this.finder.getPontuacao();
	}
}
