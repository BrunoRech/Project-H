package control.builder;

import control.abstractFactory.Spawner;
import model.Tabuleiro;

//classe abstrata dos construtores de tabuleiro
public abstract class TabuleiroBuilder {
	public abstract void construirAgua();
	public abstract void construirVrClaras();
	public abstract void construirVrEscura();
	public abstract void construirVrOvasAmarela();
	public abstract void construirVrOvasVermelha();
	//@return Tabuleiro, o tabuleiro do jogo
	public abstract Tabuleiro getTabuleiro();
	public abstract void reset(Spawner spawner);
}
