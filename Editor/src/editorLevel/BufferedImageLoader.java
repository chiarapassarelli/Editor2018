package editorLevel;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	public BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try { // genera un'eccezione che dobbiamo gestire
				// carica l'immagine in base a un nome che viene passato nel metodo
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
