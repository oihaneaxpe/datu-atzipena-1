/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileOutputStream;
import model.Ikaslea;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class JSONFitxaKudeaketaObjectModelParserrarekin {

    /**
     * JSON fitxategi batetik datuak memorian, List batean, kargatu.
     *
     * @param fitxategia
     * @return
     * @throws IOException
     */
    public static ObservableList<Ikaslea> datuakMemorianKargatu(String fitxategia) {
        ObservableList<Ikaslea> data = null;
        //1. Fitxategia parseatu eta JsonObjectModel zuhaitza lortu
        try {
            JsonReader reader = Json.createReader(new FileReader(fitxategia));
            JsonStructure jsonst = reader.read();
            data = jsonOMALista((JsonArray) jsonst);

        } catch (IOException ioe) {
            System.out.println("IOException bat egon da.");
        } finally {
            return data;
        }

    }

    /**
     * Memorian dauzkagun datuak json fitxategian gordetzen ditu.
     *
     * @param data
     * @param fitxategia
     * @return
     * @throws IOException
     */
    public static boolean datuakFitxategianGorde(ObservableList<Ikaslea> data, String fitxategia)
            throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

        if (data == null) {
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

        JsonArray model = listaAJsonOM(data);
        System.out.println(model.toString());

//        StringWriter stWriter = new StringWriter();
//        JsonWriter jsonWriter = Json.createWriter(stWriter);
//        jsonWriter.writeObject(model);
//        jsonWriter.close();
//
//        String jsonData = stWriter.toString();
//        System.out.println(jsonData);
        FileOutputStream out = new FileOutputStream("Ikasleak.json");
        JsonWriter jsonWriter = Json.createWriter(out);
        jsonWriter.writeArray(model);
        jsonWriter.close();
        return true;
    }

    public static ObservableList<Ikaslea> jsonOMALista(JsonArray model) {

        ObservableList<Ikaslea> data = null;
        data = FXCollections.observableArrayList();

        for (int i = 0; i < model.size(); i++) {
            JsonObject jsonIkaslea = model.getJsonObject(i);
            Ikaslea ikaslea = new Ikaslea(jsonIkaslea.getJsonNumber("zenbakia").intValue(),
                    jsonIkaslea.getJsonString("izena").getString(),
                    jsonIkaslea.getJsonString("abizena1").getString(),
                    jsonIkaslea.getJsonString("abizena2").getString(),
                    jsonIkaslea.getJsonString("microsoftKontua").getString());
            data.add(ikaslea);
        }
        return data;
    }

    public static JsonArray listaAJsonOM(ObservableList<Ikaslea> data) {

        JsonArrayBuilder aBuilder = Json.createArrayBuilder();
        JsonObjectBuilder oBuilder = Json.createObjectBuilder();

        for (Ikaslea ikaslea : data) {
            oBuilder.add("zenbakia", ikaslea.getZenbakia());
            oBuilder.add("izena", ikaslea.getIzena());
            oBuilder.add("abizena1", ikaslea.getAbizena1());
            oBuilder.add("abizena2", ikaslea.getAbizena2());
            oBuilder.add("microsoftKontua", ikaslea.getMicrosoftKontua());
            JsonObject jsonObjIkaslea = oBuilder.build();
            aBuilder.add(jsonObjIkaslea);
        }
        JsonArray jsonArraya = aBuilder.build();
        return jsonArraya;

    }
}
