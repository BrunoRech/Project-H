package model;

//classe do tabuleiro padrão do jogo
public class TabuleiroPadrao extends Tabuleiro{
	
	
	public TabuleiroPadrao() {
		tabuleiro = new GameObject[5][5];
	}

	@Override
	public void setAgua(int y, int x) {
		this.tabuleiro[y][x] = spawner.spawnAgua();
	}

	@Override
	public void setVrClara(int y, int x) {
		this.tabuleiro[y][x] = spawner.spawnVrClara();
	}

	@Override
	public void setVrEscura(int y, int x) {
		this.tabuleiro[y][x] = spawner.spawnVrEscura();
	}

	@Override
	public void setVrOvasAmarela(int y, int x) {
		this.tabuleiro[y][x] = spawner.spawnVrClaraOvasAmarelas();
	}

	@Override
	public void setVrOvasVermelha(int y, int x) {
		this.tabuleiro[y][x] = spawner.spawnVrClaraOvasVermelhas();
	}
}
