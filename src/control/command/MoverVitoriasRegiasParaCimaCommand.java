package control.command;

import control.InterfaceController;

//classe do comando de mover uma vitória régia para cima em 1 quadrado
public class MoverVitoriasRegiasParaCimaCommand implements Command{
	
	public InterfaceController controlador;
	
	public MoverVitoriasRegiasParaCimaCommand(InterfaceController controle) {
		this.controlador = controle;
	}
	@Override
	public void execute() {
		controlador.moverPecasTabuleiroParaCima("execute");
		
	}

	@Override
	public void undo() {
		controlador.moverPecasTabuleiroParaBaixo("undo");
	}

	@Override
	public void redo() {
		controlador.moverPecasTabuleiroParaCima("redo");
	}
	
	@Override
	public String toString() {
		return "MoverVitoriasRegiasParaCimaCommand";
	}
	
	

}
