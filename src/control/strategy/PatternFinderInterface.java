package control.strategy;

import model.Agua;
import model.GameObject;
import model.Tabuleiro;
import model.VitoriaRegia;

//Classe abstrata do strategy de procurar padroes no tabuleiro
public abstract class PatternFinderInterface {
	
	//metodos abstratos
	public abstract boolean patternFinder(String cor, Tabuleiro tabuleiro);
	public abstract int getPontuacao();
	
	//metodo auxiliar que verifica se a peca e valida para verificacao do padrao
	protected boolean isValid(GameObject obj, String cor) {
		
		if (obj.getClass() != Agua.class){			
			if (((VitoriaRegia) obj).isHasFlor() && ((VitoriaRegia) obj).getFlorCor().equalsIgnoreCase(cor)) {
				return true;
			}
		}
		return false;
	}
	
	//metodo que e chamado para fazer a verificacao
	public void verificar(String cor, Tabuleiro tabuleiro) {
		patternFinder(cor, tabuleiro);
	}
	
	
	
	
}
