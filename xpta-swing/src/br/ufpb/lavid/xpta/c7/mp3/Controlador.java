package br.ufpb.lavid.xpta.c7.mp3;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class Controlador extends JWindow implements Runnable {
	public Scanner scanner = new Scanner(System.in);
	public String entrada;
	public ArrayList<Track> lista;
        public JLabel label = new JLabel();

	/**
	 * A classe controladora recebe uma lista com todos os arquivos mp3 que
	 * serão abertos, e fará o gerenciamento das atividades relacionadas à
	 * execução das faixas simultaneamente.
	 * 
	 * @param lista
	 *            de arquivos mp3.
	 */
	public Controlador(ArrayList<Track> lista) {
		this.lista = lista;

		// Inicializa a thread do Controlador:
		new Thread(this).start();
                add(label);
	}

	public void pausar() {
		for (Track mp3 : lista) {
			mp3.pause();
			label.setText("Pausado");
		}
	}

	public void resumir() {
		for (Track mp3 : lista) {
			mp3.resume();
			label.setText("Resumiu");
		}
	}

	public void play() {
		for (Track mp3 : lista) {
			new Thread(mp3).start();
			label.setText("Play");
		}
	}

	public void equalizar()
	{
		
		 float[] equalizar = {-10, -10, -8, 7, 5, 4, 3, 2,
		 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9,
		 -10, -11, -12, 8, 9, 10, 11, 10, 12, 4, 4,
		 3, 2};
		 
            for (Track mp3: lista) {
                    mp3.equalizer(equalizar);
            }
	}

        public void addVolume(int value) {
            for (Track mp3: lista) {
                mp3.setVolume(mp3.getVolume()+value);
            }
        }


	public void run() {

		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                play();
                
		while (true) {
			label.setText("Digite sua opção:");
                        DataInputStream dis = new DataInputStream(System.in);
                        try {
                            entrada = "" + dis.readChar();
                        } catch (IOException ex) {
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }

			if (entrada.equalsIgnoreCase("pause")) {
				label.setText("Pause");
				pausar();
			} else if (entrada.equalsIgnoreCase("resume"))
				resumir();
			else if (entrada.equalsIgnoreCase("play"))
				play();
			else if (entrada.equalsIgnoreCase("quit"))
				System.exit(0);
			else if (entrada.equalsIgnoreCase("equalizar"))
				equalizar();
			else if (entrada.equalsIgnoreCase("+"))
				addVolume(1);
			else if (entrada.equalsIgnoreCase("-"))
				addVolume(-1);
			else
				label.setText("Opção inválida.");
		}
	}

	public static void main(String args[]) {

		// ExecutorService pool = Executors.newFixedThreadPool(3);

		ArrayList<Track> lista = new ArrayList<Track>();

/*		lista
				.add(new Mp3(
						"/media/FEC2-0D20/mp3/A/Annihilator/Schizo Deluxe/Annihilator - 03 - Warbird.mp3",
						0));
		lista
				.add(new Mp3(
						"/media/FEC2-0D20/mp3/A/Annihilator/Schizo Deluxe/Annihilator - 02 - Drive.mp3",
						0));

		lista
				.add(new Mp3(
						"/media/FEC2-0D20/mp3/A/Atrocity/Hallucinations/03 - Fatal Step.mp3",
						0));*/

                String itunes = "/Users/rennan/Music/iTunes/iTunes Music/";

		lista
		.add(new Track(itunes+"Dead Fish/Afasia/01 Afasia.mp3"));
		
		lista
		.add(new Track(itunes+"Dead Fish/Afasia/09 Noite.mp3"));

		// Cria um novo controlador com a lista de músicas a serem executadas:
		Controlador c = new Controlador(lista);

                c.setSize(400,400);
                c.setVisible(true);

	}

}
