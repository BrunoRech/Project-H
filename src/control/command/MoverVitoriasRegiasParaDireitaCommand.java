package control.command;

import control.InterfaceController;

//classe do comando de mover uma vitória régia para direita em 1 quadrado
public class MoverVitoriasRegiasParaDireitaCommand implements Command{
	
	public InterfaceController controlador;
	
	public MoverVitoriasRegiasParaDireitaCommand(InterfaceController controle) {
		this.controlador = controle;
	}
	@Override
	public void execute() {
		controlador.moverPecasTabuleiroParaDireita("execute");
		
	}

	@Override
	public void undo() {
		controlador.moverPecasTabuleiroParaEsquerda("undo");
		
	}

	@Override
	public void redo() {
		controlador.moverPecasTabuleiroParaDireita("redo");
	}
	
	@Override
	public String toString() {
		return "MoverVitoriasRegiasParaDireitaCommand";
	}

}
