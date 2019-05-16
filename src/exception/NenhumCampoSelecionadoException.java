package exception;

import javax.swing.JOptionPane;

//classe de erro de nenhum campo selecionado
public class NenhumCampoSelecionadoException extends Exception{
	//construtor
	public NenhumCampoSelecionadoException() {
		JOptionPane.showMessageDialog(null, "Selecione um campo válido");
	}
}
