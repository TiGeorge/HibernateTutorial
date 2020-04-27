package org.example.hibernate;

import org.example.hibernate.entities.Department;
import org.example.hibernate.util.Bootstrap;
import org.example.hibernate.util.DataUtils;
import org.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersisterntDemo {


    public static void main(String[] args) {

        Bootstrap.loadData();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        Department department = null;

        try {
            session.getTransaction().begin();

            department = DataUtils.findDepartment(session, "D10");

            department.setLocation("Krasnodar" + System.currentTimeMillis());

            System.out.println("- Location = " + department.getLocation());

            session.flush();

            department.setLocation("Krasnodar" + System.currentTimeMillis());

            System.out.println("- Location = " + department.getLocation());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        session = factory.getCurrentSession();
        try{
            session.getTransaction().begin();

            department = DataUtils.findDepartment(session,"D10");
            System.out.println("- D10 Location = " + department.getLocation());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }



    }

}
