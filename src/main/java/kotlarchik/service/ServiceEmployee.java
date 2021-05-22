package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceEmployee implements DAO<Employee, Integer> {
    private SessionFactory factory;

    public ServiceEmployee(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Employee employee) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        }
    }

    @Override
    public Employee read(Integer id) {
        try(Session session = factory.openSession()){
            Employee employee = session.get(Employee.class, id);
            return employee;
        }
    }

    @Override
    public List<Employee> readAll() {
        try(Session session = factory.openSession()){
            Query<Employee> query = session.createQuery("from Employee");
            return query.list();
        }
    }

    @Override
    public void update(Employee employee) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Employee employee) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
        }
    }
}
