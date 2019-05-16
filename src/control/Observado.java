package control;

//interface do observado com a declaração de cada método que será usado
public interface Observado {
	void addObservador(Observador obs);
	
	//ainda não é necessário nesse momento
	//void removerObservador(Observador obs);
}
