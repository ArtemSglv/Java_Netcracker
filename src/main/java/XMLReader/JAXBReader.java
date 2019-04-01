package XMLReader;

import injection.Injector;
import org.joda.time.LocalDate;
import repository.Person;
import repository.PersonRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.File;

public class JAXBReader implements XMLReader {

    @Override
    public PersonRepository getPersonsFromXML(String path) {
        PersonRepository repo = Injector.inject(new PersonRepository());

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonRepository.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            repo = (PersonRepository) unmarshaller.unmarshal(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repo;
    }
}
