package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Dealer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class ServiceDealer implements DAO<Dealer, Integer> {
    private SessionFactory factory;

    public ServiceDealer(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Dealer dealer) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(dealer);
            session.getTransaction().commit();
        }
    }

    @Override
    public Dealer read(Integer id) {
        try(Session session = factory.openSession()){
            Dealer dealer = session.get(Dealer.class, id);
            return dealer;
        }
    }

    @Override
    public List<Dealer> readAll() {
        try(Session session = factory.openSession()){
            Query<Dealer> query = session.createQuery("from Dealer");
            return query.list();
        }
    }

    @Override
    public void update(Dealer dealer) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(dealer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Dealer dealer) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(dealer);
            session.getTransaction().commit();
        }
    }
}
