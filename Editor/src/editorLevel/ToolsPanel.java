package editorLevel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolsPanel extends JPanel implements ActionListener {

	private MyPanel panel;
	private JButton btnMario = new JButton("");
	private JButton btnFloor = new JButton("");
	private JButton btnLadder = new JButton("");
	private JButton btnIndietro = new JButton("Indietro");

	public BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage sfondo = null;
	private BufferedImage superMario = null;
	private BufferedImage floor = null;
	private BufferedImage ladder = null;

	public ToolsPanel(MyPanel panel) {
		super();
		this.panel = panel;
		sfondo = loader.loadImage("/sfondotools.jpg");
		superMario = loader.loadImage("/fungo2.png");
		floor = loader.loadImage("/tile1.png");
		ladder = loader.loadImage("/scalagialla.png");

		panel.paintImage = superMario;

		btnIndietro.addActionListener(this);
		add(btnIndietro);
		// così facendo posso mettermi in ascolto sugli eventi del bottone
		btnMario.addActionListener(this);
		// aggiungiamo al bottone l'immagine
		btnMario.setIcon(new ImageIcon(superMario.getScaledInstance(32, 32, 0)));
		add(btnMario); // aggiungo il bottone al pannello

		btnFloor.addActionListener(this);
		btnFloor.setIcon(new ImageIcon(floor.getScaledInstance(32, 32, 0)));
		add(btnFloor);

		btnLadder.addActionListener(this);
		btnLadder.setIcon(new ImageIcon(ladder.getScaledInstance(32, 32, 0)));
		add(btnLadder);

	}

	@Override
	public void paintComponent(Graphics g) {

		// g.setColor(new java.awt.Color(0, 0, 0));
		// g.fillRect(0, 0, 800, 600);

		g.drawImage(sfondo, 0, 0, 800, 608, null);
	}

	public BufferedImage getSuperMario() {
		return superMario;
	}

	public void setSuperMario(BufferedImage superMario) {
		this.superMario = superMario;
	}

	public BufferedImage getFloor() {
		return floor;
	}

	public void setFloor(BufferedImage floor) {
		this.floor = floor;
	}

	public BufferedImage getLadder() {
		return ladder;
	}

	public void setLadder(BufferedImage ladder) {
		this.ladder = ladder;
	}

	public int numberImage(BufferedImage paintImage) {

		if (paintImage.toString().equals(superMario.toString()))
			return 1;
		else if (paintImage.toString().equals(ladder.toString()))
			return 2;
		else if (paintImage.toString().equals(floor.toString()))
			return 3;
		else
			return 4;
	}

	public BufferedImage imageToNumber(int num) {

		if (num == 1)
			return superMario;
		else if (num == 2)
			return ladder;
		else if (num == 3)
			return floor;
		else
			return null;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMario) {
			panel.paintImage = superMario;
			// System.out.println("sto mettendo un supermario");
		} else if (e.getSource() == btnFloor) {
			panel.paintImage = floor;
			// System.out.println("sto mettendo un floor");
		} else if (e.getSource() == btnLadder) {
			panel.paintImage = ladder;
			// System.out.println("sto mettendo un ladder");
		} else if (e.getSource() == btnIndietro) {
			if (panel.getPoints().size() > 0) {
				panel.getPoints().remove(panel.getPoints().lastElement());
				panel.repaint();
			} else {
				Dialog dialog = new Dialog();
				dialog.visualizza();
			}

		}
	}
}
