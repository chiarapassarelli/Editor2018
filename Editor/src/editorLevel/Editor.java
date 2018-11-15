package editorLevel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Editor {

	public Editor() {
		JFrame frame = new JFrame("Level Editor");
		MyPanel mypanel = new MyPanel();
		// tools sarebbe un nuovo pannello però di oggetti che devo inserire nel mio
		// editor
		ToolsPanel tools = new ToolsPanel(mypanel);
		Menu m = new Menu(mypanel, tools);
		mypanel.setPreferredSize(new Dimension(800, 608));
		tools.setPreferredSize(new Dimension(300, 608));

		java.awt.Container contentPane = frame.getContentPane();
		// flow layout ci mette tutti gli elementi in fila finchè c'è spazio
		contentPane.setLayout(new FlowLayout());
		// aggiungiamo i due pannelli
		contentPane.add(mypanel, BorderLayout.EAST);
		contentPane.add(tools, BorderLayout.WEST);
		// ci assicura che si chiude la finestra
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.setJMenuBar(m.getMb());
		// ci fa la resize della finestra in base ai suoi contenuti
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
