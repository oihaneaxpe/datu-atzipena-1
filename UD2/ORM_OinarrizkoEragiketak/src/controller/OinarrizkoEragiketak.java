package controller;

import java.util.Iterator;
import model.Ikaslea;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OinarrizkoEragiketak {

    private static Configuration config = new Configuration();
    private static SessionFactory faktoria = config.configure().buildSessionFactory();

    public static void main(String[] args) {
        //Ikaslea ikas = new Ikaslea(2,"Julen","Alzaa","","");
        //datuaGehitu(ikas);
        datuaEzabatu(2);
        datuakIkusi();

    }

    public static void datuakIkusi() {

        Session saioa = null;
        try {
            saioa = faktoria.openSession();
            System.out.println("IKASLEAK");
            System.out.println("---------------------------------------------");
            for (Iterator<Ikaslea> itIkasleak = saioa.createQuery("FROM Ikaslea").iterate(); itIkasleak.hasNext();) {
                Ikaslea ik = itIkasleak.next();
                System.out.println(ik.getZenbakia() + "\t" + ik.getIzena() + "\t" + ik.getAbizena1());
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            if (saioa != null) {
                saioa.close();
            }
        }
    }

    public static void datuaGehitu(Ikaslea ik) {
        Transaction tx = null;
        Session saioa = null;
        int ikasleZenb;
        try {
            saioa = faktoria.openSession();
            tx = saioa.beginTransaction();
            ikasleZenb = (int) saioa.save(ik);
            tx.commit();
            System.out.println(ikasleZenb + " ikaslea datu-basean gehitu da.");
        } catch (HibernateException e) {
            System.out.println("Ezin izan da ikaslea gehitu => " + e.getCause());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (saioa != null) {
                saioa.close();
            }
        }
    }

    public static void datuaEzabatu(int idIkaslea) {

        Session saioa = null;
        Ikaslea ik = null;
        Transaction tx = null;
        try {
            saioa = faktoria.openSession();
            tx = saioa.beginTransaction();
           // ik = (Ikaslea) saioa.load(Ikaslea.class,idIkaslea); //hau beharrezkoa al da?
            ik = (Ikaslea)saioa.load("Ikaslea", idIkaslea); //horrela ere bai, ezta?
            //get metodoa antzekoa da baina ez du eszepziorik eragiten erregistroa existitzen ez bada.
            saioa.delete(ik);
            tx.commit();
        } catch (ObjectNotFoundException onfe) {
            System.out.println("Ikasle hori ez dago");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            saioa.close();
        }
    }
}