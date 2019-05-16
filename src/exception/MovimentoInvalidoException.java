package exception;

import javax.swing.JOptionPane;

//classe de erro de um movimento inválido
public class MovimentoInvalidoException extends Exception{
	//construtor
	public MovimentoInvalidoException() {
		JOptionPane.showMessageDialog(null, "Não foi possivel mover essa peça\n Não há espaço suficiente");
	}
}
