package control.builder;

//classe do diretor que comanda os construtores
public class Director {
	private TabuleiroBuilder builder;
	
	//construtor da classe
	//@param builder, o construtor que sera utilizado no metodo construir
	public Director(TabuleiroBuilder builder) {
		this.builder = builder;
	}
	//metodo de criacao do tabuleiro chamando o builder para executar passo-a-passo
	public void construir() {
		builder.reset();
		builder.construirAgua();
		builder.construirVrClaras();
		builder.construirVrEscura();
		builder.construirVrOvasAmarela();
		builder.construirVrOvasVermelha();
	}
}
