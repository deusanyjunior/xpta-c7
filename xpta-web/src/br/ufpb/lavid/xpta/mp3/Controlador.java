package br.ufpb.lavid.xpta.mp3;

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

public class Controlador implements Runnable {

    public Scanner scanner;
    public String entrada;
    public ArrayList<Track> lista;
    public JLabel label = new JLabel();
    public ArrayList<byte[]> tracks;

    /**
     * A classe controladora de eventos de teclado recebe uma lista com todos os arquivos mp3 que
     * serão abertos, e fará o gerenciamento das atividades relacionadas à execução das faixas simultaneamente.
     *
     * @param lista
     *            de arquivos mp3.
     */
    public Controlador(ArrayList<Track> lista) {
        scanner = new Scanner(System.in);
        tracks = new ArrayList<byte[]>();
        this.lista = lista;
        // Inicializa a thread do Controlador:
        new Thread(this).start();
    }

    public void pausar() {
        for (Track mp3 : lista) {
            mp3.pause();
            System.out.println("Pausado");
        }
    }

    public void resumir() {
        for (Track mp3 : lista) {
            mp3.resume();
            System.out.println("Resumiu");
        }
    }

    public void play() {
        for (int i = 0; i < tracks.size(); i++) {
            (lista.get(i)).playFromByteArray(tracks.get(i));
        }
    }

    public void equalizar() {

        float[] equalizar = {-10, -10, -8, 7, 5, 4, 3, 2,
            1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9,
            -10, -11, -12, 8, 9, 10, 11, 10, 12, 4, 4,
            3, 2};

        for (Track mp3 : lista) {
            mp3.equalizer(equalizar);
        }
    }

    public void addVolume(int value) {
        for (Track mp3 : lista) {
            mp3.setVolume(mp3.getVolume() + value);
        }
    }

    public void load() {
        for (Track t : lista) {
            tracks.add(t.loadTrackByteArray());
        }
    }

    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        while (true) {
            System.out.println("Digite sua opção:");
            entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("pause")) {

                pausar();
            } else if (entrada.equalsIgnoreCase("resume")) {
                resumir();
            } else if (entrada.equalsIgnoreCase("play")) {
                play();
            } else if (entrada.equalsIgnoreCase("quit")) {
                System.exit(0);
            } else if (entrada.equalsIgnoreCase("equalizar")) {
                equalizar();
            } else if (entrada.equalsIgnoreCase("+")) {
                addVolume(1);
            } else if (entrada.equalsIgnoreCase("-")) {
                addVolume(-1);
            } else if (entrada.equalsIgnoreCase("load")) {
                load();
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    /**
     * Converte utilizando o pacpl todos os áudios da pasta das tracks.
     * @args diretorioDeEntrada é uma string que representa o diretório que terá recursivamente
     * todos os arquivos(tracks) interiores a ele convertidos para o formato de saída.
     * @args diretorioDeSaida diretório para o qual a saída da conversão enviará os novos arquivos.
     */
    public static void pacplAll(String diretorioDeEntrada, String diretorioDeSaida, String formato) {
        try {
            String commandLine = "pacpl -p -t " + formato + " -r " + diretorioDeEntrada + " --overwrite --outdir diretorioDeSaida";
            Runtime.getRuntime().exec(commandLine);
        } catch (IOException e) {
            System.out.print("pacpl(): " + e.getMessage());
        }
    }

    /**
     * Converte utilizando o pacpl uma faixa única.
     * @args inputFile é uma string que representa o arquivo que será convertido.
     * @args diretorioDeSaida diretório para o qual a saída da conversão enviará o novo arquivo convertido.
     */
    public static void pacplOne(String inputFile, String diretorioDeSaida, String formato) {
        try {
            String commandLine = "pacpl -p -t " + formato + " --overwrite --outdir diretorioDeSaida " + inputFile;
            Runtime.getRuntime().exec(commandLine);
        } catch (IOException e) {
            System.out.print("pacpl(): " + e.getMessage());
        }
    }

    public static void main(String args[]) {

        // ExecutorService pool = Executors.newFixedThreadPool(3);

        ArrayList<Track> lista = new ArrayList<Track>();

        lista.add(new Track("/home/pedroguimaraes/Download/mp3/01.mp3"));
        lista.add(new Track("/home/pedroguimaraes/Música/Led Zeppelin/Led Zeppelin I/Led Zeppelin - Led Zeppelin I - 02 - Babe I'm Gonna Leave You.mp3"));

        /*lista
        .add(new Mp3(
        "/media/FEC2-0D20/mp3/A/Annihilator/Schizo Deluxe/Annihilator - 02 - Drive.mp3",
        0));

        lista
        .add(new Mp3(
        "/media/FEC2-0D20/mp3/A/Atrocity/Hallucinations/03 - Fatal Step.mp3",
        0));

        String itunes = "/Users/rennan/Music/iTunes/iTunes Music/";

        lista
        .add(new Track(itunes+"Dead Fish/Afasia/01 Afasia.mp3"));

        lista
        .add(new Track(itunes+"Dead Fish/Afasia/09 Noite.mp3"));
         */
        // Cria um novo controlador com a lista de músicas a serem executadas:
        Controlador c = new Controlador(lista);
    }
}
