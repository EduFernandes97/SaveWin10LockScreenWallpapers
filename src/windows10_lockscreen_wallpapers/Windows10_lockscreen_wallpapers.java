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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author edu_f
 */
public class Windows10_lockscreen_wallpapers {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Map<String, String> mapa = new HashMap<>();

        try {
            readLog(mapa);
        } catch (Exception ex) {

        }

        InputStream inStream;
        OutputStream outStream;
        byte[] buffer;
        int length;
        BufferedImage img;
        boolean novaImg = false;

        File dir = new File("C:\\Users\\edu_f\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets");
        File[] listOfFiles = dir.listFiles();

        int i = 0;
        for (File f : listOfFiles) {
            img = ImageIO.read(f);
            if (!(f.isFile() && f.length() > 350000 && img.getWidth() >= 1920
                    && mapa.get(f.getName()) == null)) {
                continue;
            }
            if (!novaImg)
                novaImg=true;

            File temp = new File("C:\\Users\\edu_f\\OneDrive\\Imagens\\wallpaper\\img" + (listOfFiles.length + (i++)) + ".jpeg");

            inStream = new FileInputStream(f);
            outStream = new FileOutputStream(temp);

            buffer = new byte[1024];

            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            
            mapa.put(f.getName(), temp.getName());

            inStream.close();
            outStream.close();

        }
        if (novaImg)
            guardaLog(mapa);
    }

    private static void readLog(Map<String, String> mapa) throws Exception{
        FileInputStream fis;
        try {
            fis = new FileInputStream("log.bin");
        } catch (Exception ex) {
            return;
        }
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            while (true) {
                
                mapa.put((String)ois.readObject(), (String)ois.readObject());
            }
        } catch (OptionalDataException e) {
            if (!e.eof) {
                throw e;
            }
        } finally {
            ois.close();
            fis.close();
        }

    }

    private static void guardaLog(Map<String, String> mapa) throws FileNotFoundException, IOException {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        fout = new FileOutputStream("log.bin");
        oos = new ObjectOutputStream(fout);

        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            oos.writeObject(entry.getKey());
            oos.writeObject(entry.getValue());
        }
        oos.close();
        fout.close();

    }

}
