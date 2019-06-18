package control.strategy;

import model.Tabuleiro;
//Classe que procura o padrao de 4 vitorias regias na diagonal
public class FinderDiagonal4 extends PatternFinderInterface{
	
	//@return boolean, se achar o padrao true, caso contrario false
	@Override
	public boolean patternFinder(String cor, Tabuleiro tabuleiro) {
		if(esqDir(cor,tabuleiro) || dirEsq(cor,tabuleiro)){
			return true;
		}
		return false;
	}
	
	//procura o padrao da diagonal com 4 vitorias regias no sentido da direita para a esquerda
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
	
	//procura o padrao da diagonal com 4 vitorias regias no sentido da esquerda para a direita
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
	
	//@return integer, a pontuacao do padrao
	@Override
	public int getPontuacao() {
		return 3;
	}

}
