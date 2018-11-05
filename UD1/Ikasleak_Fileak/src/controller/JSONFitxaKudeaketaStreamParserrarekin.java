/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import model.Ikaslea;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

public class JSONFitxaKudeaketaStreamParserrarekin {

    /**
     * JSON fitxategi batetik datuak memorian, List batean, kargatu.
     *
     * @param fitxategia
     * @return
     */
    public static ObservableList<Ikaslea> datuakMemorianKargatu(String fitxategia) {
        ObservableList<Ikaslea> data = FXCollections.observableArrayList();
        Ikaslea ikaslea = null;
        int zenbakia = 0;
        String izena = "";
        String abizena1 = "";
        String abizena2 = "";
        String microsoftKontua = "";

        String unekoKeya = "";
        
        try {
            JsonParser parser = Json.createParser(new FileReader(fitxategia));

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY:
                        break;
                    case END_ARRAY:
                        break;
                    case START_OBJECT:
                        break;

                    case END_OBJECT:
                        ikaslea = new Ikaslea(zenbakia, izena, abizena1, abizena2, microsoftKontua);
                        data.add(ikaslea);
                        break;
                    case VALUE_FALSE:
                    case VALUE_NULL:
                    case VALUE_TRUE:
//                        System.out.println(event.toString());
//                        break;
                    case KEY_NAME:
                        unekoKeya = parser.getString();
                        break;
                    case VALUE_STRING:
                    case VALUE_NUMBER:
                        switch (unekoKeya) {
                            case "zenbakia":
                                zenbakia = parser.getInt();
                                break;
                            case "izena":
                                izena = parser.getString();
                                break;
                            case "abizena1":
                                abizena1 = parser.getString();
                                break;
                            case "abizena2":
                                abizena2 = parser.getString();
                                break;
                            case "microsoftKontua":
                                microsoftKontua = parser.getString();
                                break;
                        }

                        break;
                }
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("Fitxategi hori ez da existitzen.");
        }
        return data;
    }

    public static boolean datuakFitxategianGorde(ObservableList<Ikaslea> data, String fitxategia) {
        boolean emaitza = false;
        try {
            FileWriter writer = new FileWriter(fitxategia);

            JsonGenerator gen = Json.createGenerator(writer);
            gen.writeStartArray();
            for (Ikaslea ikasle : data){
                gen.writeStartObject();
                gen.write("zenbakia", ikasle.getZenbakia());
                gen.write("izena", ikasle.getIzena());
                gen.write("abizena1", ikasle.getAbizena1());
                gen.write("abizena2", ikasle.getAbizena2());
                gen.write("microsoftKontua", ikasle.getMicrosoftKontua());                
                gen.writeEnd();            
            }
            gen.writeEnd();//END_ARRAY
            gen.close();
            emaitza = true;
        } catch (IOException ioe) {
            System.out.println("Errorea 137");
        } finally {
            return emaitza;
        }

    }
}
