package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Options;
import kotlarchik.model.PTS;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceOptions implements DAO<Options, Integer> {
    private SessionFactory factory;

    public ServiceOptions(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Options options) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(options);
            session.getTransaction().commit();
        }
    }

    @Override
    public Options read(Integer id) {
        try(Session session = factory.openSession()){
            Options options = session.get(Options.class, id);
            return options;
        }
    }

    @Override
    public List<Options> readAll() {
        try(Session session = factory.openSession()){
            Query<Options> query = session.createQuery("from Options");
            return query.list();
        }
    }

    @Override
    public void update(Options options) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(options);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Options options) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(options);
            session.getTransaction().commit();
        }
    }
}
