package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Shipment.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Insert Record
        Shipment s1 = new Shipment("Shipment1", new Date(), "IN_TRANSIT", "Hyderabad", "Delhi");
        session.save(s1);

        tx.commit();
        System.out.println("Record Inserted Successfully");

        // Delete using HQL with named parameter
        session = factory.openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("delete from Shipment where id = :sid");
        query.setParameter("sid", 1);
        int result = query.executeUpdate();

        tx.commit();
        System.out.println("Deleted Rows: " + result);

        session.close();
        factory.close();
    }
}
