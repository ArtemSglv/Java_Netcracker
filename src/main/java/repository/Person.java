package repository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Comparator;

import static java.util.UUID.randomUUID;

public class Person implements Comparable<Person> {
    static Logger log = LogManager.getLogger(Person.class.getName());

    public Person(String fio, String sex, LocalDate birthday) {
        id = randomUUID().toString().substring(0, 7);
        this.fio = fio;
        this.sex = sex;
        this.birthday = birthday;
        log.debug("Person "+toString()+" was created");
    }

    private String id;
    private String fio;
    private String sex;
    private LocalDate birthday;

    public int age() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

    public String getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getSex() {
        return sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public int compareTo(Person pr) {

        return age() - pr.age();
    }

    public static Comparator<Person> FioComparator = new Comparator<Person>() {

        @Override
        public int compare(Person p1, Person p2) {
            return p1.getFio().compareTo(p2.getFio());
        }
    };
    public static Comparator<Person> AgeComparator = new Comparator<Person>() {

        @Override
        public int compare(Person p1, Person p2) {
            return p1.age() - p2.age();
        }
    };
    public static Comparator<Person> BirthdayComparator = new Comparator<Person>() {

        @Override
        public int compare(Person p1, Person p2) {

            return p1.getBirthday().compareTo(p2.getBirthday());
        }
    };

    @Override
    public String toString() {
        return "id:" + getId() + " " + getFio() + " " + getSex() + " " + age();
    }
}
