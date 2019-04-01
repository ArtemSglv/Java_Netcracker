package XMLReader;

import injection.Injector;
import org.joda.time.LocalDate;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import repository.Person;
import repository.PersonRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMReader implements XMLReader {
    @Override
    public PersonRepository getPersonsFromXML(String path) {

        PersonRepository repo = Injector.inject(new PersonRepository());

        try {


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(path));

            NodeList personElements = document.getDocumentElement().getElementsByTagName("person");

            // Перебор всех элементов person
            for (int i = 0; i < personElements.getLength(); i++) {
                Node person = personElements.item(i);

                // Получение атрибутов каждого элемента
                NamedNodeMap attributes = person.getAttributes();

                // Добавление person.
                repo.add(new Person(attributes.getNamedItem("fio").getNodeValue(),
                        attributes.getNamedItem("sex").getNodeValue(),
                        new LocalDate(attributes.getNamedItem("birthday").getNodeValue())
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repo;
    }
}
