package editorLevel;

import javax.swing.JOptionPane;

public class Dialog {
	private String input;

	public String getInput() {
		input = JOptionPane.showInputDialog("Nome Livello");
		return input;
	}

	public void visualizza(String testo) {
		JOptionPane.showMessageDialog(null, testo, null, JOptionPane.PLAIN_MESSAGE);
	}

	public void visualizza() {
		JOptionPane.showMessageDialog(null, "Non è possibile tornare alla mossa precedende");
	}
}
