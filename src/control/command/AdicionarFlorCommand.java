package control.command;

import control.InterfaceController;

//classe do comando de adicionar flor na vitoria regia
public class AdicionarFlorCommand implements Command{
	
	public InterfaceController controlador;
	
	public AdicionarFlorCommand(InterfaceController controle) {
		this.controlador = controle;
	}
	
	@Override
	public void execute() {
		controlador.adicionarFlor();
		
	}
}
