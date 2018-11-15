package editorLevel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener {
	private int width = 800;
	private Vector<ColoredPoint> points = new Vector<ColoredPoint>();
	private Vector<ColoredPoint> points2 = new Vector<ColoredPoint>();
	private Color paintColor = Color.yellow;
	public BufferedImage paintImage = null;
	public BufferedImage sfondo = null;
	public BufferedImageLoader loader = new BufferedImageLoader();

	public MyPanel() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void removeAllElement() {
		points.clear();
	}

	public Vector<ColoredPoint> getPoints() {
		return points;
	}

	public Vector<ColoredPoint> getPoints2() {
		return points2;
	}

	@Override
	public void paintComponent(Graphics g) {
		sfondo = loader.loadImage("/sfondotools.jpg");
		g.drawImage(sfondo, 0, 0, 800, 608, null);
//		g.setColor(new java.awt.Color(0, 0, 0));
//		g.fillRect(0, 0, 800, 600);
		for (int i = 0; i < width; i += 32) {
			g.setColor(Color.gray);
			g.fillRect(i, 0, 1, 800);
			g.fillRect(0, i, 800, 1);
		}

		for (int i = 0; i < points.size(); i++) {
			ColoredPoint tmp = points.get(i);
			g.drawImage(tmp.getImage(), tmp.getPoint().x * 32, tmp.getPoint().y * 32, 32, 32, null);
		}
		for (int i = 0; i < points2.size(); i++) {
			ColoredPoint tmp = points2.get(i);
			g.drawImage(tmp.getImage(), tmp.getPoint().x * 32, tmp.getPoint().y * 32, 32, 32, null);
		}
	}

	// deve fare ciò che fa il mousepressed cioè deve controllare il click del mouse
	// deve convertire le coordinate e aggiungerlo al vettore
	// quindi basta richiamare il metodo mouseDragged
	public void mouseDragged(MouseEvent e) {

		mousePressed(e);
	}

	// succede però che se clicco due volte sulla stessa cella il vettore di points
	// continua ad aumentare ed è peggio se ho l'evento mouseDragged
	// quindi devo controllare nel vector se quel punto ce l ho già oppure no prima
	// di inserirlo nel vettore
	private void removeDuplicate(Point p) {
		for (int i = 0; i < points.size(); i++) {
			ColoredPoint c = points.get(i);
			if (c.getPoint().equals(p)) {
				points.remove(i);
				return;
			}
		}
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent mouse) {

		int x = mouse.getX();
		int y = mouse.getY();

		Point p = clickToGrid(x, y);
		ColoredPoint c = new ColoredPoint(paintColor, p, paintImage);

		// SE C'E' GIA' UN PUNTO LO RIMUOVO
		removeDuplicate(p);

		points.add(c);

		repaint();
//		for (int i = 0; i < points.size(); i++)
//			System.out.println("x logica" + points.get(i).getPoint().x + " " + "y logica" + points.get(i).getPoint().y);
	}

	// converte le coordinate grafiche in coordinate logiche
	private Point clickToGrid(int x, int y) {
		int px = x;
		int py = y;
		px = px / 32;
		py = py / 32;
		return new Point(px, py);
	}

	public void setPaintColor(Color paintColor) {
		this.paintColor = paintColor;
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
