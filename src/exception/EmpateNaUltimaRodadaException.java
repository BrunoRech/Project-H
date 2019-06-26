package exception;

import javax.swing.JOptionPane;

public class EmpateNaUltimaRodadaException extends Exception{

	public EmpateNaUltimaRodadaException() {
		JOptionPane.showMessageDialog(null, "Empate na ultima rodada!\n"
				+ " Os campos foram resetados e as flores embaralhadas");
	}
}
