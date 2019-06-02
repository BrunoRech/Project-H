package control.abstractFactory;

import model.Agua;
import model.Flor;
import model.FlorAmarela;
import model.FlorVermelha;
import model.VitoriaRegia;
import model.Vr_Clara;
import model.decorator.DuplaFaceDecorator;
import model.decorator.OvasAmarelasDecorator;
import model.decorator.OvasVermelhasDecorator;

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
		return new OvasAmarelasDecorator(new Vr_Clara());
	}

	@Override
	public VitoriaRegia spawnVrClaraOvasVermelhas() {
		return new OvasVermelhasDecorator(new Vr_Clara());
	}

	@Override
	public VitoriaRegia spawnVrEscura() {
		return new DuplaFaceDecorator(new Vr_Clara());
	}

	@Override
	public Agua spawnAgua() {
		return new Agua();
	}

}
