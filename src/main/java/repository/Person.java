package repository;

import org.joda.time.DateTime;

import java.util.Comparator;

import static java.util.UUID.randomUUID;

public class Person implements Comparable<Person>{
    public Person(String fio, String sex, DateTime birthday) {
        id = randomUUID().toString().substring(0, 7);
        this.fio = fio;
        this.sex = sex;
        this.birthday = birthday;
    }

    private String id;
    private String fio;
    private String sex;
    private DateTime birthday;

    public int age() {
        return DateTime.now().getYear() - birthday.getYear();
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

    public DateTime getBirthday() {
        return birthday;
    }

    @Override
    public int compareTo(Person pr) {

        return this.id.compareTo(pr.id);
    }

    public static Comparator<Person> FioComparator = new Comparator<Person>() {

        @Override
        public int compare(Person p1, Person p2) {
            return p1.getFio().compareTo( p2.getFio());
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
}
