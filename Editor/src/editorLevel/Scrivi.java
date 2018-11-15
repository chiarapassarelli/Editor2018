package editorLevel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Scrivi {

	public static void main(String args[]) throws IOException {
		FileWriter w;
		w = new FileWriter("scrittura.txt");

		BufferedWriter b;
		b = new BufferedWriter(w);

		b.write("abcd\nefghi");

		b.write("123");

		b.flush();
	}

}
