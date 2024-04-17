import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Person {

    public enum Sex {
        MALE, FEMALE
    }

    private String name;
    private LocalDate birthday;
    private Sex gender;
    private String emailAddress;

    // Constructor
    public Person(String name, LocalDate birthday, Sex gender, String emailAddress) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }
    
    public void printPerson() {
        System.out.println("Name: " + name + ", Email: " + emailAddress + ", Age: " + getAge() + ", Gender: " + gender);
    }

    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsLocalClass(List<Person> roster, int age) {
        class LocalClassChecker implements CheckPerson {
            public boolean test(Person p) {
                return p.getAge() >= age;
            }
        }
        printPersons(roster, new LocalClassChecker());
    }

    public static void printPersonsAnonymousClass(List<Person> roster, int age) {
        printPersons(roster, new CheckPerson() {
            public boolean test(Person p) {
                return p.getAge() >= age;
            }
        });
    }

    public static void printPersonsLambda(List<Person> roster, int age) {
        printPersons(roster, p -> p.getAge() >= age);
    }
}