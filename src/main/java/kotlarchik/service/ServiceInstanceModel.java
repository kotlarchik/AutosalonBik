package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Instancemodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceInstanceModel implements DAO<Instancemodel, Integer> {
    private SessionFactory factory;

    public ServiceInstanceModel(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Instancemodel instancemodel) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(instancemodel);
            session.getTransaction().commit();
        }
    }

    @Override
    public Instancemodel read(Integer id) {
        try(Session session = factory.openSession()){
            Instancemodel instancemodel = session.get(Instancemodel.class, id);
            return instancemodel;
        }
    }

    @Override
    public List<Instancemodel> readAll() {
        try(Session session = factory.openSession()){
            Query<Instancemodel> query = session.createQuery("from Instancemodel");
            return query.list();
        }
    }

    @Override
    public void update(Instancemodel instancemodel) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(instancemodel);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Instancemodel instancemodel) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(instancemodel);
            session.getTransaction().commit();
        }
    }
}
