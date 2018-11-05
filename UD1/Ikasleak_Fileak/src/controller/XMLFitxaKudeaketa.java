/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Ikaslea;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author idoia
 */
public class XMLFitxaKudeaketa {

    /**
     * xml fitxategi batetik datuak memorian, List batean, kargatu.
     *
     * @param fitxategia
     * @return
     * @throws IOException
     */
    public static ObservableList<Ikaslea> datuakMemorianKargatu(String fitxategia)
            throws IOException, ParserConfigurationException, SAXException {

        //1. Fitxategia parseatu eta DOM zuhaitza lortu
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(fitxategia));

        ObservableList<Ikaslea> data = domLista(document);

        return data;

    }

    /**
     * Memorian dauzkagun datuak xml fitxategian gordetzen ditu.
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

        Document doc = listaDom(data);
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new java.io.File(fitxategia));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);

        return true;
    }

    public static ObservableList<Ikaslea> domLista(Document document) {

        ObservableList<Ikaslea> data = null;
        data = FXCollections.observableArrayList();

        NodeList ikasleNodoak = document.getElementsByTagName("ikaslea");
        for (int i = 0; i < ikasleNodoak.getLength(); i++) {
            int zenbakia = 0;
            String izena = "", abizena1 = "", abizena2 = "", microsoftKontua = "";
            Node nodoa = ikasleNodoak.item(i);
            Element elemIkaslea = (Element) nodoa;
            zenbakia = Integer.parseInt(elemIkaslea.getAttribute("zenbakia"));
            NodeList ikasleNodoarenSemeak = nodoa.getChildNodes();
            for (int j = 0; j < ikasleNodoarenSemeak.getLength(); j++) {
                Node semea = ikasleNodoarenSemeak.item(j);
                if (semea.getNodeName() == "izena") {
                    izena = ((Element) semea.getChildNodes()).getTextContent();
                } else if (semea.getNodeName() == "abizena1") {
                    abizena1 = ((Element) semea.getChildNodes()).getTextContent();
                } else if (semea.getNodeName() == "abizena2") {
                    abizena2 = ((Element) semea.getChildNodes()).getTextContent();
                } else if (semea.getNodeName() == "microsoftKontua") {
                    microsoftKontua = ((Element) semea.getChildNodes()).getTextContent();
                }
            }
            Ikaslea ikaslea = new Ikaslea(zenbakia, izena, abizena1, abizena2, microsoftKontua);
            data.add(ikaslea);
        }
        return data;
    }

    public static Document listaDom(ObservableList<Ikaslea> data) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element erroa = doc.createElement("ikasleak");

        doc.appendChild(erroa);

        Integer zenbakia = 0;
        String izena = "", abizena1 = "", abizena2 = "", microsoftKontua = "";
        for (int i = 0; i < data.size(); i++) {
            zenbakia = data.get(i).getZenbakia();
            izena = data.get(i).getIzena();
            abizena1 = data.get(i).getAbizena1();
            abizena2 = data.get(i).getAbizena2();
            microsoftKontua = data.get(i).getMicrosoftKontua();

            Element elemIkaslea;
            Element azpiElem;
            Text testua;

            elemIkaslea = doc.createElement("ikaslea");
            elemIkaslea.setAttribute("zenbakia", zenbakia.toString());
            erroa.appendChild(elemIkaslea);

            azpiElem = doc.createElement("izena");
            testua = doc.createTextNode(izena);
            azpiElem.appendChild(testua);
            elemIkaslea.appendChild(azpiElem);

            azpiElem = doc.createElement("abizena1");
            testua = doc.createTextNode(abizena1);
            azpiElem.appendChild(testua);
            elemIkaslea.appendChild(azpiElem);

            azpiElem = doc.createElement("abizena2");
            testua = doc.createTextNode(abizena2);
            azpiElem.appendChild(testua);
            elemIkaslea.appendChild(azpiElem);

            azpiElem = doc.createElement("microsoftKontua");
            testua = doc.createTextNode(microsoftKontua);
            azpiElem.appendChild(testua);
            elemIkaslea.appendChild(azpiElem);            
        }

        return doc;
    }
}
