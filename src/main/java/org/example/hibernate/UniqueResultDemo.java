package org.example.hibernate;

import org.example.hibernate.entities.Department;
import org.example.hibernate.entities.Employee;
import org.example.hibernate.util.Bootstrap;
import org.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Set;

public class UniqueResultDemo {

    public static Department getDepartment(Session session, String deptNo) {

        String sql = "Select d from " + Department.class.getName() + " d "//
                + " where d.deptNo= :deptNo ";
        Query<Department> query = session.createQuery(sql);
        query.setParameter("deptNo", deptNo);
        return (Department) query.getSingleResult();
    }

    public static Employee getEmployee(Session session, Long empId) {

        String sql = "Select e from " + Employee.class.getName() + " e "
                + " where e.empId= :empId ";
        Query<Employee> query = session.createQuery(sql);
        query.setParameter("empId", empId);
        return (Employee) query.getSingleResult();
    }

    public static void main(String[] args) {

        Bootstrap.loadData();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Department department = getDepartment(session, "D10");
            Set<Employee> employeeSet = department.getEmployees();

            System.out.println("Dept Name: " + department.getDeptName());
            employeeSet.forEach(employee -> System.out.println(" Emp name: " + employee.getEmpName()));

            Employee employee = getEmployee(session, 2L);
            System.out.println("Emp Name: " + employee.getEmpName());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
