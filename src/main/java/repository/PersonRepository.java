package repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sorts.SortInterface;
import sorts.impl.BubbleSort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Контейнер для объектов Person
 */
public class PersonRepository {
    static Logger log = LogManager.getLogger(PersonRepository.class.getName());
    public PersonRepository(SortInterface sort) {
        persons = new Person[0];
        this.sort = sort;
    }

    private Person[] persons;
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
