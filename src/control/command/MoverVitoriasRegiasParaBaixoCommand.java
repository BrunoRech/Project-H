package control.command;

import control.InterfaceController;

//classe do comando de mover uma vitória régia para baixo em 1 quadrado
public class MoverVitoriasRegiasParaBaixoCommand implements Command{
	
	public InterfaceController controlador;
	
	public MoverVitoriasRegiasParaBaixoCommand(InterfaceController controle) {
		this.controlador = controle;
	}
	@Override
	public void execute() {
		controlador.moverPecasTabuleiroParaBaixo("execute");
		
	}

	@Override
	public void undo() {

		controlador.moverPecasTabuleiroParaCima("undo");
	}

	@Override
	public void redo() {
		controlador.moverPecasTabuleiroParaBaixo("redo");
	}
	@Override
	public String toString() {
		return "MoverVitoriasRegiasParaBaixoCommand";
	}
	
	

}