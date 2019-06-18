package control.strategy;

import model.Tabuleiro;
//Classe que procura o padrao de 5 vitorias regias na diagonal
public class FinderDiagonal5 extends PatternFinderInterface{
	
	//@return boolean, se achar o padrao true, caso contrario false
	@Override
	public boolean patternFinder(String cor, Tabuleiro tabuleiro) {
		if ((isValid(tabuleiro.getElementAt(0, 0), cor)
						&& isValid( tabuleiro.getElementAt(1, 1), cor)
						&& isValid(tabuleiro.getElementAt(2, 2), cor)
						&& isValid(tabuleiro.getElementAt(3, 3), cor)
						&& isValid(tabuleiro.getElementAt(4, 4), cor))
						
		|| (isValid(tabuleiro.getElementAt(0, 4), cor)
						&& isValid( tabuleiro.getElementAt(1, 3), cor)
						&& isValid(tabuleiro.getElementAt(2, 2), cor)
						&& isValid(tabuleiro.getElementAt(3, 1), cor)
						&& isValid(tabuleiro.getElementAt(4, 0), cor))
						
		)  {
			return true;
		}
		return false;
	}

	//@return integer, a pontuacao do padrao
	@Override
	public int getPontuacao() {
		return 5;
	}

}
