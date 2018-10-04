/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgXMLFitxategiak;

import java.io.File;
import java.io.IOException;
//import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
//import org.w3c.dom.Element;
//import org.w3c.dom.Text;
//import org.xml.sax.SAXException;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;



public class XMLFitxategiakErabili {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {	
      
      //Zuhaitza Sortu fitxategitik abiatuta
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File("Liburuak.xml"));

      //Zuhaitza 0tik sortu
        Document documentBerria = builder.newDocument();
      
      
      //Zuhaitza irakurri	
        System.out.println("Elementu erroa = Dokumentu elementua: " +document.getDocumentElement().getTagName());
        System.out.println("Liburu kopurua: " + document.getElementsByTagName("liburu").getLength()); 
        NodeList liburuNodoak = document.getElementsByTagName("liburu");
        for(int i =0;i<liburuNodoak.getLength();i++) {
            Node nodoa = liburuNodoak.item(i);
            Element elemLiburua=(Element)nodoa;
            System.out.print(elemLiburua.getAttribute("isbn")+" - ");
            NodeList liburuNodoarenSemeak = nodoa.getChildNodes();
            for (int j=0;j<liburuNodoarenSemeak.getLength();j++){
                Node semea = liburuNodoarenSemeak.item(j);
                if (semea.getNodeName()=="izenburua"){
                    System.out.println(((Element)semea.getChildNodes()).getTextContent());
                }
                
            }
            
        }
        
			
      //Zuhaitza aldatu
	//Elementuak sortu, atributuak eta testua gehitu
		
        Element elemLiburu = document.createElement("liburu");
        elemLiburu.setAttribute("isbn","014");
        Element elemIzenburu = document.createElement("izenburu");
        Element elemEgile = document.createElement("egile");
        Text textIzenburu = document.createTextNode("Vredaman");
        Text textEgile = document.createTextNode("Unai Elorriaga");
        document.getDocumentElement().appendChild(elemLiburu);
        elemLiburu.appendChild(elemIzenburu);
        elemIzenburu.appendChild(textIzenburu);
//        elemLiburu.appendChild(elemEgile);
//        elemEgile.appendChild(textEgile);
        System.out.println("Liburu kopurua: " + document.getElementsByTagName("liburu").getLength()); 
		
      //Fitxategia idatzi zuhaitzetik abiatuta
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new java.io.File("Liburuak4.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
		
	}
}
