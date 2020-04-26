package org.example.hibernate;

import net.bytebuddy.build.Plugin;
import org.example.hibernate.entities.Employee;
import org.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class QueryObjectDemo2 {


    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            String sql = "select e from " + Employee.class.getName() + " e "
                    + "where e.department.deptNo=:deptNo ";

            Query<Employee> query = session.createQuery(sql);

            query.setParameter("deptNo", "D10");

            List<Employee> employees = query.getResultList();

            for (Employee emp : employees) {
                System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
