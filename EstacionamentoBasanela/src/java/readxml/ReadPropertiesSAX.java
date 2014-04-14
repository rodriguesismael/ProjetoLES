/*
 * Classe ReadPropertiesSAX
 */
package readxml;

import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.util.Map;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class ReadPropertiesSAX extends DefaultHandler {

    private Map hashMap;

    /**
     * Creates a new instance of ReadPropertiesSAX
     */
    public ReadPropertiesSAX() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String name;
        String type;
        if ("logic".equals(localName)) {

            name = attributes.getValue("name");
            type = attributes.getValue("type");

            hashMap.put(name, type);

        }
    }

    public void setHashMap(Map hashMap) {
        this.hashMap = hashMap;
    }

    public Map getHashMap() {
        return this.hashMap;
    }
}
