package org.example.hibernate;

import org.example.hibernate.entities.Employee;
import org.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class QueryObjectDemo {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            String sql = "Select e from " + Employee.class.getName() + " e "
                    + " order by e.empName, e.empNo ";

            Query<Employee> query = session.createQuery(sql);

            List<Employee> employees = query.getResultList();

            for (Employee employee : employees) {
                System.out.println("Emp: " + employee.getEmpNo() + ":"
                        + employee.getEmpName());
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
