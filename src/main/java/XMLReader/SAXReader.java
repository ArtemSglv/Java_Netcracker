package XMLReader;

import injection.Injector;
import org.joda.time.LocalDate;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import repository.Person;
import repository.PersonRepository;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXReader implements XMLReader {
    @Override
    public PersonRepository getPersonsFromXML(String path) {
        PersonRepository repo = Injector.inject(new PersonRepository());
        class XMLHandler extends DefaultHandler {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals("person")) {
                    String fio = attributes.getValue("fio");
                    String sex = attributes.getValue("sex");
                    LocalDate bday = new LocalDate(attributes.getValue("birthday"));
                    repo.add(new Person(fio, sex, bday));
                }
            }
        }
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XMLHandler handler = new XMLHandler();
            parser.parse(new File(path), handler);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return repo;
    }
}
