package exception;

import javax.swing.JOptionPane;

//classe de erro de um campo invalido selecionado para uma acao especifica
public class CampoInvalidoException extends Exception{
	//construtor
	public CampoInvalidoException() {
		JOptionPane.showMessageDialog(null, "Campo invalido para sua jogada\n Verifique as regras do jogo");
	}
}
