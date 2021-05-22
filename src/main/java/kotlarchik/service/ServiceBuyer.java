package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Bodytype;
import kotlarchik.model.Buyer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServiceBuyer implements DAO<Buyer, Integer> {
    private SessionFactory factory;

    public ServiceBuyer(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Buyer buyer) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(buyer);
            session.getTransaction().commit();
        }
    }

    @Override
    public Buyer read(Integer id) {
        try(Session session = factory.openSession()){
            Buyer buyer = session.get(Buyer.class, id);
            return buyer;
        }
    }

    @Override
    public List<Buyer> readAll() {
        try(Session session = factory.openSession()){
            Query<Buyer> query = session.createQuery("from Buyer");
            return query.list();
        }
    }

    @Override
    public void update(Buyer buyer) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(buyer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Buyer buyer) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(buyer);
            session.getTransaction().commit();
        }
    }
}
