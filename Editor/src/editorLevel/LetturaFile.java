package editorLevel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LetturaFile {

	int[][] LeggiFile(String filename) {

		BufferedReader br;
		int riga = 0;
		int colonna = 0;
		String line = " ";
		int[][] mat = new int[0][0];

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))));
			// prendo la prima riga del file

			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// cerco dove ho lo spazio con il token
			StringTokenizer st1 = new StringTokenizer(line, " ");
			// mi salvo in "RIGA" il valore preso in input
			riga = Integer.parseInt(st1.nextToken());
			// mi salvo in "COLONNA" il valore preso in input
			colonna = Integer.parseInt(st1.nextToken());
			// creo una matrice grande riga*colonna
			mat = new int[riga][colonna];
			int x = 0;
			int y;
			// vado alla prossima riga per iniziare a prendere la matrice
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// leggo fino a quando la linea non � nulla cio� fino alla fine del file
			while (line != null) {
				// la colonna parte sempre da zero
				// mentre la riga aumenta all'aumentare delle line che prendo in input
				y = 0;

				// tokenizzo le prossime linee in base allo spazio
				StringTokenizer st = new StringTokenizer(line, " ");
				// fino alla fine del token cio� fino all'ultimo spazio continuo a tokenizzare
				// saltando gli spazi e prendendo quindi i numeri
				while (st.hasMoreTokens()) {
					// salvo i numeri nella matrice
					mat[x][y] = Integer.parseInt(st.nextToken());
					// aumento le colonne
					y++;
				}
				// quando ho finito di leggere la colonna aumento la riga
				x++;
				// leggo la prossima riga/line
				try {
					line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// fine lettura file
		return mat;
	}

}
