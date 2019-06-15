package control.strategy;

import model.Tabuleiro;
import model.VitoriaRegia;

public abstract class PatternFinderInterface {
	
	public abstract boolean patternFinder(String cor, Tabuleiro tabuleiro);
	public abstract int getPontuacao();
	
	
	protected boolean isValid(VitoriaRegia vr, String cor) {
		if (vr.isHasFlor() && vr.getFlorCor().equalsIgnoreCase(cor)) {
			return true;
		}
		return false;
	}

	public void verificar(String cor, Tabuleiro tabuleiro) {
		patternFinder(cor, tabuleiro);
	}
	
	
	
	
}
