package repository.impl;

import repository.Person;
import repository.SearchLogic;

public class FindByAge implements SearchLogic {
    @Override
    public boolean compare(Person pr, Object toFind) {
        return ((Integer)pr.age()).equals(toFind);
    }
}
