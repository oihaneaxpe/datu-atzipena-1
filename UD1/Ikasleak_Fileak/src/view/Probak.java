/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.json.JsonArray;
import model.Ikaslea;
import javax.json.Json;
import javax.json.stream.JsonParser;

/**
 *
 * @author IMadariaga
 */
public class Probak {

    public static void main(String[] args) {
probauJsonStreamParserra();
    }

    public static void probauJsonStreamParserra() {
        try {
            FileReader jsonStream = new FileReader("Ikasleak.json");

            JsonParser parser = Json.createParser(jsonStream);

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY:
                    case END_ARRAY:
                    case START_OBJECT:
                    case END_OBJECT:
                    case VALUE_FALSE:
                    case VALUE_NULL:
                    case VALUE_TRUE:
                        System.out.println(event.toString());
                        break;
                    case KEY_NAME:
                        System.out.print(event.toString() + " "
                                + parser.getString() + " - ");
                        break;
                    case VALUE_STRING:
                    case VALUE_NUMBER:
                        System.out.println(event.toString() + " "
                                + parser.getString());
                        break;
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Ez dut aurkitu");
        }

    }

    public static void probauListaJsonera() {
        ObservableList<Ikaslea> data = FXCollections.observableArrayList(
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
        try {
            JsonArray arraya = controller.JSONFitxaKudeaketaObjectModelParserrarekin.listaAJsonOM(data);
            System.out.println(arraya.toString());

        } catch (Exception ex) {
            Logger.getLogger(Probak.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
