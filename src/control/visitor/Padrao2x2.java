package control.visitor;

import model.Agua;
import model.Tabuleiro;
import model.VitoriaRegia;

public class Padrao2x2 extends TabuleiroVisitor {

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
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ((tabuleiro.getElementAt(j, i).getClass() != Agua.class
						&& aux((VitoriaRegia) tabuleiro.getElementAt(j, i), cor))
						&& (tabuleiro.getElementAt(j + 1, i).getClass() != Agua.class
								&& aux((VitoriaRegia) tabuleiro.getElementAt(j + 1, i), cor))
						&& (tabuleiro.getElementAt(j, i + 1).getClass() != Agua.class
								&& aux((VitoriaRegia) tabuleiro.getElementAt(j, i + 1), cor))
						&& (tabuleiro.getElementAt(j + 1, i + 1).getClass() != Agua.class
								&& aux((VitoriaRegia) tabuleiro.getElementAt(j + 1, i + 1), cor))) {
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
