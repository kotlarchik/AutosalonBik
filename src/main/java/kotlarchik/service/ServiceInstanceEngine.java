package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Instanceengine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceInstanceEngine implements DAO<Instanceengine, Integer> {
    private SessionFactory factory;

    public ServiceInstanceEngine(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Instanceengine instanceengine) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(instanceengine);
            session.getTransaction().commit();
        }
    }

    @Override
    public Instanceengine read(Integer id) {
        try(Session session = factory.openSession()){
            Instanceengine instanceengine = session.get(Instanceengine.class, id);
            return instanceengine;
        }
    }

    @Override
    public List<Instanceengine> readAll() {
        try(Session session = factory.openSession()){
            Query<Instanceengine> query = session.createQuery("from Instanceengine");
            return query.list();
        }
    }

    @Override
    public void update(Instanceengine instanceengine) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(instanceengine);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Instanceengine instanceengine) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(instanceengine);
            session.getTransaction().commit();
        }
    }
}
