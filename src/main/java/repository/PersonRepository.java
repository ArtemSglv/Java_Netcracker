package repository;

import injection.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sorts.SortInterface;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Контейнер для объектов Person
 */
@XmlRootElement(name= "Persons")
public class PersonRepository {
    static Logger log = LogManager.getLogger(PersonRepository.class.getName());
    public PersonRepository() {
        persons = new Person[0];
    }

    @XmlElement(name="person")
    private Person[] persons;
    @Inject
    private SortInterface sort;

    /**
     * Добавляет элемент в массив
     *
     * @param p добовляемый элемент
     */
    public void add(Person p) {
        persons = Arrays.copyOf(persons, persons.length + 1);
        persons[persons.length - 1] = p;
        log.debug("Person "+p.getId()+" was added");
    }

    public void addArrayOfPersons(Person[] persons){
        this.persons = persons;
    }

    /**
     * Удаляет элемент с индексом index
     *
     * @param index удаляемого элемента
     */
    public void delete(int index) {
        for (int i = index; i < persons.length - 1; i++) {
            persons[i] = persons[i + 1];
            log.debug("Person "+persons[i].getId()+"was deleted");
        }
        persons = Arrays.copyOf(persons, persons.length - 1);
    }

    public Person getPerson(int index) {
        return persons[index];
    }

    public int getPersonsLength() {
        return persons.length;
    }

    public Person[] getPersonArray(){
        return persons;
    }

    public void sort(Comparator<Person> condition) {
        sort.sort(persons, condition);
    }

    public Person[] find(Object toFind, SearchLogic object){
        Person[] result = new Person[0];
        for (Person pr:persons)
            if (object.compare(pr, toFind)) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = pr;
            }
        return result;
    }

}
