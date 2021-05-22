package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Transmission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceTransmission implements DAO<Transmission, Integer> {
    private SessionFactory factory;

    public ServiceTransmission(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Transmission transmission) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(transmission);
            session.getTransaction().commit();
        }
    }

    @Override
    public Transmission read(Integer id) {
        try(Session session = factory.openSession()){
            Transmission transmission = session.get(Transmission.class, id);
            return transmission;
        }
    }

    @Override
    public List<Transmission> readAll() {
        try(Session session = factory.openSession()){
            Query<Transmission> query = session.createQuery("from Transmission");
            return query.list();
        }
    }

    @Override
    public void update(Transmission transmission) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(transmission);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Transmission transmission) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(transmission);
            session.getTransaction().commit();
        }
    }
}
