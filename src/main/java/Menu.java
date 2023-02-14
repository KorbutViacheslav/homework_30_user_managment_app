import db.DBView;
import models.Person.Person;

import java.util.Scanner;

public class Menu {
    DBView dbView = new DBView();

    public void run() {
        printMenu();
        while (true) {
            System.out.println("Select the option menu...");
            String type = getStringByScanner();
            if (type.equals("0")) {
                System.out.println("Program finish.");
                break;
            } else {
                readUserInput(type);
            }
        }
    }

    private void readUserInput(String type) {
        switch (type) {
            case ("1") -> {
                System.out.println("You have selected the option to add a new person.");
                Person person = getPersonDate();
                dbView.addPerson(person);
                System.out.println("You add new person: " + person);
            }
            case ("2") -> {
                System.out.println("You have selected the option to display all persons");
                dbView.getAllPersons();
            }
            case ("3") -> {
                System.out.println("You have selected the option to display all persons oldest by value.");
                dbView.displayAllPersonOldestBy(getStringByScanner());
            }
            case ("4") -> {
                System.out.println("You have selected the option to delete person");
                System.out.println("Enter the id of the person whose you want to delete:");
                dbView.deleteTheUser(getStringByScanner());
            }
            case ("5") -> {
                System.out.println("You have selected the option to update person data");
                System.out.println("Enter the id of the person whose data you want to update:");
                dbView.updateUserData(getStringByScanner());
            }
            case ("6") -> {
                System.out.println("You have selected the option to get the person by ID");
                System.out.println("Enter person ID:");
                System.out.println(dbView.getTheUserByID(getStringByScanner()));
            }
            case ("7") -> {
                System.out.println("You have selected the option to get the person by first name");
                System.out.println("Enter person first name:");
                System.out.println(dbView.getThePersonByFirstName(getStringByScanner()));
            }
            default -> {
                System.out.println("You have entered a value that does not exist. Please make your choice again...");
            }
        }
    }

    private Person getPersonDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter last name");
        String lastName = scanner.next();
        System.out.println("Enter age");
        int age = scanner.nextInt();
        return new Person(name, lastName, age);
    }

    private static void printMenu() {
        System.out.println("1 - Add new person");
        System.out.println("2 - Display all persons");
        System.out.println("3 - Display all persons oldest by value");
        System.out.println("4 - Delete the person");
        System.out.println("5 - Update person data");
        System.out.println("6 - Get the person by ID");
        System.out.println("7 - Get the person by firstName");
        System.out.println("0 - Finish");
    }

    private String getStringByScanner() {
        return new Scanner(System.in).next();
    }
}