package sorts.impl;

import repository.Person;
import sorts.SortInterface;

import java.util.Comparator;

public class InsertionSort implements SortInterface {
    @Override
    public void sort(Person[] arrPer, Comparator<Person> condition) {

        int n = arrPer.length;
        for (int i = 1; i < n; ++i) {
            Person key = arrPer[i];
            int j = i - 1;

            while (j >= 0 && condition.compare(arrPer[j], key) > 0) {
                arrPer[j + 1] = arrPer[j];
                j = j - 1;
            }
            arrPer[j + 1] = key;
        }
    }
}
