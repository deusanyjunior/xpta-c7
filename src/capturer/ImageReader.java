package capturer;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.videolan.jvlc.JVLC;
import org.videolan.jvlc.Playlist;
import org.videolan.jvlc.VLCException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rennan
 */
public class ImageReader {

    private int width,  height,  limit,  last,  ratio,  cont;
    private final String imageFormat = "snap%05d.png";
    private boolean[][] iluminedMatrix;
    private boolean sameSize;
    private static Playlist playlist;

    static {
        new File("snaps").mkdir();
        new File("out").mkdir();
    }

    public ImageReader(String[] vlcArgs, int limit, int ratio) {
        this(vlcArgs, limit, ratio, 0, 0);
    }

    public ImageReader(String[] vlcArgs, int limit, int ratio, int width, int height) {
        this.width = width;
        this.height = height;
        this.limit = limit;
        this.ratio = ratio;
        last = 1 - ratio;
        if (width > 0 && height > 0) {
            iluminedMatrix = new boolean[width][height];
            sameSize = false;
        }
        JVLC jvlc = new JVLC(finshArgs(vlcArgs));
        try {
            playlist = new Playlist(jvlc);
            playlist.play();
        } catch (VLCException ex) {
            ex.printStackTrace();
        }
    }

    public static void stop(){
        if(playlist != null){
            try {
                playlist.stop();
            } catch (VLCException ex) {
                System.out.println(ex.getMessage());
            }
            playlist = null;
        }
    }

    private String[] finshArgs(String[] vlcArgs) {
        int len = vlcArgs.length;
        String[] newArgs = new String[len + 6];
        System.arraycopy(vlcArgs, 0, newArgs, 0, len);
        File path = new File("snaps");
        while (path.exists()) {
            String[] list = path.list();
            for(String s: list)
                new File("snaps/"+s).delete();
            path.delete();
        }
        path.mkdir();
        File pathOut = new File("out");
        while (pathOut.exists()) {
            String[] list = pathOut.list();
            for(String s: list)
                new File("out/"+s).delete();
            pathOut.delete();
        }
        pathOut.mkdir();
//        newArgs[len++] = "--vout-filter=transform";
//        newArgs[len++] = "--transform-type=vflip";
        newArgs[len++] = "--video-filter=scene";
        newArgs[len++] = "--scene-path=" + path.getAbsolutePath() + "";
        newArgs[len++] = "--scene-format=png";
        newArgs[len++] = "--scene-prefix=snap";
        newArgs[len++] = "--scene-ratio=" + ratio;
        newArgs[len++] = "--plugin-path=" + new File("plugins").getAbsolutePath();
        for (String s : newArgs) {
            System.out.print(s + " ");
        }
        System.out.println();
        return newArgs;
    }

    private class ImageFilter extends AbstractFileFilter {

        @Override
        public boolean accept(File file) {
            return file.getName().endsWith(".png");
        }
    }

    public boolean[][] getIluminedMatrix() {
        File imgDir = new File("snaps");
        String[] fileList;
        AbstractFileFilter filter = new ImageFilter();

        try {
            do {
                fileList = imgDir.list(filter);
            } while (fileList.length <= 0);
            String newest = String.format(imageFormat, last + ratio);
            last += ratio;
            System.out.println("snaps/" + newest);

            File newestFile = new File("snaps/" + newest);

            while (!newestFile.exists()) {
                newestFile = new File("snaps/" + newest);
            }

            System.out.println("Existe");
            BufferedImage img = null;
            while (true) {
                try {
                    img = ImageIO.read(newestFile);
                    break;
                } catch (Exception ex) {}
            }

            width = img.getWidth();
            height = img.getHeight();
            iluminedMatrix = new boolean[width][height];
            sameSize = true;
            
            return transform(img);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private boolean[][] transform(BufferedImage image) {
//        if (sameSize) {
        System.out.println(iluminedMatrix.length + "x" + iluminedMatrix[0].length + " " + image.getWidth() + "x" + image.getHeight());
        for (int i = 0; i < iluminedMatrix.length; i++) {
            for (int j = 0; j < iluminedMatrix[0].length; j++) {
                int rgb = image.getRGB(i, j);
                int r = (rgb & 0xFF0000) >> 16;
                int g = (rgb & 0xFF00) >> 8;
                int b = (rgb & 0xFF);
                int y = Math.round(0.299f * r + 0.587f * g + 0.114f * b);
                iluminedMatrix[i][j] = ((y / limit) > 0);
            }
        }
//        }
        return iluminedMatrix;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRatio() {
        return ratio;
    }

    public int getHeight() {
        return iluminedMatrix[0].length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return iluminedMatrix.length;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
