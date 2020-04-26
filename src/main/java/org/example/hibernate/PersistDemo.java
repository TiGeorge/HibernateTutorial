package org.example.hibernate;

import org.example.hibernate.entities.Department;
import org.example.hibernate.entities.Employee;
import org.example.hibernate.util.Bootstrap;
import org.example.hibernate.util.DataUtils;
import org.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class PersistDemo {

    public static void main(String[] args) {

        Bootstrap.LoadData();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        Department department = null;
        Employee emp = null;
        try {
            session.getTransaction().begin();

            Long maxEmpId = DataUtils.getMaxEmpId(session);
            Long empId = maxEmpId + 1;

            department = DataUtils.findDepartment(session, "D10");

            emp = new Employee();
            emp.setEmpId(empId);
            emp.setEmpNo("E" + empId);
            emp.setEmpName("Name " + empId);
            emp.setJob("Coder");
            emp.setSalary(1000f);
            emp.setManager(null);
            emp.setHireDate(new Date());
            emp.setDepartment(department);

            session.persist(emp);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        System.out.println("Emp No: " + emp.getEmpNo());

    }

}
