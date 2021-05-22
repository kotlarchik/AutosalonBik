package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Instancetransmission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceInstanceTransmission implements DAO<Instancetransmission, Integer> {
    private SessionFactory factory;

    public ServiceInstanceTransmission(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Instancetransmission instancetransmission) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(instancetransmission);
            session.getTransaction().commit();
        }
    }

    @Override
    public Instancetransmission read(Integer id) {
        try(Session session = factory.openSession()){
            Instancetransmission instancetransmission = session.get(Instancetransmission.class, id);
            return instancetransmission;
        }
    }

    @Override
    public List<Instancetransmission> readAll() {
        try(Session session = factory.openSession()){
            Query<Instancetransmission> query = session.createQuery("from Instancetransmission");
            return query.list();
        }
    }

    @Override
    public void update(Instancetransmission instancetransmission) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(instancetransmission);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Instancetransmission instancetransmission) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(instancetransmission);
            session.getTransaction().commit();
        }
    }
}
