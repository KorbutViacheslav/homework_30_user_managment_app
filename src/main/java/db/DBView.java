package db;

import models.Person.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DBView {
    private static ConnectorToDatabase connectorToDatabase = new ConnectorToDatabase();

    //1*****************************Add new user******************************
    public void addPerson(Person person) {
        try (PreparedStatement prepareStatement = connectorToDatabase.getConnection().
                prepareStatement("INSERT INTO pers.person(first_name, last_name,age) VALUES (?, ?, ?) ")) {
            prepareStatement.setString(1, person.getFirstName());
            prepareStatement.setString(2, person.getLastName());
            prepareStatement.setInt(3, person.getAge());
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //2*****************************Display all users*****************************
    public void getAllPersons() {
        ArrayList<Person> people = new ArrayList<>();
        try (PreparedStatement prepareStatement = connectorToDatabase.getConnection().
                prepareStatement("SELECT * FROM pers.person")) {
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                people.add(new Person(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        people.forEach(person -> System.out.println(person.toString()));
    }

    //3*****************************Display all person oldest by value*****************************
    public void displayAllPersonOldestBy(String age) {
        ArrayList<Person> people = new ArrayList<>();
        try (PreparedStatement prepareStatement = connectorToDatabase.getConnection().
                prepareStatement("SELECT * FROM person WHERE age >=?")) {
            prepareStatement.setInt(1, Integer.parseInt(age));
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                people.add(new Person(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        people.forEach(person -> System.out.println(person.toString()));
    }

    //4*****************************Delete the user*****************************
    public void deleteTheUser(String id) {
        try (PreparedStatement prepareStatement = connectorToDatabase.getConnection().
                prepareStatement("DELETE FROM person WHERE id=?;")) {
            prepareStatement.setInt(1, Integer.parseInt(id));
            System.out.println("You delete person: " + getTheUserByID(id));
            boolean resultSet = prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //5*****************************Update user data*****************************
    public void updateUserData(String id) {
        Scanner scanner = new Scanner(System.in);
        try (PreparedStatement prepareStatement = connectorToDatabase.getConnection().
                prepareStatement("UPDATE person SET first_name = ?, last_name = ?, age =? WHERE id = ?;")) {
            System.out.println("You want update " + getTheUserByID(id));
            System.out.println("Enter new first name person:");
            prepareStatement.setString(1, scanner.next());
            System.out.println("Enter new last name person:");
            prepareStatement.setString(2, scanner.next());
            System.out.println("Enter new age person:");
            prepareStatement.setInt(3, Integer.parseInt(scanner.next()));
            prepareStatement.setInt(4, Integer.parseInt(id));
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Congratulation. You new update data - " + getTheUserByID(id));
    }

    //6*****************************Get the user by ID*****************************
    public Person getTheUserByID(String id) {
        Person person = null;
        try (PreparedStatement prepareStatement = connectorToDatabase.getConnection().
                prepareStatement("SELECT * FROM person where id=?")) {
            prepareStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                person = new Person(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    //7*****************************Get the person by firstName*****************************
    public Person getThePersonByFirstName(String firstName) {
        Person person = null;
        try (PreparedStatement prepareStatement = connectorToDatabase.getConnection().
                prepareStatement("SELECT * FROM person where first_name=?")) {
            prepareStatement.setString(1, firstName);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                person = new Person(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
}
