package control.strategy;

import model.Agua;
import model.GameObject;
import model.Tabuleiro;
import model.VitoriaRegia;

public abstract class PatternFinderInterface {
	
	public abstract boolean patternFinder(String cor, Tabuleiro tabuleiro);
	public abstract int getPontuacao();
	
	
	protected boolean isValid(GameObject obj, String cor) {
		
		if (obj.getClass() != Agua.class){			
			if (((VitoriaRegia) obj).isHasFlor() && ((VitoriaRegia) obj).getFlorCor().equalsIgnoreCase(cor)) {
				return true;
			}
		}
		return false;
	}

	public void verificar(String cor, Tabuleiro tabuleiro) {
		patternFinder(cor, tabuleiro);
	}
	
	
	
	
}
