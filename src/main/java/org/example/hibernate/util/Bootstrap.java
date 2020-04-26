package org.example.hibernate.util;

import org.example.hibernate.entities.Department;
import org.example.hibernate.entities.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Bootstrap {

    public static void LoadData() {

        Department department = new Department();
        department.setDeptId(1);
        department.setDeptNo("D10");
        department.setDeptName("Sales department");

        Date haireDate1 = new GregorianCalendar(2018, 10, 21).getTime();
        Employee employee1 = new Employee(1L, "Peter", "programmer", null,
                haireDate1, 1000f, 100f, department);
        Date haireDate2 = new GregorianCalendar(2019, 1, 11).getTime();
        Employee employee2 = new Employee(2L, "Ivan", "consult", null,
                haireDate2, 1000f, 100f, department);
        Date haireDate3 = new GregorianCalendar(2015, 12, 2).getTime();
        Employee employee3 = new Employee(3L, "Goga", "consult", null,
                haireDate3, 1000f, 100f, department);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(department);
            session.persist(employee1);
            session.persist(employee2);
            session.persist(employee3);
            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
        }
    }
}
