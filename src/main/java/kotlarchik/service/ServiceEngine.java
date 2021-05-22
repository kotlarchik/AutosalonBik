package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Engine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceEngine implements DAO<Engine, Integer> {
    private SessionFactory factory;

    public ServiceEngine(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Engine engine) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(engine);
            session.getTransaction().commit();
        }
    }

    @Override
    public Engine read(Integer id) {
        try(Session session = factory.openSession()){
            Engine engine = session.get(Engine.class, id);
            return engine;
        }
    }

    @Override
    public List<Engine> readAll() {
        try(Session session = factory.openSession()){
            Query<Engine> query = session.createQuery("from Engine");
            return query.list();
        }
    }

    @Override
    public void update(Engine engine) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(engine);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Engine engine) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(engine);
            session.getTransaction().commit();
        }
    }
}
