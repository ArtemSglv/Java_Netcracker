package repository.impl;

import org.joda.time.DateTime;
import repository.Person;
import repository.SearchLogic;

public class FindByBirthday implements SearchLogic {
    @Override
    public boolean compare(Person pr, Object toFind) {
        return pr.getBirthday().equals(toFind);
    }
}
