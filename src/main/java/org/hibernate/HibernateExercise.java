package org.hibernate;

import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HibernateExercise {
    static SessionFactory factory;
    private static final Logger logger = LogManager.getLogger(HibernateExercise.class);
    // Main
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        factory = configuration.buildSessionFactory();

        // Add
        insertStudent("Antonio", "Martinez", "antonio@email.com", "971112312",70);
        insertStudent("Diego", "Martinez", "diego@email.com", "971888828",70);
        insertStudent("Lucas", "Rodriguez", "lucas@email.com", "971822827",70);
        insertStudent("Manolo", "Iglesias", "manolo@email.com", "971888821",70);

        // Update
        updateStudent(1, "Change name", "Martinez", "antonio@email.com", "971112312",70);

        // Delete
        deleteStudent(2);

        // Get one item
        System.out.println("Get name of student 1: " + getStudent(1).getName());

        // Print all students
        System.out.println("Students..............: ");
        for (Student student : listStudents()) {
            System.out.println(student.getId() + " : " + student.getName());
        }

        // Close and finish
        factory.close();
    }

    // Método para insertar un estudiante
    private static void insertStudent(String name, String lastName, String email, String phone, int age) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Student student = new Student(name, lastName, email, phone,age);
            session.save(student);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para actualizar un estudiante
    private static void updateStudent(int id, String name, String lastName, String email, String phone,int age) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                student.setName(name);
                student.setLastname(lastName);
                student.setEmail(email);
                student.setPhone(phone);
                student.setAge(age);
                session.update(student);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para eliminar un estudiante
    private static void deleteStudent(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para obtener un estudiante por su id
    private static Student getStudent(int id) {
        Session session = factory.openSession();
        try {
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Método para obtener todos los estudiantes
    private static List<Student> listStudents() {
        Session session = factory.openSession();
        try {
            return session.createQuery("FROM Student", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }
}
