package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Equipment;
import kotlarchik.model.Model;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServiceModel implements DAO<Model, Integer> {
    private SessionFactory factory;

    public ServiceModel(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Model model) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(model);
            session.getTransaction().commit();
        }
    }

    @Override
    public Model read(Integer id) {
        try(Session session = factory.openSession()){
            Model model = session.get(Model.class, id);
            return model;
        }
    }

    @Override
    public List<Model> readAll() {
        try(Session session = factory.openSession()){
            Query<Model> query = session.createQuery("from Model");
            return query.list();
        }
    }

    @Override
    public void update(Model model) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(model);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Model model) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(model);
            session.getTransaction().commit();
        }
    }
}
