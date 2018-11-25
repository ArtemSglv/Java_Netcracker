package sorts.impl;

import repository.Person;
import sorts.SortInterface;

import java.util.Comparator;

public class QuickSort implements SortInterface {
    private Person[] array;
    private Comparator<Person> condition;

    @Override
    public void sort(Person[] arrPer, Comparator<Person> condition) {
        array = arrPer;
        this.condition = condition;
        int startIndex = 0;
        int endIndex = arrPer.length - 1;
        doSort(startIndex, endIndex);
    }

    private void doSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (condition.compare(array[i],array[cur])<= 0)) {
                i++;
            }
            while (j > cur && (condition.compare(array[cur], array[j]) <= 0)) {
                j--;
            }
            if (i < j) {
                Person temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur);
        doSort(cur+1, end);
    }
}
