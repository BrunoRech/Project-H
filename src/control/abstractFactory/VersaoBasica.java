package control.abstractFactory;

import model.Agua;
import model.Flor;
import model.FlorAmarela;
import model.FlorVermelha;
import model.VitoriaRegia;
import model.VitoriaRegiaComponent;
import model.Vr_Escura;
import model.decorator.OvasAmarelasDecorator;
import model.decorator.OvasVermelhasDecorator;
import model.decorator.SapoAmareloDecorator;
import model.decorator.SapoVermelhoDecorator;

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
		return new VitoriaRegiaComponent();
	}

	@Override
	public VitoriaRegia spawnVrClaraOvasAmarelas() {
		return new SapoAmareloDecorator(new OvasAmarelasDecorator(new VitoriaRegiaComponent()));
	}

	@Override
	public VitoriaRegia spawnVrClaraOvasVermelhas() {
		return new SapoVermelhoDecorator(new OvasVermelhasDecorator(new VitoriaRegiaComponent()));
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
