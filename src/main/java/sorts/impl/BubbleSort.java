package sorts.impl;

import repository.Person;
import sorts.SortInterface;

import java.util.Comparator;

public class BubbleSort implements SortInterface {

    @Override
    public void sort(Person[] arrPer, Comparator<Person> condition) {
        for (int i = 0; i < arrPer.length; i++) {
            for (int j = 1; j < (arrPer.length - i); j++) {
                if (condition.compare(arrPer[j - 1], arrPer[j]) > 0) {
                    //swap elements
                    Person temp = arrPer[j - 1];
                    arrPer[j - 1] = arrPer[j];
                    arrPer[j] = temp;
                }

            }
        }
    }
}
