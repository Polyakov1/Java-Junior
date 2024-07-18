package org.example.app;

import org.example.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.exit;

public class App_V2 {

    public static void main(String[] args) {

        String[] options = {"1- Просмотреть весь список курсов",
                "2- Добавить курс",
                "3- Изменить курс",
                "4- Удалить курс",
                "0- Выйти",
        };

        Scanner scanner = new Scanner(System.in);
        int option = 1;

        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()) {

            while (option!=0){
                printMenu(options);
                try {
                    option = scanner.nextInt();
                    switch (option){
                        case 1: viewTheCourses(sessionFactory.getCurrentSession()); break;
                        case 2: addCourse(sessionFactory.getCurrentSession()); break;
                        case 3: changeCourse(sessionFactory.getCurrentSession()); break;
                        case 4: deleteCourse(sessionFactory.getCurrentSession()); break;
                        case 0: exit(0);
                    }
                }
                catch (Exception ex){
                    System.out.println("Пожалуйста, введите число от 1 до 4 или 0");
                    scanner.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Выберите пункт меню : ");
    }

    private static void viewTheCourses(Session session) {
        System.out.println("Вы выбрали пункт меню №1 ");
        System.out.println("Весь список курсов: ");

        session.beginTransaction();

        String sql = "FROM Course ";

        List<Course> courses = session.createQuery(sql).list();

        for (Iterator<Course> it = courses.iterator(); it.hasNext();) {
            Course course = (Course) it.next();
            System.out.println(course);
        }
        session.getTransaction().commit();
    }


    private static void addCourse(Session session) {
        System.out.println("Вы выбрали пункт меню №2 ");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название курса: ");
        String title = scanner.next();

        System.out.println("Введите продолжительность курса: ");
        int duration = scanner.nextInt();

        session.beginTransaction();

        Course course = new Course(title, duration);
        session.save(course);
        System.out.println("Курс успешно добавлен в таблицу");

        session.getTransaction().commit();
    }



    private static void changeCourse(Session session) {

        System.out.println("Вы выбрали пункт меню №3 ");
        System.out.println("Введите id курса, который хотите изменить: "); //TODO предложить отмену выбранной операции
        Scanner scanner = new Scanner(System.in);
        int courseID = scanner.nextInt();

        session.beginTransaction();
        Course retrievedCourse = session.get(Course.class, courseID);

        System.out.println("Введите новое название курса: ");
        String newTitle = scanner.next();

        System.out.println("Введите продолжительность курса: ");
        int newDuration = scanner.nextInt();

        retrievedCourse.updateTitle(newTitle);
        retrievedCourse.updateDuration(newDuration);
        session.update(retrievedCourse);
        System.out.println("Обновление курса с ID="+courseID + "прошло успешно");

        session.getTransaction().commit();
    }


    private static void deleteCourse(Session session) {

        System.out.println("Вы выбрали пункт меню №4 ");
        System.out.println("Введите id курса, который хотите удалить: ");
        Scanner scanner = new Scanner(System.in);
        int courseID = scanner.nextInt();

        session.beginTransaction();

        Course deleteddCourse = session.get(Course.class, courseID);

        session.delete(deleteddCourse);

        session.getTransaction().commit();
    }

}
