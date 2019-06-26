package control.command;

import control.InterfaceController;

//classe do comando de mover uma vitoria regia para direita em 1 quadrado
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
	public String toString() {
		return "MoverVitoriasRegiasParaDireitaCommand";
	}

}
