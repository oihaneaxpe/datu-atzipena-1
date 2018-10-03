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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacters {

    public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;
        int count = 0; //Zenbat irakurketa egin diren kontatzeko

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("characteroutput.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
                count = count + 1;

            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            System.out.println("Irakurketa kopurua: " + count);

        }
    }
}
