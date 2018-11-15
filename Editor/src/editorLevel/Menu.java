package editorLevel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener, ItemListener {
	private JMenu menu, submenu;
	private JMenuItem i1, i2, i3, i4, i5;
	private JMenuBar mb = new JMenuBar();
	private MyPanel panel;
	private ToolsPanel tools;
	private Vector<FileWriter> files;
	private Vector<String> names;
	private Vector<ColoredPoint> points = new Vector<ColoredPoint>();

	public JMenuBar getMb() {
		return mb;
	}

	public void setMb(JMenuBar mb) {
		this.mb = mb;
	}

	public Menu(MyPanel panel, ToolsPanel tools) {
		super();
		this.panel = panel;
		this.tools = tools;
		files = new Vector<FileWriter>();
		names = new Vector<String>();
		menu = new JMenu("File");
		menu.setBackground(Color.black);
		submenu = new JMenu("Open File");
		i1 = new JMenuItem("New");
		i2 = new JMenuItem("Save");
		i3 = new JMenuItem("Save As...");
		i4 = new JMenuItem("Level1");
		i5 = new JMenuItem("Level2");
		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		submenu.add(i4);
		submenu.add(i5);
		menu.add(submenu);
		mb.add(menu);

		// metto in ascolto i menu
		i1.addActionListener(this);
		i2.addActionListener(this);
		i3.addActionListener(this);

		// metto in ascolto i menu item
		i4.addActionListener(this);
		i5.addActionListener(this);

	}

	@Override
	public void paintComponent(Graphics g) {

		for (int i = 0; i < points.size(); i++) {
			ColoredPoint tmp = points.get(i);
			g.drawImage(tmp.getImage(), tmp.getPoint().x * 32, tmp.getPoint().y * 32, 32, 32, null);
		}
	}

	public void itemStateChanged(ItemEvent e) {

	}

	public boolean presente(FileWriter f, Vector<FileWriter> files) {
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).equals(f))
				return true;
		}

		return false;
	}

	public boolean name(String s, Vector<String> names) {
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).equals(s))
				return true;
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		// se sto premendo sul menu la parola chiave new
		// siccome ho memorizzato gli eventi in un vector basta che resetto il vector e
		// poi chiamo repaint
		if (e.getSource() == i1) {
			panel.getPoints().clear();
			panel.getPoints2().clear();
			panel.removeAllElement();
			panel.repaint();
		}

		// se premo save mi deve salvare il file in un documento
		else if (e.getSource() == i2 || e.getSource() == i3) {
			// finestra di dialogo per salvare il livello creato dal giocatore con il nome
			// che egli
			// sceglie dalla finestra di dialogo
			Dialog o = new Dialog();
			String messaggio = "Livello Inserito " + o.getInput();

			o.visualizza(messaggio);
			String path = messaggio.substring(17);
			try {

				FileWriter f = new FileWriter(path);
				if (!presente(f, files) && !name(path, names)) {
					files.add(f);
					names.add(path);

					BufferedWriter out = new BufferedWriter(f);
					int[][] matrice = new int[19][25];

					for (int i = 0; i < panel.getPoints().size(); i++) {

						int tipo = tools.numberImage(panel.getPoints().get(i).getImage());

						switch (tipo) {
						case 1:
							matrice[panel.getPoints().get(i).getPoint().y][panel.getPoints().get(i).getPoint().x] = 1;
							break;
						case 2:
							matrice[panel.getPoints().get(i).getPoint().y][panel.getPoints().get(i).getPoint().x] = 2;
							break;
						case 3:
							matrice[panel.getPoints().get(i).getPoint().y][panel.getPoints().get(i).getPoint().x] = 3;
							break;
						case 4:
							matrice[panel.getPoints().get(i).getPoint().y][panel.getPoints().get(i).getPoint().x] = 0;
							break;
						default:
							break;

						}

					}

					for (int i = 0; i < matrice.length; i++) {
						for (int j = 0; j < matrice[i].length; j++) {
							out.write(matrice[i][j] + " ");
						}
						out.newLine();

					}
					out.flush();
					out.close();

				} else {
					System.out.println("file già esistente");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} // fine tasto jmenu save
			// inizio jmenu save as..
		else if (e.getSource() == i4) {

			// mi deve leggere il livello 1 e caricarmelo
			panel.getPoints().clear();
			panel.getPoints2().clear();
			LetturaFile l = new LetturaFile();

			int[][] mat = new int[19][25];

			mat = l.LeggiFile("Level1.txt");
			disegnaLivello(mat);
		} else if (e.getSource() == i5) {

			// mi deve leggere il livello 1 e caricarmelo
			panel.getPoints().clear();
			panel.getPoints2().clear();
			LetturaFile l = new LetturaFile();

			int[][] mat = new int[19][25];

			mat = l.LeggiFile("Level2.txt");
			disegnaLivello(mat);
		} else {
		}
	}

	public void disegnaLivello(int[][] mat) {

		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 19; j++) {

				switch (mat[j][i]) {

				case 1: {
					Point p2 = new Point(i, j);
					ColoredPoint c2 = new ColoredPoint(Color.BLACK, p2, tools.imageToNumber(1));
					panel.getPoints2().add(c2);
					panel.repaint();
				}
					break;
				case 2: {
					Point p = new Point(i, j);
					ColoredPoint c = new ColoredPoint(Color.BLACK, p, tools.imageToNumber(2));
					panel.getPoints2().add(c);
					panel.repaint();
				}
					break;
				case 3: {
					Point p1 = new Point(i, j);
					panel.paintImage = tools.imageToNumber(3);
					ColoredPoint c1 = new ColoredPoint(Color.BLACK, p1, panel.paintImage);
					panel.getPoints2().add(c1);
					panel.repaint();
				}
					break;
				default:
					break;
				}

			}
		} // fine due for

	}
}
