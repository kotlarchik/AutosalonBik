package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Bodytype;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServiceBodytype implements DAO<Bodytype, Integer> {
    private SessionFactory factory;

    public ServiceBodytype(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Bodytype bodytype) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(bodytype);
            session.getTransaction().commit();
        }
    }

    @Override
    public Bodytype read(Integer id) {
        try(Session session = factory.openSession()){
            Bodytype bodytype = session.get(Bodytype.class, id);
            return bodytype;
        }
    }

    @Override
    public List<Bodytype> readAll() {
        try(Session session = factory.openSession()){
            Query<Bodytype> query = session.createQuery("from Bodytype");
            return query.list();
        }
    }

    @Override
    public void update(Bodytype bodytype) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(bodytype);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Bodytype bodytype) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(bodytype);
            session.getTransaction().commit();
        }
    }
}
