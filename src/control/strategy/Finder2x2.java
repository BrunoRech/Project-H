package control.strategy;

import model.Tabuleiro;

public class Finder2x2 extends PatternFinderInterface{

	@Override
	public boolean patternFinder(String cor, Tabuleiro tabuleiro) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (
					    isValid(tabuleiro.getElementAt(j, i), cor)
						&& isValid(tabuleiro.getElementAt(j + 1, i), cor)
						&& isValid(tabuleiro.getElementAt(j, i + 1), cor)
						&& isValid(tabuleiro.getElementAt(j + 1, i + 1), cor)) {
							
							return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getPontuacao() {
		return 1;
	}

}
