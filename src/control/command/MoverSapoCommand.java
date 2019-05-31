package control.command;

import control.InterfaceController;

//classe do comando de mover o sapo para outra vitória régia
public class MoverSapoCommand implements Command{
	
	public InterfaceController controlador;
	
	public MoverSapoCommand(InterfaceController controle) {
		this.controlador = controle;
	}
	@Override
	public void execute() {
		controlador.adicionarSapo("execute");
		
	}

	@Override
	public void undo() {
		controlador.removerSapo("undo");
		
	}

	@Override
	public void redo() {
		controlador.adicionarSapo("redo");
	}

}