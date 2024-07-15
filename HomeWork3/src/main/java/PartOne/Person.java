package PartOne;

import java.io.*;

public class Person implements Serializable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void serialize(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("сериализацию  в " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Person deserialize(String fileName) {
        Person person = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            person = (Person) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;
    }

    public static void main(String[] args) {
        Person person = new Person("Алиса", 222);
        person.serialize("person.ser");

        Person deserializedPerson = Person.deserialize("person.ser");
        System.out.println("десериализацию  Person: " + deserializedPerson.name + ", " + deserializedPerson.age);
    }
}
