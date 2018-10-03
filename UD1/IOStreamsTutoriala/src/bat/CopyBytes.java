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
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {

    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;
        int count = 0; //Zenbat irakurketa egin diren kontatzeko

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
                count = count + 1;
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            System.out.println("Irakurketa kopurua: " + count);
        }
    }
}
