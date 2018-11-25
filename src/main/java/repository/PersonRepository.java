package repository;

import sorts.SortInterface;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Контейнер для объектов Person
 */
public class PersonRepository {
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
    }

    /**
     * Удаляет элемент с индексом index
     *
     * @param index удаляемого элемента
     */
    public void delete(int index) {
        for (int i = index; i < persons.length - 1; i++) {
            persons[i] = persons[i + 1];
        }
        persons = Arrays.copyOf(persons, persons.length - 1);
    }

    public Person getPerson(int index) {
        return persons[index];
    }

    public int getPersonsLength() {
        return persons.length;
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
