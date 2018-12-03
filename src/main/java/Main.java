import org.joda.time.LocalDate;
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

        repo.add(new Person("Ивнаов Иван Иванович", "male", new LocalDate(1997,03,28)));
        repo.add(new Person("Zyev Pavel Ivanovich", "male", new LocalDate(2008,03,28)));
        repo.add(new Person("Artem", "male", new LocalDate(2008,03,15)));
        repo.add(new Person("Pavel", "male", new LocalDate(2008,03,27))); //27.03.2008
        repo.add(new Person("Ivan", "male", new LocalDate(2006,03,28)));
        repo.add(new Person("Zak", "male", new LocalDate(2119,03,28)));

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
            System.out.println(i + " " + p.toString());
        }
    }
    private static void printListOfPersons(Person[] persons) {
        for (int i = 0; i < persons.length; i++) {
            System.out.println(i + " " + persons[i].toString());
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

        repo.add(new Person(fio, sex, new LocalDate())); //заполнить дату
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
        Scanner stringScanner = new Scanner(System.in);
        int answer = in.nextInt();
        Person[] result= new Person[0];
        switch (answer) {
            case 1: {
                String fio;
                System.out.println("Введите фамилию:");

                fio = stringScanner.nextLine();
                result = repo.find(fio, new FindByFio());
                break;
            }
            case 2: {
                String bday;
                System.out.println("Введите дату:");

                bday = stringScanner.nextLine();
                result = repo.find(bday, new FindByBirthday());
                break;
            }
            case 3: {
                int age;
                System.out.println("Введите возраст:");

                age = in.nextInt();
                result = repo.find(age, new FindByAge());
                break;
            }
        }
        printListOfPersons(result);
    }
}
