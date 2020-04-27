package org.example.hibernate;

import org.example.hibernate.entities.Employee;
import org.example.hibernate.entities.ShortEmpInfo;
import org.example.hibernate.util.Bootstrap;
import org.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ShortEmpInfoQueryDemo {

    public static void main(String[] args) {

        Bootstrap.loadData();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            String sql = "Select new " + ShortEmpInfo.class.getName()
                    + "(e.empId, e.empNo, e.empName)" + " from "
                    + Employee.class.getName() + " e ";

            Query<ShortEmpInfo> query = session.createQuery(sql);

            query.getResultList().forEach(emp -> System.out.println("Emp: " + emp.getEmpNo() + " : "
                    + emp.getEmpName()));

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }


    }
}
