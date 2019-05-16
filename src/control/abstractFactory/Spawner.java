package control.abstractFactory;

import model.Agua;
import model.Flor;
import model.VitoriaRegia;

//classe do abstract factory das peças do jogo
public abstract class Spawner {
	//agua
	public abstract Agua spawnAgua();
	
	//flores
	//@param numero, o "peso" da flor
	public abstract Flor spawnFlorAmarela(int numero);
	public abstract Flor spawnFlorVermelha(int numero);
	
	//vitórias régias
	public abstract VitoriaRegia spawnVrClara();
	public abstract VitoriaRegia spawnVrClaraOvasAmarelas();
	public abstract VitoriaRegia spawnVrClaraOvasVermelhas();	
	public abstract VitoriaRegia spawnVrEscura();
	
}
