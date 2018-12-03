package repository;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.impl.FindByFio;
import sorts.impl.BubbleSort;
import sorts.impl.InsertionSort;
import sorts.impl.QuickSort;

import static org.junit.Assert.*;
import static repository.Person.*;

public class PersonRepositoryTest {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
    private PersonRepository repo = new PersonRepository(new QuickSort());
    private Person p;
    private Person p2;
    private Person p3;

    @Before
    public void setUp() throws Exception {
        p =new Person("Artem","male",new LocalDate(1997,1,1));
        p2 =new Person("Ivan","male",new LocalDate(1997,4,6));
        p3 =new Person("Anna","female",new LocalDate(2009,7,12));

        repo.add(p);
        repo.add(p2);
        repo.add(p3);
    }

    @Test
    public void add() {
        repo = new PersonRepository(new QuickSort());
        Person p = new Person("Anna","female",new LocalDate(2009,7,12));
        repo.add(p);

        Assert.assertEquals(repo.getPerson(0),p);

    }

    @Test
    public void delete() {
        repo.delete(0);

        Assert.assertEquals(repo.getPersonsLength(),2);
    }

    // не правильно работает
    @Test
    public void sort_by_fio() {
        repo.sort(Person.FioComparator);

        Person[] actual = new Person[]{p3,p,p2};
        Assert.assertEquals(repo.getPersonArray(),actual);
    }
    @Test
    public void sort_by_bday() {
        repo.sort(BirthdayComparator);

        Person[] actual = new Person[]{p,p2,p3};
        Assert.assertEquals(repo.getPersonArray(),actual);
    }
    @Test
    public void sort_by_age() {
        repo.sort(AgeComparator);

        Person[] actual = new Person[]{p3,p,p2};
        Assert.assertEquals(repo.getPersonArray(),actual);
    }

    @Test
    public void find() {
        repo.find("Artem",new FindByFio());

        Person actual = p;
        Assert.assertEquals(p,actual);
    }
}