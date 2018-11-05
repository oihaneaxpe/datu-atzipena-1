/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ikaslea;

/**
 *
 * @author idoia
 */
public class TestuFitxaKudeaketa {

    /**
     * csv egitura daukan karaktere fitxategi batetik datuak memorian, List
     * batean, kargatu.
     *
     * @param fitxategia
     * @return
     * @throws IOException
     */
    public static ObservableList<Ikaslea> datuakMemorianKargatu(String fitxategia) throws IOException {

        ObservableList<Ikaslea> data = null;
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(fitxategia));
            data = FXCollections.observableArrayList();
            String l;
            while ((l = inputStream.readLine()) != null) {
                Scanner s = new Scanner(l).useDelimiter(",");
                Ikaslea ikasle = new Ikaslea(s.nextInt(), s.next(), s.next(), s.next(), s.next());
                data.add(ikasle);                
            }
            
        } catch (FileNotFoundException fnfe) {
            System.out.println("Ez da fitxategia aurkitu.");
            data = null;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            return data;
        }        
    }

    /**
     * Memorian dauzkagun datuak testu fitxategian gordetzen ditu.
     *
     * @param data
     * @param fitxategia
     * @return
     * @throws IOException
     */
    public static boolean datuakFitxategianGorde(ObservableList<Ikaslea> data, String fitxategia) throws IOException {

        if (data == null || data.size()==0) {
            data = FXCollections.observableArrayList(
                    new Ikaslea(1, "Julen", "Aboitiz", "Bermeosolo", "dam2-jaboitizb"),
                    new Ikaslea(2, "Aimar", "Aizpuru", "Eguia", "dam2-aaizpurue"),
                    new Ikaslea(3, "Jon", "Akarregi", "Letona", "dam2-jakarregi"),
                    new Ikaslea(4, "Julen", "Alzaa", "Pedro de Oliveira", "dam2-jalzaaped"),
                    new Ikaslea(5, "Eider", "Anduaga", "Garcia", "dam2-eanduagag"),
                    new Ikaslea(6, "Jon", "Aretxabaleta", "Zelaia", "dam2-jaretxaba"),
                    new Ikaslea(7, "Oihane", "Axpe", "Telleriarte", "dam2-oaxpetell"),
                    new Ikaslea(8, "Jon", "Barrutieta", "Lorca", "dam2-jbarrutie"),
                    new Ikaslea(9, "Jon Mikel", "Beitia", "Echeita", "dam2-jbeitiaec"),
                    new Ikaslea(10, "Josu", "Bilbao", "Iraegi", "dam2-jbilbaoir"),
                    new Ikaslea(11, "Xabier", "Bolumburu", "Casado", "dam2-xbolumbur"),
                    new Ikaslea(12, "Gontzal", "de Carlos", "Garcia", "dam2-gdecarlos"),
                    new Ikaslea(13, "Xabier", "Diaz", "de Pedro", "dam2-xdiazdepe"),
                    new Ikaslea(14, "Mikel", "Elizondo", "Nogales", "dam2-melizondo"),
                    new Ikaslea(15, "Koldo", "Etxaniz", "Orbea", "dam2-ketxanizo"),
                    new Ikaslea(16, "Ibon", "Felipe", "Mora", "dam2-ifelipemo"),
                    new Ikaslea(17, "Sara", "Garrido", "Carranza", "dam2-sgarridoc"),
                    new Ikaslea(18, "Andoitz", "Horrillo", "Valenciano", "dam2-ahorrillo"),
                    new Ikaslea(19, "Aritz", "Iza", "Ugarte", "dam2-aizaugart"),
                    new Ikaslea(20, "Gaizka", "Izagirre", "López", "dam2-gizagirre"),
                    new Ikaslea(21, "Aitor", "Magallanes", "Lozano", "dam2-amagallan"),
                    new Ikaslea(22, "Eider", "Maiztegui", "Cestafe", "dam2-emaiztegu"),
                    new Ikaslea(23, "Lucia", "Meruelo", "Garcia", "dam2-lmeruelog")
            );
        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(
                    new FileWriter(fitxategia));

            for (int i = 0; i < data.size(); i++) {
                pw.print(data.get(i).getZenbakia() + "," + data.get(i).getIzena() + "," + data.get(i).getAbizena1() + "," + data.get(i).getAbizena2() + "," + data.get(i).getMicrosoftKontua());
                pw.println();
            }
        } finally {
            if (pw != null) {
                pw.close();
                System.out.println(data.size() + " ikasle gorde dira " + fitxategia + " fitxategian!!");
                return true;
            } else {
                System.out.println("Ezin izan dira datuak gorde.");
                return false;
            }
        }
    }
}
