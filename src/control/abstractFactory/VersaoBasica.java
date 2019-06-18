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

//classe do concrete factory da versao basica do jogo
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
	//@return vitoria regia padrao
	@Override
	public VitoriaRegia spawnVrClara() {
		return new VitoriaRegiaComponent();
	}
	//@return uma vitoria regia com decorador de ovas e sapo amarelo
	@Override
	public VitoriaRegia spawnVrClaraOvasAmarelas() {
		return new SapoAmareloDecorator(new OvasAmarelasDecorator(new VitoriaRegiaComponent()));
	}
	//@return uma vitoria regia com decorador de ovas e sapo vermelho
	@Override
	public VitoriaRegia spawnVrClaraOvasVermelhas() {
		return new SapoVermelhoDecorator(new OvasVermelhasDecorator(new VitoriaRegiaComponent()));
	}
	//@return uma vitoria regia escura
	@Override
	public VitoriaRegia spawnVrEscura() {
		return new Vr_Escura();
	}
	//@return agua
	@Override
	public Agua spawnAgua() {
		return new Agua();
	}

}
