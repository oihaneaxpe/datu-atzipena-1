
package pkgXMLFitxategiak;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author IMadariaga
 */
public class XMLFitxategiaSortu0tik {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) throws ParserConfigurationException,SAXException,IOException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Element elementua;
      //Zuhaitza 0tik sortu
        Document doc = builder.newDocument();

        Element erroa = doc.createElement("liburutegia");

        doc.appendChild(erroa);
        elementua = doc.createElement("liburua");
        elementua.setTextContent("Ensayo sobre la ceguera");
        erroa.appendChild(elementua);

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new java.io.File("Liburuak3.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
   }
}
