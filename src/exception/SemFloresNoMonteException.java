package exception;

import javax.swing.JOptionPane;

public class SemFloresNoMonteException extends Exception{
	//construtor
	public SemFloresNoMonteException() {
		JOptionPane.showMessageDialog(null, "Acabou as flores dos montes!\n "
				+ "O campo foi limpo e as flores foram embaralhadas\n"
				+ " novamente nos montes");
	}
		
}
