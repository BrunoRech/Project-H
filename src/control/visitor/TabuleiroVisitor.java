package control.visitor;

import model.Tabuleiro;
import model.VitoriaRegia;

public abstract class TabuleiroVisitor {

	protected boolean amareloPontuou;
	protected boolean vermelhoPontuou;

	public abstract void verificarPadraoAmarelo(Tabuleiro tabuleiro);

	public abstract void verificarPadraoVermelho(Tabuleiro tabuleiro);

	public boolean aux(VitoriaRegia vr, String cor) {
		if (vr.hasFlor() && vr.getFlorCor().equalsIgnoreCase(cor)) {
			return true;
		}
		return false;
	}

	public boolean amareloPontuou() {
		return amareloPontuou;
	}

	public boolean vermelhoPontuou() {
		return vermelhoPontuou;
	}
	
	protected abstract boolean patternFinder(String cor,Tabuleiro tabuleiro);
	public abstract int getPontuacao();
}
