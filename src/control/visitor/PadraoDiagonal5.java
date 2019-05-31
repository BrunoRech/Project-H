package control.visitor;

import model.Agua;
import model.Tabuleiro;
import model.VitoriaRegia;

public class PadraoDiagonal5 extends TabuleiroVisitor {

	@Override
	public void verificarPadraoAmarelo(Tabuleiro tabuleiro) {
		amareloPontuou = patternFinder("amarela", tabuleiro);
	}

	@Override
	public void verificarPadraoVermelho(Tabuleiro tabuleiro) {
		vermelhoPontuou = patternFinder("vermelha", tabuleiro);
	}

	@Override
	protected boolean patternFinder(String cor, Tabuleiro tabuleiro) {
		if ((tabuleiro.getElementAt(0, 0).getClass() != Agua.class
				&& aux((VitoriaRegia) tabuleiro.getElementAt(0, 0), cor))
				&& (tabuleiro.getElementAt(1, 1).getClass() != Agua.class
						&& aux((VitoriaRegia) tabuleiro.getElementAt(1, 1), cor))
				&& (tabuleiro.getElementAt(2, 2).getClass() != Agua.class
						&& aux((VitoriaRegia) tabuleiro.getElementAt(2, 2), cor))
				&& (tabuleiro.getElementAt(3, 3).getClass() != Agua.class
						&& aux((VitoriaRegia) tabuleiro.getElementAt(3, 3), cor))
				&& (tabuleiro.getElementAt(4, 4).getClass() != Agua.class
						&& aux((VitoriaRegia) tabuleiro.getElementAt(4, 4), cor))) {
			return true;
		}
		return false;
	}

	@Override
	public int getPontuacao() {
		return 5;
	}

}
