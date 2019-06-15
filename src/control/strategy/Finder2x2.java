package control.strategy;

import model.Agua;
import model.Tabuleiro;
import model.VitoriaRegia;

public class Finder2x2 extends PatternFinderInterface{

	@Override
	public boolean patternFinder(String cor, Tabuleiro tabuleiro) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ((tabuleiro.getElementAt(j, i).getClass() != Agua.class
						&& isValid((VitoriaRegia) tabuleiro.getElementAt(j, i), cor))
						&& (tabuleiro.getElementAt(j + 1, i).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(j + 1, i), cor))
						&& (tabuleiro.getElementAt(j, i + 1).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(j, i + 1), cor))
						&& (tabuleiro.getElementAt(j + 1, i + 1).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(j + 1, i + 1), cor))) {
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
