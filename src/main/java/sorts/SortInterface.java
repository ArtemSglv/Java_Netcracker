package sorts;

import repository.Person;

import java.util.Comparator;

public interface SortInterface {
    public void sort(Person[] arrPer, Comparator<Person> condition);
}

