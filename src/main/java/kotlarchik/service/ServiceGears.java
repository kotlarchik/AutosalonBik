package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Buyer;
import kotlarchik.model.Gears;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServiceGears implements DAO<Gears, Integer> {
    private SessionFactory factory;

    public ServiceGears(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Gears gears) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(gears);
            session.getTransaction().commit();
        }
    }

    @Override
    public Gears read(Integer id) {
        try(Session session = factory.openSession()){
            Gears gears = session.get(Gears.class, id);
            return gears;
        }
    }

    @Override
    public List<Gears> readAll() {
        try(Session session = factory.openSession()){
            Query<Gears> query = session.createQuery("from Gears");
            return query.list();
        }
    }

    @Override
    public void update(Gears gears) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(gears);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Gears gears) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(gears);
            session.getTransaction().commit();
        }
    }
}
