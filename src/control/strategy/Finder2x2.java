package control.strategy;

import model.Tabuleiro;
//Classe que procura o padrao do quadrado de 4 vitorias regias no tabuleiro
public class Finder2x2 extends PatternFinderInterface{
	
	//@return boolean, se achar o padrao true, caso contrario false
	@Override
	public boolean patternFinder(String cor, Tabuleiro tabuleiro) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (
					    isValid(tabuleiro.getElementAt(j, i), cor)
						&& isValid(tabuleiro.getElementAt(j + 1, i), cor)
						&& isValid(tabuleiro.getElementAt(j, i + 1), cor)
						&& isValid(tabuleiro.getElementAt(j + 1, i + 1), cor)) {
							
							return true;
				}
			}
		}
		return false;
	}
	
	//@return integer, a pontuacao do padrao
	@Override
	public int getPontuacao() {
		return 1;
	}

}
