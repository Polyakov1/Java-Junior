package PartTwo;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Геттеры и сеттеры

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Метод для добавления нового человека
    public void addPerson(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
    }

    // Метод для обновления информации о человеке
    public void updatePerson(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.merge(this);
        entityManager.getTransaction().commit();
    }

    // Метод для удаления человека из базы данных
    public void deletePerson(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(this) ? this : entityManager.merge(this));
        entityManager.getTransaction().commit();
    }

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersonPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Person person1 = new Person("Bob", 25);
        person1.addPerson(entityManager);

        Person person2 = new Person("Alice", 30);
        person2.addPerson(entityManager);

        Person retrievedPerson = entityManager.find(Person.class, person1.getId());
        System.out.println("Retrieved Person: " + retrievedPerson.getName() + ", " + retrievedPerson.getAge());

        person1.setAge(26);
        person1.updatePerson(entityManager);

        retrievedPerson = entityManager.find(Person.class, person1.getId());
        System.out.println("Updated Person: " + retrievedPerson.getName() + ", " + retrievedPerson.getAge());

        person2.deletePerson(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }
}
