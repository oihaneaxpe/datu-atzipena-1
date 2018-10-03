/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bat;

/**
 *
 * @author idoiama
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytesFileNotExistKontrolatuz {

    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xañadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (FileNotFoundException fne) { //in objektua sortzean gertatu daiteke hau
            System.out.println("Ez da fitxategia aurkitu.");
        } catch (IOException ioe) {
            System.out.println("Erroreren bat egon da => " + ioe.getMessage());
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
