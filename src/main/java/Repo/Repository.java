package Repo;

import java.util.Arrays;

/**
 * Контейнер для объектов Person
 */
public class Repository {
    public Repository() {
        persons = new Person[0];
    }

    private Person[] persons;

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
}
