package control.builder;

//classe do diretor que comanda os construtores
public class Director {
	private TabuleiroBuilder builder;
	
	//construtor da classe
	//@param builder, o construtor que será utilizado no método construir
	public Director(TabuleiroBuilder builder) {
		this.builder = builder;
	}
	//método de criação do tabuleiro chamando o builder para executar passo-a-passo
	public void construir() {
		builder.reset();
		builder.construirAgua();
		builder.construirVrClaras();
		builder.construirVrEscura();
		builder.construirVrOvasAmarela();
		builder.construirVrOvasVermelha();
	}
}
