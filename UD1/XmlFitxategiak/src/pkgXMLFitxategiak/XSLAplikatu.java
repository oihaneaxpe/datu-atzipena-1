/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgXMLFitxategiak;

import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Idoia
 */
public class XSLAplikatu {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
	    try
        {
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource("LiburuakHtmlra.xsl");
            Source xmlDoc = new StreamSource("Liburuak.xml");

            String outputFileName = "Liburuak.html";
            OutputStream htmlFile = new FileOutputStream(outputFileName);

            Transformer transformer = tFactory.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	}

