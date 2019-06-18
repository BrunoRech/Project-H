package control.command;

import control.InterfaceController;

//classe do comando de mover uma vitoria regia para esquerda em 1 quadrado
public class MoverVitoriasRegiasParaEsquerdaCommand implements Command{
	
	public InterfaceController controlador;
	
	public MoverVitoriasRegiasParaEsquerdaCommand(InterfaceController controle) {
		this.controlador = controle;
	}
	@Override
	public void execute() {
		controlador.moverPecasTabuleiroParaEsquerda("execute");
		
	}

	@Override
	public void undo() {
		controlador.moverPecasTabuleiroParaDireita("undo");
		
	}
	
	@Override
	public String toString() {
		return "MoverVitoriasRegiasParaEsquerdaCommand";
	}

}
