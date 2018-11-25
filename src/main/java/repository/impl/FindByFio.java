package repository.impl;

import repository.Person;
import repository.SearchLogic;

public class FindByFio implements SearchLogic {

    @Override
    public boolean compare(Person pr, Object toFind) {

        return pr.getFio().equals(toFind.toString());
    }
}
