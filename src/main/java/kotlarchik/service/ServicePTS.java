package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Model;
import kotlarchik.model.PTS;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServicePTS implements DAO<PTS, Integer> {
    private SessionFactory factory;

    public ServicePTS(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(PTS pts) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(pts);
            session.getTransaction().commit();
        }
    }

    @Override
    public PTS read(Integer id) {
        try(Session session = factory.openSession()){
            PTS pts = session.get(PTS.class, id);
            return pts;
        }
    }

    @Override
    public List<PTS> readAll() {
        try(Session session = factory.openSession()){
            Query<PTS> query = session.createQuery("from PTS");
            return query.list();
        }
    }

    @Override
    public void update(PTS pts) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(pts);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(PTS pts) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(pts);
            session.getTransaction().commit();
        }
    }
}
