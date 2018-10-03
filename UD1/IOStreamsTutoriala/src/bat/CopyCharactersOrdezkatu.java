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


/* CopyCharacters programa baina 'a' letra 'o' letrarekin ordezkatuz
*/
public class CopyCharactersOrdezkatu {
    public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("characteroutputordezkapenarekin.txt");

            int c;
            int zer = 'a';
            int zerekin = 'o';
            while ((c = inputStream.read()) != -1) {
                if (c == zer)
                    c = zerekin;
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
