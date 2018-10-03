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
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class CopyLinesZenbakiekin {
    public static void main(String[] args) throws IOException {

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("xañadu.txt"));
            outputStream = new PrintWriter(new FileWriter("characteroutputlerrozenbakiekin.txt"));

            int i = 0; //lerro zenbakia 
            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(++i + "- " + l);
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
