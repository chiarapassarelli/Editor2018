package LogicPart;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	public static final long serialVersionUID = 1L;
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Game";
	public boolean running = false;
	private JFrame frame;
	public int tickCount = 0;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	// I raster sono composti da matrici di pixel (chiamati anche celle), ciascuna
	// contenente un valore che rappresenta le condizioni dell’area coperta dalla
	// cella
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		// delta è in realtà quanti nano secondi sono passati da così lontano. Una volta
		// che ne abbiamo colpito uno, ne verrà eliminato uno
		double delta = 0;

		while (running) {
			boolean shouldRender = true;
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;

			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frames++;
			render();
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("ticks: " + ticks + ", frames:" + frames);
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		tickCount++;
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = i + tickCount;
	}

	public void render() {
		// doppio schermo uno in cui disegno e uno che invece mostro attraverso l'uso di
		// una strategia di buffer
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			// facciamo un triplo buffer
			createBufferStrategy(3);
			return;
		}
		java.awt.Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.black);
		g.drawRect(0, 0, getWidth(), getHeight());
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game().start();
	}

}
