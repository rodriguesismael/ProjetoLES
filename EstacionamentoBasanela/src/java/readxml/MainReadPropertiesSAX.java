/*
 * Classe MainReadPropertiesSAX
 */
package readxml;

import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.SAXException;
import java.io.File;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class MainReadPropertiesSAX {

    private SAXParserFactory factory;
    private SAXParser parse;
    private ReadPropertiesSAX reader;

    /**
     * Creates a new instance of MainReadPropertiesSAX
     */
    public MainReadPropertiesSAX() throws ReadPropertiesSaxException {
        try {
            factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            parse = factory.newSAXParser();
            reader = new ReadPropertiesSAX();

        } catch (FactoryConfigurationError ex) {
            throw new ReadPropertiesSaxException(ex.getMessage());
        } catch (ParserConfigurationException ex) {
            throw new ReadPropertiesSaxException(ex.getMessage());
        } catch (SAXException ex) {
            throw new ReadPropertiesSaxException(ex.getMessage());
        }
    }

    public void readXML(String xmlFile, Map hashmap) throws ReadPropertiesSaxException {
        try {

            reader.setHashMap(hashmap);
            File f = new File(xmlFile);
            parse.parse(f, reader);

        } catch (SAXException ex) {
            throw new ReadPropertiesSaxException(ex.getMessage());
        } catch (IOException ex) {
            throw new ReadPropertiesSaxException(ex.getMessage());
        }
    }
}
