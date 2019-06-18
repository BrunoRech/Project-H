package control.builder;

import control.abstractFactory.VersaoBasica;
import model.Tabuleiro;
import model.TabuleiroPadrao;

//classe do concrete builder de tabuleiro padrao
public class TabuleiroPadraoBuilder extends TabuleiroBuilder{
	private Tabuleiro tabuleiro;

	@Override
	//adiciona os tiles de agua no tabuleiro
	public void construirAgua() {
		this.tabuleiro.setAgua(1, 0);
		this.tabuleiro.setAgua(3, 0);
		this.tabuleiro.setAgua(0, 1);
		this.tabuleiro.setAgua(4, 1);
		this.tabuleiro.setAgua(2, 2);
		this.tabuleiro.setAgua(0, 3);
		this.tabuleiro.setAgua(4, 3);
		this.tabuleiro.setAgua(1, 4);
		this.tabuleiro.setAgua(3, 4);
	}

	@Override
	//adiciona as vitorias regias claras no tabuleiro
	public void construirVrClaras() {
		this.tabuleiro.setVrClara(0,0);
		this.tabuleiro.setVrClara(2,0);
		this.tabuleiro.setVrClara(4,0);
		
		this.tabuleiro.setVrClara(1,1);
		
		this.tabuleiro.setVrClara(0,2);
		this.tabuleiro.setVrClara(1,2);
		this.tabuleiro.setVrClara(3,2);
		this.tabuleiro.setVrClara(4,2);
		
		this.tabuleiro.setVrClara(1,3);
		this.tabuleiro.setVrClara(2,3);
		
		this.tabuleiro.setVrClara(0,4);
		this.tabuleiro.setVrClara(2,4);
		this.tabuleiro.setVrClara(4,4);
	}

	@Override
	//adiciona as vitorias regias claras com ovas amarelas no tabuleiro
	public void construirVrOvasAmarela() {
		this.tabuleiro.setVrOvasAmarela(2, 1);
		
	}

	@Override
	//adiciona as vitorias regias claras com ovas vermelhas no tabuleiro
	public void construirVrOvasVermelha() {
		this.tabuleiro.setVrOvasVermelha(3, 3);
		
	}
	
	@Override
	//adiciona a vitoria regia totalmente escura no tabuleiro
	public void construirVrEscura() {
		this.tabuleiro.setVrEscura(3,1);
	}

	@Override
	//retorna o tabuleiro
	//@return Tabuleiro, o tabuleiro do jogo
	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}

	@Override
	//cria um novo tabuleiro e dá set no tipo de spawner
	public void reset() {
		this.tabuleiro = new TabuleiroPadrao();
		tabuleiro.setSpawner(new VersaoBasica());
	}
}
