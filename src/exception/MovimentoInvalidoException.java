package exception;

import javax.swing.JOptionPane;

//classe de erro de um movimento invalido
public class MovimentoInvalidoException extends Exception{
	//construtor
	public MovimentoInvalidoException() {
		JOptionPane.showMessageDialog(null, "Nao foi possivel mover essa peca\n Nao ha¡ espaco suficiente");
	}
}
