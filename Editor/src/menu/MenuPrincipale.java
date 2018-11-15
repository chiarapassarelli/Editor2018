package menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import editorLevel.Editor;
import gui.GameManager;

public class MenuPrincipale extends JPanel implements ActionListener {
	//CIAO MARIAAAAAAAAAAA
	private JButton start = new JButton("START");
	private JButton option = new JButton("OPTION");
	private JButton btnEditor = new JButton("EDITOR");

	private Editor editor;
	private GameManager game;

	public MenuPrincipale() {

		start.addActionListener(this);
		add(start);
		option.addActionListener(this);
		add(option);
		btnEditor.addActionListener(this);
		add(btnEditor);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEditor)
			editor = new Editor();
		else if (e.getSource() == start)
			game = new GameManager();

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Menu Principale");
		MenuPrincipale mypanel = new MenuPrincipale();

		mypanel.setPreferredSize(new Dimension(800, 608));

		java.awt.Container contentPane = frame.getContentPane();
		// flow layout ci mette tutti gli elementi in fila finchè c'è spazio
		contentPane.setLayout(new FlowLayout());
		// aggiungiamo i due pannelli
		contentPane.add(mypanel, BorderLayout.CENTER);

		// ci assicura che si chiude la finestra
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		// ci fa la resize della finestra in base ai suoi contenuti
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
