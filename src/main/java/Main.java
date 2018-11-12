import Repo.Person;
import Repo.Repository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Main {
    public static void main (String[] args){

        Repository repo = new Repository();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");


        repo.Add(new Person("Ивнаов Иван Иванович","male", formatter.parseDateTime("28.03.1997")));
        repo.Add(new Person("Zyev Pavel Ivanovich","male", formatter.parseDateTime("28.03.2008")));
        repo.Add(new Person("Zyev Pavel Ivanovich","male", formatter.parseDateTime("28.03.2008")));


        for(int i=0;i<repo.GetPersonsLength();i++)
        {
            Person p = repo.GetPerson(i);
            System.out.println(p.getId() + " "+p.getFio() +" "+ p.getSex() + " "+ p.Age());
        }
    }
}
