package XMLReader;

import repository.PersonRepository;

public interface XMLReader {
    PersonRepository getPersonsFromXML(String path);
}
