package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class ServiceContract implements DAO<Contract, Integer> {
    private SessionFactory factory;

    public ServiceContract(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Contract contract) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(contract);
            session.getTransaction().commit();
        }
    }

    @Override
    public Contract read(Integer id) {
        try(Session session = factory.openSession()){
            Contract contract = session.get(Contract.class, id);
            return contract;
        }
    }

    @Override
    public List<Contract> readAll() {
        try(Session session = factory.openSession()){
            Query<Contract> query = session.createQuery("from Contract");
            return query.list();
        }
    }

    @Override
    public void update(Contract contract) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(contract);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Contract contract) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(contract);
            session.getTransaction().commit();
        }
    }
}
