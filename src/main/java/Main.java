import repository.Person;
import repository.PersonRepository;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import repository.impl.*;
import sorts.impl.*;

import java.util.Scanner;

public class Main {
    static private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
    static private PersonRepository repo = new PersonRepository(new QuickSort());
    //static private PersonRepository repo = new PersonRepository(new BubbleSort());
    //static private PersonRepository repo = new PersonRepository(new InsertionSort());

    public static void main(String[] args) {

        repo.add(new Person("Ивнаов Иван Иванович", "male", formatter.parseDateTime("28.03.1997")));
        repo.add(new Person("Zyev Pavel Ivanovich", "male", formatter.parseDateTime("28.03.2008")));
        repo.add(new Person("Artem", "male", formatter.parseDateTime("15.03.2008")));
        repo.add(new Person("Pavel", "male", formatter.parseDateTime("27.03.2008")));
        repo.add(new Person("Ivan", "male", formatter.parseDateTime("28.03.2006")));
        repo.add(new Person("Zak", "male", formatter.parseDateTime("28.03.2119")));

        while (true) {

            printMainMenu();

            Scanner in = new Scanner(System.in);
            int answer = in.nextInt();
            selectorMainMenu(answer);
        }
    }

    private static void printMainMenu() {
        System.out.println("Привет! Вот меню:");
        System.out.println("1 - Вывести список людей");
        System.out.println("2 - Добавить человека");
        System.out.println("3 - Удалить человека");
        System.out.println("4 - Поиск");
        System.out.println("5 - Сортировка");
        System.out.println("0 - Выход");
    }

    private static void selectorMainMenu(int index) {
        switch (index) {
            case 1: {
                printListOfPersons();
                break;
            }
            case 2: {
                addNewPerson();
                break;
            }
            case 3: {
                deletePerson();
                break;
            }
            case 4: {
                selectFinder();
                break;
            }
            case 5: {
                repo.sort(Person.FioComparator);
                printListOfPersons();
                break;
            }
            case 0: {
                System.exit(0);
                break;
            }

        }
    }

    private static void printListOfPersons() {
        for (int i = 0; i < repo.getPersonsLength(); i++) {
            Person p = repo.getPerson(i);
            System.out.println(i + " " + "id:" + p.getId() + " " + p.getFio() + " " + p.getSex() + " " + p.age());
        }
    }
    private static void printListOfPersons(Person[] persons) {
        for (int i = 0; i < persons.length; i++) {
            System.out.println(i + " " + "id:" + persons[i].getId() + " " + persons[i].getFio() + " " + persons[i].getSex() + " " + persons[i].age());
        }
    }

    private static void addNewPerson() {
        Scanner in = new Scanner(System.in);
        String fio, sex, date;

        System.out.println("Введите ФИО:");
        fio = in.nextLine();
        System.out.println("Введите пол:");
        sex = in.nextLine();
        System.out.println("Введите дату рождения в формате ДД.ММ.ГГГГ:");
        date = in.nextLine();

        repo.add(new Person(fio, sex, formatter.parseDateTime(date)));
    }

    private static void deletePerson() {
        Scanner in = new Scanner(System.in);
        int input_index;
        System.out.println("Введите номер человека:");
        printListOfPersons();
        input_index = in.nextInt();
        repo.delete(input_index);
    }

    private static void selectFinder() {
        System.out.println("1 - По фамилии");
        System.out.println("2 - По дате рождения");
        System.out.println("3 - По возрасту");

        Scanner in = new Scanner(System.in);
        int answer = in.nextInt();
        Person[] result= new Person[0];
        switch (answer) {
            case 1: {
                System.out.println("Введите фамилию:");
                String fio;
                fio = in.nextLine();
                result = repo.find(fio, new FindByFio());
                break;
            }
            case 2: {
                System.out.println("Введите дату:");
                String bday;
                bday = in.nextLine();
                result = repo.find(bday, new FindByBirthday());
                break;
            }
            case 3: {
                System.out.println("Введите возраст:");
                int age;
                age = in.nextInt();
                result = repo.find(age, new FindByAge());
                break;
            }
        }
        printListOfPersons(result);
    }
}
