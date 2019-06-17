package control.strategy;

import model.Tabuleiro;

public class FinderDiagonal4 extends PatternFinderInterface{

	@Override
	public boolean patternFinder(String cor, Tabuleiro tabuleiro) {
		if(esqDir(cor,tabuleiro) || dirEsq(cor,tabuleiro)){
			return true;
		}
		return false;
	}
	
	private boolean dirEsq(String cor, Tabuleiro tabuleiro){
		if (
						isValid(tabuleiro.getElementAt(0, 3), cor)
						&& isValid(tabuleiro.getElementAt(1, 2), cor)
						&& isValid(tabuleiro.getElementAt(2, 1), cor)
						&& isValid(tabuleiro.getElementAt(3, 0), cor)
				|| (
						 isValid(tabuleiro.getElementAt(0, 4), cor)
						&& isValid(tabuleiro.getElementAt(1, 3), cor)
						&& isValid(tabuleiro.getElementAt(2, 2), cor)
						&& isValid(tabuleiro.getElementAt(3, 1), cor))
				|| (
						 isValid(tabuleiro.getElementAt(1, 3), cor))
						&& isValid(tabuleiro.getElementAt(2, 2), cor)
						&& isValid(tabuleiro.getElementAt(3, 1), cor)
						&& isValid(tabuleiro.getElementAt(4, 0), cor)
				|| (
						 isValid(tabuleiro.getElementAt(1, 4), cor)
						&& isValid(tabuleiro.getElementAt(2, 3), cor)
						&& isValid(tabuleiro.getElementAt(3, 2), cor)
						&& isValid(tabuleiro.getElementAt(4, 1), cor))) {
			return true;
		}
		return false;
	}
	
	private boolean esqDir(String cor, Tabuleiro tabuleiro){
		if (
						isValid(tabuleiro.getElementAt(1, 0), cor)
						&& isValid(tabuleiro.getElementAt(2, 1), cor)
						&& isValid(tabuleiro.getElementAt(3, 2), cor)
						&& isValid(tabuleiro.getElementAt(4, 3), cor)
				|| (
						isValid(tabuleiro.getElementAt(0, 0), cor)
						&& isValid(tabuleiro.getElementAt(1, 1), cor)
						&& isValid(tabuleiro.getElementAt(2, 2), cor)
						&& isValid(tabuleiro.getElementAt(3, 3), cor))
				|| (
						isValid(tabuleiro.getElementAt(1, 1), cor))
						&& isValid(tabuleiro.getElementAt(2, 2), cor)
						&& isValid(tabuleiro.getElementAt(3, 3), cor)
						&& isValid(tabuleiro.getElementAt(4, 4), cor)
				|| (
						isValid(tabuleiro.getElementAt(0, 1), cor)
						&& isValid(tabuleiro.getElementAt(1, 2), cor)
						&& isValid(tabuleiro.getElementAt(2, 3), cor)
						&& isValid(tabuleiro.getElementAt(3, 4), cor))) {
			return true;
		}
		return false;
	}
	@Override
	public int getPontuacao() {
		return 3;
	}

}
