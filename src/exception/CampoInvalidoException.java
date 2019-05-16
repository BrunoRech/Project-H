package exception;

import javax.swing.JOptionPane;

//classe de erro de um campo inválido selecionado para uma ação específica
public class CampoInvalidoException extends Exception{
	//construtor
	public CampoInvalidoException() {
		JOptionPane.showMessageDialog(null, "Esse campo não é válido para sua ação\n Verifique as regras do jogo");
	}
}
