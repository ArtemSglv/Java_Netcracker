package repository;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sorts.impl.QuickSort;

import static injection.Injector.inject;
import static org.junit.Assert.*;

public class PersonTest {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
    private PersonRepository repo = inject(new PersonRepository());
    private Person p;
    private Person p2;
    private Person p3;

    @Before
    public void setUp(){
        p =new Person("Artem","male",new LocalDate(1997,3,28));
        p2 =new Person("Ivan","male",new LocalDate(1997,4,6));
        p3 =new Person("Anna","female",new LocalDate(2009,7,12));

        repo.add(p);
        repo.add(p2);
        repo.add(p3);
    }

    @Test
    public void age() {
        Assert.assertEquals(p.age(),21);
    }

    @Test
    public void compareTo() {
        Assert.assertEquals(p.compareTo(p2),0);
        Assert.assertEquals(p.compareTo(p3),12);
        Assert.assertEquals(p3.compareTo(p2),-12);
    }
}