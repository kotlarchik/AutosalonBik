package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Equipment;
import kotlarchik.model.Marka;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServiceMarka implements DAO<Marka, Integer> {
    private SessionFactory factory;

    public ServiceMarka(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Marka marka) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(marka);
            session.getTransaction().commit();
        }
    }

    @Override
    public Marka read(Integer id) {
        try(Session session = factory.openSession()){
            Marka marka = session.get(Marka.class, id);
            return marka;
        }
    }

    @Override
    public List<Marka> readAll() {
        try(Session session = factory.openSession()){
            Query<Marka> query = session.createQuery("from Marka");
            return query.list();
        }
    }

    @Override
    public void update(Marka marka) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(marka);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Marka marka) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(marka);
            session.getTransaction().commit();
        }
    }
}
