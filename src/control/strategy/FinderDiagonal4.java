package control.strategy;

import model.Agua;
import model.Tabuleiro;
import model.VitoriaRegia;

public class FinderDiagonal4 extends PatternFinderInterface{

	@Override
	public boolean patternFinder(String cor, Tabuleiro tabuleiro) {
		if ((tabuleiro.getElementAt(1, 0).getClass() != Agua.class
				&& isValid((VitoriaRegia) tabuleiro.getElementAt(1, 0), cor))
				&& (tabuleiro.getElementAt(2, 1).getClass() != Agua.class
						&& isValid((VitoriaRegia) tabuleiro.getElementAt(2, 1), cor))
				&& (tabuleiro.getElementAt(3, 2).getClass() != Agua.class
						&& isValid((VitoriaRegia) tabuleiro.getElementAt(3, 2), cor))
				&& (tabuleiro.getElementAt(4, 3).getClass() != Agua.class
						&& isValid((VitoriaRegia) tabuleiro.getElementAt(4, 3), cor))

				|| ((tabuleiro.getElementAt(0, 0).getClass() != Agua.class
						&& isValid((VitoriaRegia) tabuleiro.getElementAt(0, 0), cor))
						&& (tabuleiro.getElementAt(1, 1).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(1, 1), cor))
						&& (tabuleiro.getElementAt(2, 2).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(2, 2), cor))
						&& (tabuleiro.getElementAt(3, 3).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(3, 3), cor)))

				|| ((tabuleiro.getElementAt(1, 1).getClass() != Agua.class
						&& isValid((VitoriaRegia) tabuleiro.getElementAt(1, 1), cor))
						&& (tabuleiro.getElementAt(2, 2).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(2, 2), cor))
						&& (tabuleiro.getElementAt(3, 3).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(3, 3), cor))
						&& (tabuleiro.getElementAt(4, 4).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(4, 4), cor)))

				|| ((tabuleiro.getElementAt(0, 1).getClass() != Agua.class
						&& isValid((VitoriaRegia) tabuleiro.getElementAt(0, 1), cor))
						&& (tabuleiro.getElementAt(1, 2).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(1, 2), cor))
						&& (tabuleiro.getElementAt(2, 3).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(2, 3), cor))
						&& (tabuleiro.getElementAt(3, 4).getClass() != Agua.class
								&& isValid((VitoriaRegia) tabuleiro.getElementAt(3, 4), cor)))) {
			return true;
		}
		return false;
	}

	@Override
	public int getPontuacao() {
		return 3;
	}

}
