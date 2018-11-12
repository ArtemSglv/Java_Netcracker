package Repo;

import org.joda.time.DateTime;

import static java.util.UUID.randomUUID;

public class Person {
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
}
