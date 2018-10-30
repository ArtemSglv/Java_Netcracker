package Repo;

import java.util.Arrays;

/**
 * Контейнер для объектов Person
 */
public class Repository {
    public Repository(){
        persons = new Person[1];
        countOfPerson=0;
    }
    private Person[] persons;
    private int countOfPerson;

    /**
     * Добавляет элемент в массив
     * @param p добовляемый элемент
     */
    public void Add(Person p)
    {
        if(countOfPerson>=persons.length)
        {
            persons = Arrays.copyOf(persons,persons.length+1);
            persons[persons.length-1] = p;
        }
        else {
            persons[countOfPerson] = p;
        }
        countOfPerson++;
    }

    /**
     * Удаляет элемент с индексом index
     * @param index удаляемого элемента
     */
    public void Delete(int index)
    {

    }

    public Person GetPerson(int index)
    {
        return persons[index];
    }
    public int GetPersonsLength()
    {
        return persons.length;
    }
}
