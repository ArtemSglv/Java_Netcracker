package repository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Comparator;

import static java.util.UUID.randomUUID;

//@XmlRootElement(name = "person")
public class Person implements Comparable<Person> {
    public static class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
        public LocalDate unmarshal(String v) throws Exception {
            return LocalDate.parse(v);
        }

        public String marshal(LocalDate v) throws Exception {
            return v.toString();
        }
    }
    static Logger log = LogManager.getLogger(Person.class.getName());

    public Person(){
        id = randomUUID().toString().substring(0, 7);
    }
    public Person(String fio, String sex, LocalDate birthday) {
        id = randomUUID().toString().substring(0, 7);
        this.fio = fio;
        this.sex = sex;
        this.birthday = birthday;
        log.debug("Person "+toString()+" was created");
    }

    private String id;
    @XmlAttribute(name = "fio")
    private String fio;
    @XmlAttribute(name = "sex")
    private String sex;
    @XmlAttribute(name = "birthday")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
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
