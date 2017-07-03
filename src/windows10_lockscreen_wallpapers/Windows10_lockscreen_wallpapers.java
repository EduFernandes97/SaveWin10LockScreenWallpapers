/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows10_lockscreen_wallpapers;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author edu_f
 */
public class Windows10_lockscreen_wallpapers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        InputStream inStream = null;
        OutputStream outStream = null;
        byte[] buffer;
        int length;
        BufferedImage img;

        File dir = new File("C:\\Users\\edu_f\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets");
        File[] listOfFiles = dir.listFiles();

        int i = 0;
        for (File f : listOfFiles) {
//            long a = f.length();
            if (f.isFile() && f.length() > 350000) {

                File temp = new File("C:\\Users\\edu_f\\OneDrive\\Imagens\\wallpaper\\img" + (listOfFiles.length + (i++)) + ".jpeg");

                inStream = new FileInputStream(f);
                outStream = new FileOutputStream(temp);

                buffer = new byte[1024];

                while ((length = inStream.read(buffer)) > 0) {

                    outStream.write(buffer, 0, length);

                }

                inStream.close();
                outStream.close();

                img = ImageIO.read(temp);
                if (img.getWidth() < 1920) {
                    temp.delete();
                    i--;
                }
                

            }
            //f.delete();
        }

//        File dirNovo = new File("C:\\Users\\edu_f\\Desktop\\teste\\");
//        File[] listaNovasImg = dirNovo.listFiles();
//
//        File dirPasta = new File("C:\\Users\\edu_f\\OneDrive\\Imagens\\wallpaper");
//        File[] listaPasta = dirPasta.listFiles();
//
//        BufferedImage biA, biB;
//        DataBuffer dbA, dbB;
//
//        int cont = 1;
//        boolean Passa=true;
//
//        for (File novo : listaNovasImg) {
//            for (File f : listaPasta) {
//                biA = ImageIO.read(novo);
//                dbA = biA.getData().getDataBuffer();
//
//                biB = ImageIO.read(f);
//                dbB = biB.getData().getDataBuffer();
//
//                if (dbA.getSize() == dbB.getSize()) {
//                    if (PixeisIguais(dbA, dbB)){
//                        Passa = false;
//                        break;
//                    }
//                    
//                }
//            }
//            if (Passa)
//                novo.renameTo(new File("C:\\Users\\edu_f\\OneDrive\\Imagens\\wallpaper\\img" + (listaPasta.length + (cont++)) + ".jpeg"));
//            Passa=true;
//        }

    }

//    private static boolean PixeisIguais(DataBuffer dbA, DataBuffer dbB) {
//        int a = dbA.getSize();
//        for (int j = 0; j < dbA.getSize(); j += 1000) {
//            int q = dbA.getElem(j);
//            int q1 = dbB.getElem(j);
//
//            if (dbA.getElem(j) != dbB.getElem(j)) {
//                return false;
//            }
//        }
//        return true;
//    }

}
