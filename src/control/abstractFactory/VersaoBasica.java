package control.abstractFactory;

import model.Agua;
import model.Flor;
import model.FlorAmarela;
import model.FlorVermelha;
import model.VitoriaRegia;
import model.Vr_Clara;
import model.Vr_ClaraOvasAmarelas;
import model.Vr_ClaraOvasVermelhas;
import model.Vr_Escura;

//classe do concrete factory da versão básica do jogo
public class VersaoBasica extends Spawner{

	@Override
	//@param numero, o "peso" da flor
	public Flor spawnFlorAmarela(int numero) {
		return new FlorAmarela(numero);
	}

	@Override
	//@param numero, o "peso" da flor
	public Flor spawnFlorVermelha(int numero) {
		return new FlorVermelha(numero);
	}

	@Override
	public VitoriaRegia spawnVrClara() {
		return new Vr_Clara();
	}

	@Override
	public VitoriaRegia spawnVrClaraOvasAmarelas() {
		return new Vr_ClaraOvasAmarelas();
	}

	@Override
	public VitoriaRegia spawnVrClaraOvasVermelhas() {
		return new Vr_ClaraOvasVermelhas();
	}

	@Override
	public VitoriaRegia spawnVrEscura() {
		return new Vr_Escura();
	}

	@Override
	public Agua spawnAgua() {
		return new Agua();
	}

}
