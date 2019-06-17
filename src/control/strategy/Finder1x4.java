package control.strategy;

import model.Tabuleiro;

public class Finder1x4 extends PatternFinderInterface{

	@Override
	public boolean patternFinder(String cor, Tabuleiro tabuleiro) {
		
		if (patternFinderVertical(cor, tabuleiro) || patternFinderHorizontal(cor,tabuleiro)) {
			return true;
		}
		return false;
	}
	
	private boolean patternFinderHorizontal(String cor, Tabuleiro tabuleiro) {
	for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				if (
						isValid(tabuleiro.getElementAt(j, i), cor)
						&& isValid(tabuleiro.getElementAt(j + 1, i), cor)
						&& isValid(tabuleiro.getElementAt(j + 2, i), cor)
						&& isValid(tabuleiro.getElementAt(j + 3, i), cor)){
					return true;
				}

			}
		}
		return false;
	}
	
	private boolean patternFinderVertical(String cor, Tabuleiro tabuleiro) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				if (
						isValid(tabuleiro.getElementAt(j, i), cor)
						&& isValid(tabuleiro.getElementAt(j, i+1), cor)
						&& isValid(tabuleiro.getElementAt(j, i+2), cor)
						&& isValid(tabuleiro.getElementAt(j, i+3), cor)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getPontuacao() {
		return 2;
	}

}
