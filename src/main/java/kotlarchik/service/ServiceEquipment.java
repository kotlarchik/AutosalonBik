package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Equipment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServiceEquipment implements DAO<Equipment, Integer> {
    private SessionFactory factory;

    public ServiceEquipment(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Equipment equipment) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(equipment);
            session.getTransaction().commit();
        }
    }

    @Override
    public Equipment read(Integer id) {
        try(Session session = factory.openSession()){
            Equipment equipment = session.get(Equipment.class, id);
            return equipment;
        }
    }

    @Override
    public List<Equipment> readAll() {
        try(Session session = factory.openSession()){
            Query<Equipment> query = session.createQuery("from Equipment");
            return query.list();
        }
    }

    @Override
    public void update(Equipment equipment) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(equipment);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Equipment equipment) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(equipment);
            session.getTransaction().commit();
        }
    }
}
