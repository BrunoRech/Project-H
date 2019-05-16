package control.command;

import control.InterfaceController;

//classe do comando de adicionar flor na vitória régia
public class AdicionarFlorCommand implements Command{
	
	public InterfaceController controlador;
	
	public AdicionarFlorCommand(InterfaceController controle) {
		this.controlador = controle;
	}
	
	@Override
	public void execute() {
		controlador.adicionarFlor("execute");
		
	}

	@Override
	public void undo() {
		controlador.removerFlor("undo");
	}

	@Override
	public void redo() {
		controlador.adicionarFlor("redo");
		
	}

}
