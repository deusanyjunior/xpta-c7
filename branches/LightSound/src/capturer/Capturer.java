/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package capturer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Pr Dimas
 */
public class Capturer {
    public static ImageReader imageReader;
    
    public static void startCapture(int sensibilidade, int ratio) {
        imageReader = new ImageReader(new String[]{"dshow://"}, sensibilidade, ratio);
//        int cont = 0;
//        while (true) {
//            boolean[][] mat = imageReader.getIluminedMatrix();
//            BufferedImage img = new BufferedImage(mat.length, mat[0].length, BufferedImage.TYPE_INT_RGB);
//            for (int i = 0; i < mat.length; i++) {
//                for (int j = 0; j < mat[0].length; j++) {
//                    if (mat[i][j]) {
//                        img.setRGB(i, j, 0xFFFFFF);
//                    }
//                }
//            }
//            try {
//                ImageIO.write(img, "png", new File("out/output" + cont++ + ".png"));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    }

}
