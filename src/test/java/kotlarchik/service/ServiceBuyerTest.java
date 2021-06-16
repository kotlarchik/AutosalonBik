package kotlarchik.service;

import junit.framework.TestCase;
import kotlarchik.dao.DAO;
import kotlarchik.model.Buyer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

public class ServiceBuyerTest extends TestCase {

    @Test
    public void testCreate() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Buyer, Integer> buyerDAO = new ServiceBuyer(factory);
        Buyer buyer = new Buyer();
        buyer.setName("Иван");
        buyer.setLastName("Иванов");
        buyer.setPatronymic("Иванович");
        buyer.setPassport("1234 666789");
        buyer.setPhone(Integer.parseInt("235466216"));
        buyerDAO.create(buyer);
    }

    @Test
    public void testRead() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Buyer, Integer> buyerDAO = new ServiceBuyer(factory);
        Buyer buyer =  buyerDAO.read(1);
        Assert.assertNotNull(buyer);
    }

    @Test
    public void testReadAll() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Buyer, Integer> buyerDAO = new ServiceBuyer(factory);
        int neweleble = 7;
        int buyer = buyerDAO.readAll().size();
        Assert.assertEquals(buyer, neweleble);
    }

    @Test
    public void testUpdate() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Buyer, Integer> buyerDAO = new ServiceBuyer(factory);
        Buyer buyer = buyerDAO.read(6);
        buyer.setName("Александр");
        buyer.setLastName("Шиколай");
        buyer.setPatronymic("Сатанович");
        buyer.setPassport("6666 666666");
        buyer.setPhone(666666666);
        buyerDAO.update(buyer);
    }

    @Test
    public void testDelete() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Buyer, Integer> buyerDAO = new ServiceBuyer(factory);
        buyerDAO.delete(buyerDAO.read(7));
    }
}